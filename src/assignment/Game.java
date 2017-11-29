package assignment;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by mariooliveira on 01/11/2017.
 */

public class Game {

    static Stock apple, bp, cisco, dell, ericsson;
    static ArrayList <Stock> stockArrayList;
    static ArrayList <Player> connectedPlayers;
    // Player player;
    int numberOfPlayers;
    final static CyclicBarrier barrier = new CyclicBarrier(2);
    static BotClient botClient = new BotClient();
    static int botID = 0;
    static String firstPlayer = "";


    Game(){

        apple = new Stock("Apple", 100);
        bp = new Stock("BP", 100);
        cisco = new Stock("Cisco", 100);
        dell = new Stock("Dell", 100);
        ericsson = new Stock("Ericsson", 100);
        connectedPlayers = new ArrayList<>();

        getShares();

        stockArrayList = new ArrayList();
        stockArrayList.add(apple);
        stockArrayList.add(bp);
        stockArrayList.add(cisco);
        stockArrayList.add(dell);
        stockArrayList.add(ericsson);
    }

    public ArrayList getShares(){

        ArrayList<Integer> currentSharePrices = new ArrayList<>();

        currentSharePrices.add(apple.getPrice());
        currentSharePrices.add(bp.getPrice());
        currentSharePrices.add(cisco.getPrice());
        currentSharePrices.add(dell.getPrice());
        currentSharePrices.add(ericsson.getPrice());

        return currentSharePrices;
    }

    public Player getWinner(){

        System.out.println(connectedPlayers);

        Collections.sort(connectedPlayers);

        System.out.println(connectedPlayers);

        return connectedPlayers.get(0);
    }


    public ArrayList getInfluenceCards(){

        ArrayList<Integer> influenceCards = new ArrayList<>();

        influenceCards.add(apple.getTopCard());
        influenceCards.add(bp.getTopCard());
        influenceCards.add(cisco.getTopCard());
        influenceCards.add(dell.getTopCard());
        influenceCards.add(ericsson.getTopCard());

        return influenceCards;
    }

    public void useInfluenceCards() {

        Iterator<Stock> it = stockArrayList.iterator();
        int i = 0;

        while (it.hasNext()) {
            Stock stock = it.next();

            int votes = stock.totalNumberOfVotes;

            if (votes > 0) {
                stock.price = stock.price + stock.getTopCard();
                stock.deleteTopCard();
            } else if (votes < 0) {
                stock.deleteTopCard();
            }

            stock.totalNumberOfVotes = 0;// resets stock vote count
            stockArrayList.set(i,stock);
            i++;
        }
    }

    public ArrayList<Player> getConnectedPlayers (){

        return connectedPlayers;
    }



    public static void vote(Stock stock, String vote, Player player) {

        if (player.hasVotes() && !player.votedStock.contains(stock.name.toLowerCase())) {

            stock.vote(vote);
            player.setVotedStock(stock.name); // adds stock to the list of voted stock for this round
            player.votes = player.votes - 1; // takes one vote from player

        }
    }

    public static void botVote(Stock stock, int vote){


}

    public static void playBots(){

        Random rn = new Random();
        int choice = rn.nextInt(3);

        switch (choice){
            case 0:
                botClient.castVote();
            case 1:
                botClient.buy();
            case 2:
                botClient.sell();
        }
    }

    public static void resetBots()
    {
        botClient.reset();
    }
    public static void main(String[] args) {

        Game game = new Game();

        Scanner sc = new Scanner(System.in);

        game.apple.vote("yes");
        game.useInfluenceCards();
        System.out.println("Share prices: " + game.getShares());
        System.out.println("Influence cards: " + game.getInfluenceCards());

        //System.out.println(game.apple.getDeckOfCards());
       // System.out.println(game.player.showShares());
        //game.sellShares(game.apple, 1, 3);
        //game.buyShares(game.apple, 1, 3);

/*
        // voting menu
        while (game.player.hasVotes()) {
            System.out.println("Please cast your vote for [1]Apple, [2]BP, [3]Cisco, [4]Dell, [5]Ericsson ");
            int chosenStock = sc.nextInt(); // stock selection

            switch (chosenStock) {
                case 1: {
                    // check if voted casted
                    if(game.player.hasVotedForStock(game.apple.name)) {

                        System.out.println("You've already casted your vote for this Stock!");
                        break;
                    }
                    // otherwise allow to place vote
                    else {
                        System.out.println("Please vote YES or NO for Apple");
                        game.apple.vote(sc.next());
                        game.useInfluenceCards(game.apple);
                        System.out.println(game.player.votes);
                        break;
                        //System.out.println(game.apple.getPrice());
                        //System.out.println(game.apple.getDeckOfCards());
                        //System.out.println(game.apple.getTotalNumberOfVotes());
                    }
                }
                case 2: {

                    System.out.println("Please vote YES or NO for BP");
                    game.bp.vote(sc.next());
                    game.useInfluenceCards(game.bp);
                    break;

                }
                case 3: {
                    System.out.println("Please vote YES or NO for Cisco");
                    game.cisco.vote(sc.next());
                    game.useInfluenceCards(game.cisco);
                    break;

                }

                case 4: {
                    System.out.println("Please vote YES or NO for Dell");
                    game.dell.vote(sc.next());
                    game.useInfluenceCards(game.dell);
                    break;
                }

                case 5: {
                    System.out.println("Please vote YES or NO for Ericsson");
                    game.ericsson.vote(sc.next());
                    game.useInfluenceCards(game.ericsson);
                    break;
                }
            }
        }*/

    }

}
