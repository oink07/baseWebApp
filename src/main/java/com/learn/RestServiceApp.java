/**
 * 
 */
package com.learn;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.learn.spring.AppConfig;

/**
 * @author HY
 *
 */
public class RestServiceApp {
	
	private final static int httpPort = 8080;
	private final static int httpTimeout = 30000;
	private final static int httpsPort = 8443;
	private final static int httpsTimeout = 500000;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Server server = new Server();
        
        //HTTP Configuration
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(httpsPort);
        http_config.setOutputBufferSize(32768);
        ServerConnector http = new ServerConnector(server,new HttpConnectionFactory(http_config));        
        http.setPort(httpPort);
        http.setIdleTimeout(httpTimeout);
        
        //HTTPS Configuration
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("etc/keystore");
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");
        
        HttpConfiguration https_config = new HttpConfiguration(http_config);
        https_config.addCustomizer(new SecureRequestCustomizer());
                
        
        ServerConnector https = new ServerConnector(server,
        	new SslConnectionFactory(sslContextFactory,"http/1.1"),
        	new HttpConnectionFactory(https_config));
    	https.setPort(httpsPort);
    	https.setIdleTimeout(httpsTimeout);
    	
    	server.setConnectors(new Connector[] { http, https });
    	
    	server.setHandler(getRestServiceHandler());
        server.start();
        server.join();

	}
	
	private static ServletContextHandler getRestServiceHandler(){
		final ServletContextHandler servletContext = new ServletContextHandler();
		
		final ServletHolder servletHolder = new ServletHolder( new CXFServlet() );

		servletContext.setContextPath( "/" );
		servletContext.addServlet( servletHolder, "/rest/*" ); 
		servletContext.addEventListener( new ContextLoaderListener() );

		servletContext.setInitParameter( "contextClass", AnnotationConfigWebApplicationContext.class.getName() );
		servletContext.setInitParameter( "contextConfigLocation", AppConfig.class.getName() );
		
		return servletContext;
	}

}
