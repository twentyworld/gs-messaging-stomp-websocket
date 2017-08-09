package example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by teemper on 2017/8/9, 23:39.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Entity
public class IndexOfBroker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String place;
    private Timestamp times;
    private double openPrice;
    private double HighestPrice;
    private double lowestPrice;
    private double totalQuantity;
    private double rangeOfPercent;

    public IndexOfBroker(String place, Timestamp times, double openPrice, double highestPrice, double lowestPrice, double totalQuantity, double rangeOfPercent) {
        this.place = place;
        this.times = times;
        this.openPrice = openPrice;
        HighestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.totalQuantity = totalQuantity;
        this.rangeOfPercent = rangeOfPercent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighestPrice() {
        return HighestPrice;
    }

    public void setHighestPrice(double highestPrice) {
        HighestPrice = highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(double lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getRangeOfPercent() {
        return rangeOfPercent;
    }

    public void setRangeOfPercent(double rangeOfPercent) {
        this.rangeOfPercent = rangeOfPercent;
    }

    public IndexOfBroker() {
    }
}
