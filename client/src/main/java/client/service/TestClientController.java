package client.service;

import client.bean.User;
import client.util.AESOperator;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dujianfeng on 17/8/16.
 */
@Service
public class TestClientController {

    @Autowired
    public RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "systemError")
    public Map<String,Object> userRegister(@RequestParam(value = "name")String name, @RequestParam(value = "pwd")String pwd){

        String userPwd="";
        try {
            userPwd= AESOperator.getInstance().encrypt(pwd);
            System.out.print("客户端密码加密后的值:"+userPwd);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return restTemplate.getForObject("http://SPRING-CLOUD-ZUUL/dujianfeng2/dujianfeng/userRegister?userName="+name+"&userPwd="+userPwd,Map.class);
    }

    public Map<String,Object> getUsers(){
        return restTemplate.getForObject("http://SPRING-CLOUD-ZUUL/api2/duke/users",Map.class);
    }

    public Map<String,Object> systemError(String name,String pwd){
        Map<String,Object> errorMap=new HashMap<>();
        errorMap.put("error","远程调用微服务发生了错误");
        return errorMap;
}
}
