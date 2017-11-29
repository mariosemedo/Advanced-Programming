package assignment;

import org.apache.catalina.startup.Tomcat;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by mariooliveira on 15/11/2017.
 */
public class BotClient {


    public WebTarget webTarget;

    public BotClient(){

        webTarget = ClientBuilder.newClient().target(TomcatServer.BOT_URL);
    }

    public String getPlayers(){

        return webTarget.path("players").request().get(String.class);
    }

    public synchronized String addBot(){

        return webTarget.path("add").request().get(String.class);
    }

    public synchronized String castVote(){

        return webTarget.path("vote").request().get(String.class);
    }

    public synchronized String buy(){

        return webTarget.path("buy").request().get(String.class);
    }

    public synchronized String sell(){

        return webTarget.path("sell").request().get(String.class);
    }

    public synchronized String reset(){

        return webTarget.path("reset").request().get(String.class);
    }


  /*  public WebTarget webTarget;

    public BotClient() {
        webTarget = ClientBuilder.newClient().target(TomcatServer.BOT_URL);
    }

    public String helloPathParam(String name) {
        return webTarget.path(name).request().get(String.class);
    }

    public String helloQueryParam(String name) {
        System.out.println(name);
        return webTarget.queryParam("name", name).request().get(String.class);
    }

    public String helloPost(String name) {
        return webTarget.request().post(Entity.json(name), String.class);
    }

    public String helloForm(String name) {
        Form form = new Form();
        form.param("name", name);
        return webTarget.path("form").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED))
                .readEntity(String.class);
    }
*/

    public static void main(String[] args) {
        BotClient client = new BotClient();

        System.out.println(client.getPlayers());


        /*System.out.println("helloPathParam: " + client.helloPathParam("Alice"));
        System.out.println("helloQueryParam: " + client.helloQueryParam("Bob"));
        System.out.println("helloPost:" + client.helloPost("Charlie"));
        System.out.println("helloForm:" + client.helloForm("Dorothy"));*/
    }
}
