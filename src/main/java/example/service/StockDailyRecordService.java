package example.service;

import example.entity.Record;
import example.entity.StockDailyRecord;
import example.entity.StocksFluctuationRange;
import example.repository.RecordRepository;
import example.repository.StockDailyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private RecordRepository recordRepository;

    public List<StockDailyRecord> getAllStockDailyRecord(){
        return stockDailyRecordRepository.findAll();
    }

    public List<StockDailyRecord> getStockDailyRecordBySymbol(String symbol){
        return stockDailyRecordRepository.findBySymbol(symbol);
    }

    public List<StockDailyRecord> getStockDailyRecordBySymbolAndTimes(String symbol, Timestamp timestamp){
        return stockDailyRecordRepository.findBySymbolAndTimes(symbol,timestamp);
    }

    public void AddStockDailyRecord(StockDailyRecord stockDailyRecord){
        stockDailyRecordRepository.save(stockDailyRecord);
    }

    public List<StockDailyRecord> getStockDailyRecordByTimes(Timestamp timestamp){
        return stockDailyRecordRepository.findByTimes(timestamp);
    }

    public List<StocksFluctuationRange> getStocksFluctuationRange(){
        List<StocksFluctuationRange> rangeList = new ArrayList<>();
        List<StockDailyRecord> list = getAllStockDailyRecord();
        for(StockDailyRecord stockDailyRecord:list){
            String Symbol = stockDailyRecord.getSymbol();
            List<Record> listRecord = recordRepository.findBySymbol(Symbol);
            //StocksFluctuationRange range = new StocksFluctuationRange();
            Record latestRecord  = new Record();
            latestRecord.setTimes(new Timestamp(1));
            for(int i = 0;i<listRecord.size();i++){
                if(listRecord.get(i).getTimes().getTime()>latestRecord.getTimes().getTime())
                    latestRecord = listRecord.get(i);
            }
            double range = (latestRecord.getPrice()-stockDailyRecord.getClosePrice())/stockDailyRecord.getClosePrice();
            if(latestRecord.getSymbol()!=null){
                DecimalFormat decimalFormat = new DecimalFormat("######0.0000");
                range = Double.parseDouble(decimalFormat.format(range).toString());
                rangeList.add(new StocksFluctuationRange(latestRecord.getSymbol(),range*100,
                        Double.parseDouble(decimalFormat.format(latestRecord.getPrice()).toString()), latestRecord.getQuantity()));

            }
        }
        Collections.sort(rangeList);
        return rangeList;

    }

}
