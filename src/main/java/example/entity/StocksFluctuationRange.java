package example.entity;

/**
 * Created by temper on 2017/8/9,下午7:52.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public class StocksFluctuationRange {
    private String symbol;
    private double range;

    public StocksFluctuationRange() {
    }

    public StocksFluctuationRange(String symbol, double range) {
        this.symbol = symbol;
        this.range = range;
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
