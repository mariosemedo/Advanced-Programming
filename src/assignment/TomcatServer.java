package assignment;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import java.io.File;

/**
 * Created by mariooliveira on 15/11/2017.
 */
public class TomcatServer implements Runnable {

    public static final int TOMCAT_PORT = 8080;
    public static final String EXAMPLES_URL = "http://localhost:8080/examples/rest";
    public static final String BOT_URL = EXAMPLES_URL + "/bot";


    TomcatServer(){

    }


    @Override
    public void run() {

        // JAX-RS (Jersey) configuration
        ResourceConfig config = new ResourceConfig();
        // Packages where Jersey looks for web service classes
        config.packages("assignment");
        // Configure Jersey to use Gson
        config.register(GsonMessageBodyHandler.class);
        // Enable logging for debugging purposes
        // Comment out next line to turn off logging
        //config.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL, "INFO");
        // Tomcat configuration
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);
        // Add web application
        Context context = null;
        try {
            context = tomcat.addWebapp("/examples", new File("WebContent").getAbsolutePath());
        } catch (ServletException e) {
            e.printStackTrace();
        }
        // Declare Jersey as a servlet
        Tomcat.addServlet(context, "jersey", new ServletContainer(config));
        // Map certain URLs to Jersey
        context.addServletMappingDecoded("/rest/*", "jersey");
        // Start server
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();

    /*    ResourceConfig config = new ResourceConfig();
        // Packages where Jersey looks for web service classes
        config.packages("assignment");


        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);


        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }*/

    }
}
