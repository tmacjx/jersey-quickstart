package com.bokecc.config;

import com.bokecc.util.IpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Slf4j
@Configuration
public class AsyConfig {


    @Bean("taskExecutor")
    public TaskExecutor taskExecutor() {

        log.info("innit asy task Executor");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        int core = Runtime.getRuntime().availableProcessors();

        log.info("core = {}", core);

        //配置核心线程数
        executor.setCorePoolSize(core);

        //配置最大线程数
        executor.setMaxPoolSize(core * 2 + 1);

        // 允许线程空闲时间（单位：默认为秒）
        executor.setKeepAliveSeconds(5);

        //配置队列大小
        executor.setQueueCapacity(5000);

        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.setAwaitTerminationSeconds(10);

        //执行初始化
        executor.initialize();

        return executor;
    }
}
