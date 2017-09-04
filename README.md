# could_parent &杜建峰
该项目利用当下流行的spring cloud 实现了高可用分布式微服务

启动流程:

1:eureka服务(虚拟了3台服务容器)注:本机修改一下host文件增加几个ip映射配置3台服务器全部启动

2:server 测试服务一

3:server2 测试服务二

4:zuul 路由网关

5:ribbon 客户端启动

A:可以试着关闭某一台服务器其他的二台服务器依然能够为ribbon客户端提供服务工作

B:eureka主配置文件:
  spring:
  application:
    name: microservice-eureka-server
  profiles:
    active: peer1
---
spring:
  profiles: peer1
server:
  port: 8761
eureka:
  instance:
    hostname: eureka-server-peer1
  client:
    serviceUrl:
      defaultZone: http://eureka-server-peer2:8762/eureka/,http://eureka-server-peer3:8763/eureka/

---
spring:
  profiles: peer2
server:
  port: 8762
eureka:
  instance:
    hostname: eureka-server-peer2
  client:
    serviceUrl:
      defaultZone: http://eureka-server-peer1:8761/eureka/,http://eureka-server-peer3:8763/eureka/

---
spring:
  profiles: peer3
server:
  port: 8763
eureka:
  instance:
    hostname: eureka-server-peer3
  client:
    serviceUrl:
      defaultZone: http://eureka-server-peer1:8761/eureka/,http://eureka-server-peer2:8762/eureka/




备注:后期会完善spring config 的集成 以及redis的集成 和分布式事物的集成

QQ:1317350817
