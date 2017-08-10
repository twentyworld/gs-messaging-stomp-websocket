package example.entity;

/**
 * Created by temper on 2017/8/10,上午11:43.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public class SubmitOrder {

    private long id;
    private int isBuy;
    private double price;
    private int quantity;
    private String symbol;
    private String strategy;

    public SubmitOrder(long id, int isBuy, double price, int quantity, String symbol, String strategy) {
        this.id = id;
        this.isBuy = isBuy;
        this.price = price;
        this.quantity = quantity;
        this.symbol = symbol;
        this.strategy = strategy;
    }

    public SubmitOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
