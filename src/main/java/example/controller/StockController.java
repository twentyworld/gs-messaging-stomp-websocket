package example.controller;

import example.entity.StockDailyRecord;
import example.service.StockDailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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





}