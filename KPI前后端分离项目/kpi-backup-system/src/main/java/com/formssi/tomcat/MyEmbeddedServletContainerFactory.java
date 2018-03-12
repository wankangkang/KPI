/**
 * FileName:MyEmbeddedServletContainerFactory.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月8日下午11:32:17
 ********************************
 *Modifycation History:
 *date:2018年1月8日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * @author Bill
 *
 */
//@Component
public class MyEmbeddedServletContainerFactory extends TomcatEmbeddedServletContainerFactory{

	public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
		return super.getEmbeddedServletContainer(initializers);
	}
	
	protected void customerizeConnector(Connector connector) {
		super.customizeConnector(connector);
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		protocol.setMaxConnections(2000);
		protocol.setMaxThreads(2000);
		protocol.setConnectionTimeout(500000);
	}
}
