package example.controller;

import example.entity.Record;
import example.entity.StockDailyRecord;
import example.entity.StocksFluctuationRange;
import example.service.RecordService;
import example.service.StockDailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by temper on 2017/8/9,下午4:54.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
@Controller
public class StockController {

    @Autowired
    private StockDailyRecordService stockDailyRecordService;

    @Autowired
    private RecordService recordService;


    @RequestMapping("/getAllStockDailyRecord")
    public @ResponseBody List<StockDailyRecord> getAllStockDailyRecord(){
        return stockDailyRecordService.getAllStockDailyRecord();
    }

    @RequestMapping("/getAllStockDailyRecordBySymbol")
    public @ResponseBody List<StockDailyRecord> getStockDailyRecordBySymbol(HttpServletRequest request){
        String symbol = request.getParameter("symbol");
        return stockDailyRecordService.getStockDailyRecordBySymbol(symbol);
    }


    @RequestMapping("/getAllStockDailyRecordBySymbolAndTimes")
    public @ResponseBody List<StockDailyRecord> getAllStockDailyRecordBySymbolAndTimes(HttpServletRequest request){
        String symbol = request.getParameter("symbol");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return stockDailyRecordService.getStockDailyRecordBySymbolAndTimes(symbol,timestamp);
    }

    @RequestMapping("/getAllStockDailyRecordByTimes")
    public @ResponseBody List<StockDailyRecord> getStockDailyRecordByTimes(HttpServletRequest request){
        long timeMills = Long.parseLong(request.getParameter("symbol"));
        Timestamp times = new Timestamp(timeMills);
        return stockDailyRecordService.getStockDailyRecordByTimes(times);
    }

    @RequestMapping("/getFluctuationRange")
    public @ResponseBody List<StocksFluctuationRange> getFluctuationRange(){
        List<StocksFluctuationRange> rangeList = new ArrayList<>();
        List<StockDailyRecord> list = getAllStockDailyRecord();
        for(StockDailyRecord stockDailyRecord:list){
            String Symbol = stockDailyRecord.getSymbol();
            List<Record> listRecord = recordService.getRecordByName(Symbol);
            //StocksFluctuationRange range = new StocksFluctuationRange();
            Record latestRecord  = new Record();
            latestRecord.setTimes(new Timestamp(1));
            for(int i = 0;i<listRecord.size();i++){
                if(listRecord.get(i).getTimes().getTime()>latestRecord.getTimes().getTime())
                    latestRecord = listRecord.get(i);
            }
            double range = (latestRecord.getPrice()-stockDailyRecord.getClosePrice())/stockDailyRecord.getClosePrice();
            if(latestRecord.getSymbol()!=null)
                rangeList.add(new StocksFluctuationRange(latestRecord.getSymbol(),range,latestRecord.getPrice(),latestRecord.getQuantity()));
        }
        return rangeList;
    }
}
