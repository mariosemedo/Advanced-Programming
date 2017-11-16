package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;


/**
 * Created by mariooliveira on 01/11/2017.
 */
public class Stock {

    int price;
    String name;
    ArrayList <Integer> deckOfCards;
    int totalNumberOfVotes;


    Stock(String name, int price){

        this.name = name;
        this.price = price;
        totalNumberOfVotes = 0;

        setInfluenceCards();
    }

    private void setInfluenceCards(){
        deckOfCards = new ArrayList<>();

        deckOfCards.add(-20);
        deckOfCards.add(-10);
        deckOfCards.add(-5);
        deckOfCards.add(5);
        deckOfCards.add(10);
        deckOfCards.add(20);

        Collections.shuffle(deckOfCards);
    }

    public void deleteTopCard(){
        deckOfCards.remove(0);
        //trim

    }

    public int getTopCard(){
        return deckOfCards.get(0);
    }

    public int getTotalNumberOfVotes(){

        return totalNumberOfVotes;
    }

    public int vote(String vote){

        vote = vote.toUpperCase();

        switch (vote){
            case "YES": {
                return totalNumberOfVotes = totalNumberOfVotes + 1;
            }
            case "NO":{
                return totalNumberOfVotes = totalNumberOfVotes - 1;
            }
            default: {
                throw new InputMismatchException();
            }
        }

    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Integer> getDeckOfCards() {
        return deckOfCards;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {

        Stock stock = new Stock("BP",100);

        System.out.println(stock.deckOfCards);

        stock.deleteTopCard();

        System.out.println(stock.deckOfCards);



    }

}
