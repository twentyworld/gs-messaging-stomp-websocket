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
    private long tradeId;
    private  long traderId;
    private int isBuy;
    private String symbol;
    private double price;
    private int quantity;

    public RawOrder() {
    }

    public RawOrder(long traderId, int isBuy, String symbol, double price, int quantity) {
        this.traderId = traderId;
        this.isBuy = isBuy;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
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

    public long getTraderId() {
        return traderId;
    }

    public void setTraderId(long traderId) {
        this.traderId = traderId;
    }
}
