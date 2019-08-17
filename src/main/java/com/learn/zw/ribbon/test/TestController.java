package com.learn.zw.ribbon.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: zhang.wei
 * @Date: 2019/3/21 17:02
 * @Version: 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping(value = "/test/ribbon", method = RequestMethod.GET)
    public String testRibbon() {
        String value = restTemplate.getForObject("http://learn-provider/test/port", String.class);
        return value;
    }

    @RequestMapping(value = "/test/loadBalance")
    public String loadBalance() {
        ServiceInstance instance = loadBalancerClient.choose("learn-provider");
        return "服务信息：" + instance.getServiceId() + "，ip/端口：" + instance.getHost() + "/" + instance.getPort();
    }
}
