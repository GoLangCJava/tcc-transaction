tcc transaction 原著链接：https://github.com/changmingxie/tcc-transaction

本文是在tcc transaction1.2.x基础上实现tcc transaction基于springboot2.x的自动配置与springcloud feign自动集成

扩展2个类在@RequestBody传参情况下使用

    org.mengyun.tcctransaction.context.TransactionContextAware
    org.mengyun.tcctransaction.context.AwareTransactionContextEditor   

        
使用指南

    第一步引入maven依赖
        <dependency>
            <groupId>org.mengyun</groupId>
            <artifactId>tcc-transaction-spring-cloud-starter</artifactId>
            <version>1.2.x</version>
        </dependency>
    第二部在yml配置文件加入配置
        tcc:
          enabled: true #开启springboot自动配置
          data-source-config:
            data-source-provider: com.alibaba.druid.pool.DruidDataSource #连接池 必须配置项
            driver-class-name: com.mysql.jdbc.Driver #必须配置项
            url: jdbc:mysql://localhost:3306/tcc?useSSL=false #必须配置项
            username: root #必须配置项
            password: 123456 #必须配置项
            domain: xxx #非必须项
            table-suffix: xxx #非必须项
          feign:
            enabled: true #开启tcc transaction feign集成
    第三步
        FeignClient接口方法添加@Compensable注解
        详情参考tcc-transaction-springcloud-sample
        