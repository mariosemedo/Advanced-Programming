package assignment;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mariooliveira on 01/11/2017.
 */

public class Player {

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


    private void allocateShares(){

        shares = new ArrayList<>();
        Random rdm = new Random();
        int numberOfShares = 10;

        for (int i = 0; i < 4; i++) {
            int share = Math.abs(rdm.nextInt(numberOfShares));
            shares.add(i, share);
            numberOfShares = numberOfShares-share;
            //System.out.println(numberOfShares + " " +  share);

            if(i == 3 && numberOfShares > 0){
                shares.add(i, numberOfShares);
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public ArrayList<Integer> showShares() {
        return shares;
    }

    public int getShares(int index) {
        return shares.get(index);
    }

    public void setVotedStock(String stock){
        votedStock.add(stock.toLowerCase());
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

}
