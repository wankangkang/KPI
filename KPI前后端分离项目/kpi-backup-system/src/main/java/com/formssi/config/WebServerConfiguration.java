/**
 * FileName:WebServerConfiguration.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月8日下午11:51:49
 ********************************
 *Modifycation History:
 *date:2018年1月8日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.config;

/**
 * @author Bill
 *
 */
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;  
  
//@Configuration  
public class WebServerConfiguration  
{  
    @Bean  
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory()  
    {  
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();  
        tomcatFactory.setPort(9001);
        //tomcatFactory.setAddress("");
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());  
        return tomcatFactory;  
    }  
}  
class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer  
{  
    public void customize(Connector connector)  
    {  
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();  
        //设置最大连接数  
        protocol.setMaxConnections(2000);  
        //设置最大线程数  
        protocol.setMaxThreads(2000);  
        protocol.setConnectionTimeout(3000000);  
    }  
}  
