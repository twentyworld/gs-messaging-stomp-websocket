package example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by temper on 2017/8/6,下午4:27.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Entity
public class Trades {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private Timestamp when ;
    private String Symbol;
    private boolean isBuy;
    private double price;
    private int quantity;

    public Trades() {
    }

    public Trades(long id, Timestamp when, String symbol, boolean isBuy, double price, int quantity) {
        this.id = id;
        this.when = when;
        Symbol = symbol;
        this.isBuy = isBuy;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getWhen() {
        return when;
    }

    public void setWhen(Timestamp when) {
        this.when = when;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
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
}
