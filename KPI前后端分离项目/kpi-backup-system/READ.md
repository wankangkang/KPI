quick start
1.执行src/main/resource下的sql文件建表导入数据
2.配置application.yml中的生效环境
3.根据生效环境配置相应application-xxx.yml中的redis和mysql连接属性
4.如需前端访问，还需配置application.yml中的cors跨域范文请求头设置
5.sts开发工具上执行run as ---maven install 生成jar包
6.前端build后配置nginx部署
