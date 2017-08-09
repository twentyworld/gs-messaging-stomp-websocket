package example.entity;

/**
 * Created by temper on 2017/8/9,下午7:52.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public class StocksFluctuationRange {
    private String symbol;
    private double range;
    private double latestPrice;
    private int quantity;

    public StocksFluctuationRange() {
    }

    public StocksFluctuationRange(String symbol, double range, double latestPrice, int quantity) {
        this.symbol = symbol;
        this.range = range;
        this.latestPrice = latestPrice;
        this.quantity = quantity;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
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

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
