package example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by temper on 2017/8/7,上午10:47.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Entity
public class RawOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long trader_id;
    private int isBuy;
    private String Symbol;
    private double price;
    private int quantity;

    public RawOrder() {
    }

    public RawOrder(int isBuy, String symbol, double price, int quantity) {
        this.isBuy = isBuy;
        Symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
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

    public long getTrader_id() {
        return trader_id;
    }

    public void setTrader_id(long trader_id) {
        this.trader_id = trader_id;
    }
}
