package example.service;

import example.entity.StockDailyRecord;
import example.repository.StockDailyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by temper on 2017/8/9,下午4:42.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Service
public class StockDailyRecordService {
    @Autowired
    private StockDailyRecordRepository stockDailyRecordRepository;

    public List<StockDailyRecord> getAllStockDailyRecord(){
        return stockDailyRecordRepository.findAll();
    }

    public List<StockDailyRecord> getStockDailyRecordBySymbol(String symbol){
        return stockDailyRecordRepository.findBySymbol(symbol);
    }

    public List<StockDailyRecord> getStockDailyRecordBySymbolAndTimes(String symbol, Timestamp timestamp){
        return stockDailyRecordRepository.findBySymboAndTimes(symbol,timestamp);
    }

    public void AddStockDailyRecord(StockDailyRecord stockDailyRecord){
        stockDailyRecordRepository.save(stockDailyRecord);
    }

    public List<StockDailyRecord> getStockDailyRecordByTimes(Timestamp timestamp){
        return stockDailyRecordRepository.findByTimes(timestamp);
    }

}
