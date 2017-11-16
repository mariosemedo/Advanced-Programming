package assignment;

import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mariooliveira on 08/11/2017.
 */
public class GameServer {



    public static void main(String[] args) throws IOException {

        Game game = new Game();

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Connected! Waiting for players to join...");

        while (true){

            Socket socket = serverSocket.accept();
            GameService gameService = new GameService(game, socket);
            TomcatServer tomcatServer = new TomcatServer();
            new Thread(gameService).start();
            new Thread(tomcatServer).start();

        }




    }


}
