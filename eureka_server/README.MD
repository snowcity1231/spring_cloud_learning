	Spring Cloud Netflix，该项目是Spring Cloud的子项目之一，主要内容是对Netflix公司一系列开源产品的包装，它为Spring Boot应用提
	供了自配置的Netflix OSS整合。通过一些简单的注解，开发者就可以快速的在应用中配置一下常用模块并构建庞大的分布式系统。它主要提供
	的模块包括：服务发现（Eureka），断路器（Hystrix），智能路有（Zuul），客户端负载均衡（Ribbon）等。	
	
这里是服务发现模块Eureka中一个简单的服务注册中心

###################2017-01-19###################
Eureka Server配置多个节点，实现高可用集群



------------------2017-02-10----------------------
方便测试开发，暂还原为单机模式，如果需要集群模式，将application.properties替换为/resources/cluster 的三个application-peer*配置文件即可
