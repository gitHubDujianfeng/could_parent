package client.controller;


import client.bean.User;
import client.service.TestClientController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by dujianfeng on 17/8/11.
 */
@RestController
@RequestMapping(value = "/api")
public class ClientController {

    @Autowired
    TestClientController testClientController;

@Autowired
@LoadBalanced
private RestTemplate restTemplate;

    @GetMapping(value = "register")
    public Map<String,Object> userRegister(@RequestParam(value = "name")String name,@RequestParam(value = "pwd")String pwd){
        return testClientController.userRegister(name,pwd);

    }

    @GetMapping(value = "users")
    public Map<String,Object> users(){
        return testClientController.getUsers();
    }




}
