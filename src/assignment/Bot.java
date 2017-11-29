package assignment;

import java.util.Map;
import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by mariooliveira on 29/11/2017.
 */
public class Bot extends Player{


    Bot(String name){
        super(name);

    }

    public void castVote(){

        Random rn = new Random();
        int stock = rn.nextInt(5);

        Game.vote(Game.stockArrayList.get(stock), "YES", this);

        System.out.println(name + " voted for " + Game.stockArrayList.get(stock).name + ".");
        System.out.println(hasVotes());
    }

    public void buy(){


        Random rn = new Random();
        int stock = rn.nextInt(5);
        int moneyAvailable = getMoney();
        int currentShares = getShares(stock);
        int stockPriceAndFee = Game.stockArrayList.get(stock).getPrice() + 3;
        int sharesToBuy = moneyAvailable / stockPriceAndFee ;

        if(moneyAvailable - sharesToBuy * Game.stockArrayList.get(stock).getPrice() + 3 >= 0){
            money = moneyAvailable - 3 - sharesToBuy * Game.stockArrayList.get(stock).getPrice();
            shares.set(stock, sharesToBuy + currentShares);
            }

    }


    public void sell() {

        if (transactions > 0) {

            Random rn = new Random();
            Random rn2 = new Random();
            int stock = rn.nextInt(5);
            //System.out.println(name+" "+ stock);
            int sharesAvaliable = getShares(stock);
            if (sharesAvaliable > 0) {
                int sharesToSell = 1;
                if (sharesAvaliable >= sharesToSell) {
                    super.shares.set(stock, sharesAvaliable - sharesToSell);
                    super.money = getMoney() + sharesToSell * Game.stockArrayList.get(stock).getPrice();
                }
            }
        }
    }


}
