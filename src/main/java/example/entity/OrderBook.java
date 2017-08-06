package example.entity;

/**
 * Created by temper on 2017/8/6,下午4:27.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String Symbol;
    private double offerPrice;
    private int offerQuantity;
    private double bidPrice;
    private int bidQuantity;


    public OrderBook() {
    }

    public OrderBook(String symbol, double offerPrice, int offerQuantity, double bidPrice, int bidQuantity) {
        //this.id = id;
        Symbol = symbol;
        this.offerPrice = offerPrice;
        this.offerQuantity = offerQuantity;
        this.bidPrice = bidPrice;
        this.bidQuantity = bidQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getOfferQuantity() {
        return offerQuantity;
    }

    public void setOfferQuantity(int offerQuantity) {
        this.offerQuantity = offerQuantity;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(int bidQuantity) {
        this.bidQuantity = bidQuantity;
    }
}
