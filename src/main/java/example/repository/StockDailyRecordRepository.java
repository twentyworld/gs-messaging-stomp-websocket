package example.repository;

import example.entity.StockDailyRecord;
import org.springframework.data.repository.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by temper on 2017/8/9,下午4:37.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public interface StockDailyRecordRepository extends Repository<StockDailyRecord,Long>{
    StockDailyRecord save(StockDailyRecord stockDailyRecord);
    List<StockDailyRecord> findAll();
    List<StockDailyRecord> findBySymbol(String symbol);
    List<StockDailyRecord> findBySymboAndTimes(String symbol, Timestamp times);
    List<StockDailyRecord> findByTimes(Timestamp times);

}
