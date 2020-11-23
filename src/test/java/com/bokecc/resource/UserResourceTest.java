package com.bokecc.resource;

import com.bokecc.Application;
import com.bokecc.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

// SpringMVC测试Resource
//@RunWith(SpringRunner.class) //表示在测试环境中运行
//@SpringBootTest(classes = Application.class)
//public class UserResourceTest {
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mvc;
//    // private MockHttpSession session;
//    @Autowired
//    ObjectMapper mapper;
//
//    @Before
//    public void setupMockMvc(){
//        //初始化MockMvc对象
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    public void getUser() throws Exception{
//        int id = 1;
//        String uri = "/api/user/" + id;
//        RequestBuilder request = MockMvcRequestBuilders.get(uri)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8);
//
//        mvc.perform(request)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getUsers() throws Exception {
//        String uri = "/api/user";
//        RequestBuilder request = MockMvcRequestBuilders.get(uri)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .accept(MediaType.APPLICATION_JSON_UTF8);
//
//        mvc.perform(request)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    @Transactional
//    public void addUser() throws Exception{
//        String uri = "/api/user";
//
//        User user = new User();
//        user.setUserName("test");
//        user.setUserId("1213");
//        // jackjson序列化
//        String json = mapper.writeValueAsString(user);
//
//        RequestBuilder request = MockMvcRequestBuilders.post(uri)
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(json.getBytes());
//
//        mvc.perform(request)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());;
//    }
//
//    @Test
//    @Transactional
//    public void deleteUser() throws Exception{
//        String uri = "/api/user/1";
//
//        RequestBuilder request = MockMvcRequestBuilders.delete(uri)
//                .accept(MediaType.APPLICATION_JSON_UTF8);
//
//        mvc.perform(request)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());;
//    }
//}

//Jersey测试Resources
@RunWith(SpringRunner.class) //表示在测试环境中运行
@SpringBootTest(classes = Application.class)
public class UserResourceTest {

    private static  final String server_name = "http://localhost:8080";
    private static HttpHeaders headers;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @BeforeClass
    public static void setHeader(){
        headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    }

    @Test
    public void getUser() throws Exception{
        int id = 1;
        String url = server_name +  "/api/user/1";

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class, httpEntity);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        User user = response.getBody();
        Assert.assertNotNull(user);
    }

    @Test
    public void getUsers() throws Exception {
        String url = server_name + "/api/user";
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);
        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class, httpEntity);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        User[] userList = response.getBody();
        Assert.assertTrue(userList.length > 0);
    }

    @Test
    @Transactional
    public void addUser() throws Exception {
        String url = server_name + "/api/user";

        User user = new User();
        user.setUserName("test");
        user.setUserId("1213");
        // jackjson序列化
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
        String json = mapper.writeValueAsString(user);
        JSONObject userJSon = new JSONObject();
        HttpEntity<String> httpEntity =
                new HttpEntity<String>(json, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response);
    }

    @Test
    @Transactional
    public void updateUser() throws Exception {
        String url = server_name + "/api/user/" + 1;

        User user = new User();
        user.setUserName("sss");
        user.setUserId("1213");
        // jackjson序列化
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
        String json = mapper.writeValueAsString(user);
        JSONObject userJSon = new JSONObject();
        HttpEntity<String> httpEntity =
                new HttpEntity<String>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response);
    }

    @Test
    @Transactional
    public void deleteUser() throws Exception{
        String url = server_name + "/api/user/" + 1;
        // restTemplate.delete(url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE,
                null, String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}