package com.server2;

import com.server2.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by dujianfeng on 17/8/23.
 */
@RequestMapping(value = "duke")
@RestController
public class UserController {


    @Autowired
     RestTemplate restTemplate;


    /**
     * 获取用户列表
     * @return
     */
    @GetMapping(value = "users")
    private Map<String,Object> getUsers(){
        Map<String,Object> resultMap=restTemplate.getForObject("http://SPRING-CLOUD-SERVER1/dujianfeng/getUsers",Map.class);
        return resultMap;
    }



}
