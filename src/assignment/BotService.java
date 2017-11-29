package assignment;

import javax.ws.rs.*;

/**
 * Created by mariooliveira on 15/11/2017.
 */

@Path("/bot")
public class BotService {

    public static Game game = GameServer.game;

    @GET
    @Path("/{players}")
    public String getPlayers() {

        return game.getConnectedPlayers().toString();
    }

    @GET
    @Path("/add/")
    public String addBot(@PathParam("bot") String name) {
        while (Game.connectedPlayers.size() < 4) {
            Bot bot = new Bot("BOT" + Game.botID++);
            Game.connectedPlayers.add(bot);
        }


        return Game.connectedPlayers.toString();
    }

    @GET
    @Path("/vote")
    public String castVote() {

        Game.connectedPlayers.stream().filter(bot -> bot.name.contains("BOT")).forEach(bot -> {
            ((Bot) bot).castVote();
        });

        return "BOTS";
    }


    @GET
    @Path("/buy")
    public String buy(){

        Game.connectedPlayers.stream().filter(bot -> bot.name.contains("BOT")).forEach(bot ->{
            ((Bot) bot).buy();
        });
        return "BOT Bought";
    }

    @GET
    @Path("/sell")
    public String sell(){
        Game.connectedPlayers.stream().filter(bot -> bot.name.contains("BOT")).forEach(bot ->{
            ((Bot) bot).sell();
        });
        return "BOT Sold";
    }

    @GET
    @Path("/reset")
    public String reset(){

        Game.connectedPlayers.stream().filter(bot -> bot.name.contains("BOT")).forEach(Player::resetPlayer);
        return "BOT Reset";
    }

    /*@GET
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
    }*/

}
