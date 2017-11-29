package assignment;

import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by mariooliveira on 08/11/2017.
 */
public class GameServer {

    static Game game = new Game();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Connected! Waiting for players to join...");
        TomcatServer tomcatServer = new TomcatServer();
        new Thread(tomcatServer).start();
        while (true){
            Socket socket = serverSocket.accept();
            GameService gameService = new GameService(game, socket);
            new Thread(gameService).start();
        }

    }
}
