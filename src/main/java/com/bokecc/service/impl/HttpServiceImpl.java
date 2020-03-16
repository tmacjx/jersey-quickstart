package com.bokecc.service.impl;

import com.bokecc.service.IhttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HttpServiceImpl implements IhttpService {

    @Autowired
    private RestTemplate restTemplate;




}
