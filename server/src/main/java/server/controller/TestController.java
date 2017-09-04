package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import server.bean.User;
import server.util.AESOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dujianfeng on 17/8/10.
 */
@RestController
@RequestMapping(value = "dujianfeng")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "test")
    public String outHelloWorld(){
        return "hello duke";
    }

    @RequestMapping(value = "/getUsers")
    public Map<String,Object> getUserList(){
        Map<String,Object> resultMap=new HashMap<>();
        List<User> allUsers=new ArrayList<>();
        for (int i=0;i<3;i++){
            allUsers.add(new User("duke"+i,20));
        }
        resultMap.put("data",allUsers);
        return resultMap;
    }

    @GetMapping(value = "/userRegister")
    public Map<String,Object> userRegister(@RequestParam(value = "userName")String userNmae,
                                           @RequestParam(value = "userPwd") String userPwd){
        Map<String,Object> resultMap=new HashMap<>();


        try {
            String DeString = AESOperator.getInstance().decrypt(userPwd);
            System.out.print("服务端解密后的值:"+DeString);
            if(DeString.equals("123")){
                resultMap.put("userName",userNmae);
                resultMap.put("userPwd",userPwd);
            }else{
                resultMap.put("message","登录失败");
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }


        return resultMap;

    }

    @RequestMapping("/registered")
    public String getRegistered(){
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");
        System.out.println(discoveryClient.getLocalServiceInstance());
        System.out.println("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());

        for( String s :  discoveryClient.getServices()){
            System.out.println("services " + s);
            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
            for(ServiceInstance si : serviceInstances){
                System.out.println("    services:" + s + ":getHost()=" + si.getHost());
                System.out.println("    services:" + s + ":getPort()=" + si.getPort());
                System.out.println("    services:" + s + ":getServiceId()=" + si.getServiceId());
                System.out.println("    services:" + s + ":getUri()=" + si.getUri());
                System.out.println("    services:" + s + ":getMetadata()=" + si.getMetadata());
            }

        }

        System.out.println(list.size());
        if (list != null && list.size() > 0 ) {
            System.out.println( list.get(0).getUri()  );
        }
        return null;
    }
}
