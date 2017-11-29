package assignment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mariooliveira on 01/11/2017.
 */

public class Player implements Comparable<Player>{

    String name;
    ArrayList<Integer> shares;
    ArrayList<String> votedStock;
    int money;
    int transactions;
    int votes;

    /**
     *
     * @param name String - Name of the Player
     */
    Player(String name){

        this.name = name;
        money = 500;
        transactions = 2;
        votes = 2;

        allocateShares();
        votedStock = new ArrayList<>();

    }

    /**
     * Randomly allocates 10 shares between 5 stocks
     */
    private void allocateShares(){

        shares = new ArrayList<>();
        Random rdm = new Random();
        int numberOfShares = 10;

        for (int i = 0; i < 4; i++) {
            // Random absolute value between 0 and number of available shares
            int share = Math.abs(rdm.nextInt(numberOfShares));
            shares.add(i, share);
            numberOfShares = numberOfShares-share;

            //Allocate all the remaining shares to the last stock if there are any shares left
            if(i == 3 && numberOfShares > 0){
                shares.add(i, numberOfShares);
            }
        }
    }


    public int getMoney() {
        return money;
    }

    public int getTotalCash(){

        int cash = getMoney();
        int i = 0;

        for (int share: shares) {

            cash = cash + Game.stockArrayList.get(i).getPrice() * share;
        }
        return cash;
    }

    public ArrayList<Integer> showShares() {
        return shares;
    }

    public int getShares(int index) {
        return shares.get(index);
    }

    public void setVotedStock(String stock){
        votedStock.add(stock.toLowerCase());

        System.out.println();
    }




    public void resetPlayer(){
        votes = 2;
        votedStock.clear();
    }



    public boolean hasVotedForStock(String stock){

        //System.out.println(stock);

       return votedStock.contains(stock.toLowerCase());
    }

    public boolean hasVotes(){

        return votes > 0;
    }

    @Override
    public String toString() {
        return ("Player " + name + ": Cash = " + getMoney() + ", Shares = " + shares);
    }

    public static void main(String[] args) {

        Player test = new Player("Mario");

        System.out.println(test.toString());

    }

    @Override
    public int compareTo(Player o) {
        if(getTotalCash() > o.getTotalCash()){
            return -1;
        }
        else if(getTotalCash() < o.getTotalCash()) {
            return 1;
        }
        return 0;
    }
}
