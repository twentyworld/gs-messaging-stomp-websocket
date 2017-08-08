package example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by temper on 2017/8/8,下午12:37.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long traderBid;
    private long traderOffer;
    private Timestamp times;
    private double price;
    private int quantity;
    private String symbol;
    private String type;


    public Record() {
    }

    public Record(long traderBid, long traderOffer, Timestamp times, double price, int quantity, String symbol, String type) {
        this.traderBid = traderBid;
        this.traderOffer = traderOffer;
        this.times = times;
        this.price = price;
        this.quantity = quantity;
        this.symbol = symbol;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTraderBid() {
        return traderBid;
    }

    public void setTraderBid(long traderBid) {
        this.traderBid = traderBid;
    }

    public long getTraderOffer() {
        return traderOffer;
    }

    public void setTraderOffer(long traderOffer) {
        this.traderOffer = traderOffer;
    }

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
