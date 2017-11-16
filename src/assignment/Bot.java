package assignment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by mariooliveira on 15/11/2017.
 */



@Path("/bot")
public class Bot {

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPlain(@PathParam("name") String name) {

        return "Hello, " + name + "!";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloJson(@QueryParam("name") String name) {
        return "{\"hello\":\"" + name + "\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String helloPost(String name) {
        return "{\"hello\":\"" + name +"\"}";
    }

    @POST
    @Path("/form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String helloForm(@FormParam ("name") String name) {
        return "{\"hello\":\"" + name +"\"}";
    }

}
