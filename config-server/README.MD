基于Spring Cloud构建的分布式配置中心，此项目为服务端

spring.profiles.active=native开启本地化配置后，通过http://localhost:7001/democonfig/prod访问本地prod环境配置，democonfig即为配置对应应用名，同理，http://localhost:7001/democonfig/dev访问本地开发环境配置

git配置仓库现有两套分支，其中master配置带版本号1.0，config-branch-test分支带版本号2.0
http://localhost:7001/democonfig/prod/master访问master分支下prod环境配置，
http://localhost:7001/democonfig/dev/config-branch-test访问config-branch-test分支下开发环境的配置