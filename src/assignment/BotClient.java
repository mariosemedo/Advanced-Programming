package assignment;

import org.apache.catalina.startup.Tomcat;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 * Created by mariooliveira on 15/11/2017.
 */
public class BotClient {


    public WebTarget webTarget;

    public BotClient() {
        webTarget = ClientBuilder.newClient().target(TomcatServer.HELLO_URL);
    }


}
