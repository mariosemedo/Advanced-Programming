package assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by mariooliveira on 08/11/2017.
 */
public class GameService implements Runnable {

    Player player;
    Boolean login;
    PrintWriter out;
    Scanner in;
    Game game;
    final static CyclicBarrier barrier = new CyclicBarrier(2);
    Boolean newRound;
    int round;



    GameService(Game game, Socket socket) {

        this.game = game;
        player = new Player("");
        login = false;
        newRound = false;
        round = 0;


        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void run() {

        login();

        waitForOtherPLayers(); // 1 minute timeout


    }


    public void login() {

        try {

            if (game.numberOfPlayers < 4) {

                out.println("Please enter your name: ");
                String input = in.nextLine().trim();
                player.name = input;
                game.playersConnected.add(player);

                /*

                out.println("Hello " + input);
                out.println("To sell shares enter \n[1] for Apple \n[2] for BP \n[3] for Cisco \n[4] for Dell \n[5] for Ericsson");
                login = true;

                buyShares(game.apple, in.nextInt(), 5);
                System.out.println(player.shares);*/
            } else {

                out.println("Maximum number of players reached!");

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void roundDisplay() {
        round++;
        out.println("ROUND " + round + "\n");
        Iterator<Stock> it = game.stockArrayList.iterator();
        Iterator<Player> it2 = game.playersConnected.iterator();

        if(round > 1){
            roundReset();
        }
        if (round > 5) {
            gameOver();
        }else {


            while (it.hasNext()) {
                Stock stock = it.next();

                out.println(stock.name + "'s current share price is [" + stock.getPrice() + "]  Influence " + stock.getTopCard());
            }
            while (it2.hasNext()) {
                Player player = it2.next();
                out.println();
                out.println(player);
            }

            roundInfo();
        }

    }

    public void roundInfo() {

        String chooseInfo = "\nType in the following COMMANDS...\n\n" +
                "SELL to sell your shares to the stock market\n" +
                "BUY to buy shares of current stocks\n" +
                "VOTE to cast your vote in favour or against an influence card\n" +
                "READY to move to the next round\n";

        out.println(chooseInfo);

        String nextInput = in.next();
        setCommand(nextInput);
        out.println();
    }

    public void roundReset(){

        game.useInfluenceCards();
        player.resetPlayer();
    }

    public void gameOver(){

        // CALL WINNER METHOD

        try {
            out.println("END");
            Thread.sleep(1000);
            in.close();
            out.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public boolean sellShares(Stock stock, int stockId, int sharesToSell) {

        if (player.transactions > 0) {

            int sharesAvaliable = player.getShares(stockId - 1);

            if (sharesAvaliable >= sharesToSell) {

                player.shares.set(stockId - 1, sharesAvaliable - sharesToSell);
                player.money = player.getMoney() + sharesToSell * stock.getPrice();
                out.println("Shares available for " + stock.name + ":" + player.getShares(stockId - 1));
                out.println("Current Money: " + player.getMoney());
                player.transactions = player.transactions - 1;

                return true;
            } else {
                out.println("Insufficient shares. \nYou have " + sharesAvaliable + " shares available for " + stock.name + ".");
            }
        } else {

            out.println("You have used your 2 votes for this round!");

        }
        return false;

    }


    public void buyShares(Stock stock, int stockId, int sharesToBuy) {

        if (player.transactions > 0) {

            int moneyAvailable = player.getMoney();
            int currentShares = player.getShares(stockId - 1);

            if (moneyAvailable - sharesToBuy * stock.getPrice() + 3 >= 0) {

                player.money = moneyAvailable - 3 - sharesToBuy * stock.getPrice();
                player.shares.set(stockId - 1, sharesToBuy + currentShares);

                out.println("Shares available " + player.getShares(stockId - 1));
                out.println("Current Money: " + player.getMoney());


            } else {
                out.println("Insufficient funds to complete the transaction!\n Current money: " + moneyAvailable);
            }
        } else {
            out.println("You have used your 2 votes for this round!");
        }
    }

    public void vote(Stock stock, String vote) {

        if (player.hasVotes() && !player.votedStock.contains(stock.name.toLowerCase())) {

            stock.vote(vote);
            player.setVotedStock(stock.name); // adds stock to the list of voted stock for this round
            player.votes = player.votes - 1; // takes one vote from player

        }
    }


    public void setCommand(String command) {

        command = command.toUpperCase();
            switch (command) {
                case "SELL": {
                    trade(command);
                    break;
                }
                case "BUY": {
                    trade(command);
                    break;
                }
                case "VOTE": {
                    castVote();
                    break;
                }
                case "READY": {
                    waitForOtherPLayers();
                    break;
                }
                default:{
                    roundInfo();
                }
            }



    }


    public void waitForOtherPLayers() {

        try {
            barrier.await(1000, TimeUnit.SECONDS);
            roundDisplay();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


    public void trade(String command) {

        int choice;
        int numberOfShares = 0;
        out.println("\nPlease enter [1] for Apple || [2] for BP  || [3] for Cisco || [4] for Dell || [5] for Ericsson");
        choice = in.nextInt();

        if (command.equals("SELL")) {
            out.println("\nHow many shares would you like to sell?");
            numberOfShares = in.nextInt();
            sellShares(game.stockArrayList.get(choice - 1), choice, numberOfShares);
            roundInfo();
        } else if (command.equals("BUY")) {
            out.println("\nHow many shares would you like to buy?");
            numberOfShares = in.nextInt();
            buyShares(game.stockArrayList.get(choice - 1), choice, numberOfShares);
            roundInfo();
        }

    }


    public void castVote(){

        if(player.hasVotes()) {

            out.println("\nPlease enter to vote\n[1] for Apple \n[2] for BP \n[3] for Cisco \n[4] for Dell \n[5] for Ericsson\n");

            try {
                int stock = in.nextInt();

                if (stock >= 0 && stock <= game.stockArrayList.size()) {

                    if(!player.hasVotedForStock(game.stockArrayList.get(stock-1).name)) {
                        out.println("Please vote YES or NO for " + game.stockArrayList.get(stock - 1).name);
                        String choice = in.next();
                        vote(game.stockArrayList.get(stock - 1), choice);
                        roundInfo();
                    }
                    else {
                        out.println("YOU'VE CASTED YOUR VOTE FOR THIS STOCK! TRY AGAIN...");
                        roundInfo();
                    }
                } else {
                    out.println("STOCK DOES NOT EXIST! PLEASE TRY AGAIN...");
                    roundInfo();
                }

            } catch (InputMismatchException e) {
                e.printStackTrace();
                out.println("WRONG INPUT! PLEASE TRY AGAIN... ");
                roundInfo();
            }
        }
        else{
            out.println("YOU HAVE RAN OUT OF VOTES FOR THIS ROUND!");
        }
    }


}




