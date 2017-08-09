package example.service;

import example.entity.RawOrder;
import example.entity.Record;
import example.entity.StockDailyRecord;
import example.repository.OrderBookRepository;
import example.repository.RecordRepository;
import example.repository.StockDailyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by temper on 2017/8/7,上午11:15.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */

@Service
public class TradeService {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private OrderBookRepository orderBookRepository;
    @Autowired
    private StockDailyRecordRepository stockDailyRecordRepository;

    public List<RawOrder> BidSort(RawOrder order){
        List<RawOrder> bids = orderBookRepository.findBySymbol("order");
        return bids;
    }

    public List<RawOrder> init(){
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/temperlee/Desktop")));
        File file = new File("C:\\Users\\w\\Desktop\\tradedata.txt");
        try {
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            int lineNum=0;
            while((line = bufferedReader.readLine())!= null){
                lineNum++;
                String[] lines = line.split("\t");
                int isBuy = lines[1].equals("B")?1:0;
                float price = Float.parseFloat(lines[2].trim());
                int quantity = Integer.parseInt(lines[3].trim());
                RawOrder order = new RawOrder(lineNum,isBuy,lines[0],price,quantity);

                System.out.println(order);
                orderBookRepository.save(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,22,23,0),123.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,23,24,0),124.2,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,24,25,0),124.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,26,26,0),125.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,29,27,0),124.3,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,31,28,0),125.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,32,29,0),125.7,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,33,30,0),126.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,34,31,0),124.7,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,35,32,0),127.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,37,33,0),125.9,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,38,34,0),128.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,40,35,0),126.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,42,36,0),129.3,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,45,37,0),128.2,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,47,38,0),128.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,51,39,0),128.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,9,57,40,0),127.9,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,7,23,0),129.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,12,24,0),126.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,15,25,0),128.7,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,19,26,0),125.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,22,27,0),127.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,22,28,0),125.3,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,24,29,0),124.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,27,30,0),123.9,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,29,31,0),123.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,32,32,0),123.2,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,34,33,0),123.1,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,37,34,0),124.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,41,35,0),125.1,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,43,36,0),124.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,47,37,0),125.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,49,38,0),124.9,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,51,39,0),123.5,40,"ABT","FOK"));
        recordRepository.save(new Record(234567,345678,new Timestamp(117,8,7,10,53,40,0),124.3,40,"ABT","FOK"));



        stockDailyRecordRepository.save(new StockDailyRecord("ABT",new Timestamp(117,8,10,18,0,0,0),123.5,123.5));
        stockDailyRecordRepository.save(new StockDailyRecord("ABBV",new Timestamp(117,8,10,18,0,0,0),123.5,25.1200008392334));
        stockDailyRecordRepository.save(new StockDailyRecord("ACN",new Timestamp(117,8,10,18,0,0,0),123.5,35.010001373291016));
        stockDailyRecordRepository.save(new StockDailyRecord("ACE",new Timestamp(117,8,10,18,0,0,0),123.5,52.539998626708984));
        stockDailyRecordRepository.save(new StockDailyRecord("ADBE",new Timestamp(117,8,10,18,0,0,0),123.5,63.059999084472656));
        stockDailyRecordRepository.save(new StockDailyRecord("ADT",new Timestamp(117,8,10,18,0,0,0),123.5,39.060001373291016));
        stockDailyRecordRepository.save(new StockDailyRecord("DAL",new Timestamp(117,8,10,18,0,0,0),123.5,46.89000015258789));
        stockDailyRecordRepository.save(new StockDailyRecord("XRAY",new Timestamp(117,8,10,18,0,0,0),123.5,7.059999847412109));
        stockDailyRecordRepository.save(new StockDailyRecord("DVN",new Timestamp(117,8,10,18,0,0,0),123.5,48.22999954223633));
        stockDailyRecordRepository.save(new StockDailyRecord("DO",new Timestamp(117,8,10,18,0,0,0),123.5,61.05999984741211));
        stockDailyRecordRepository.save(new StockDailyRecord("INTC",new Timestamp(117,8,10,18,0,0,0),123.5,37.020001220703125));
        stockDailyRecordRepository.save(new StockDailyRecord("ICE",new Timestamp(117,8,10,18,0,0,0),123.5,23.610000228881836));
        stockDailyRecordRepository.save(new StockDailyRecord("IBM",new Timestamp(117,8,10,18,0,0,0),123.5,55.70000076293945));
        stockDailyRecordRepository.save(new StockDailyRecord("IP",new Timestamp(117,8,10,18,0,0,0),123.5,57.95));
        stockDailyRecordRepository.save(new StockDailyRecord("NOC",new Timestamp(117,8,10,18,0,0,0),123.5,48.799998474121094));
        stockDailyRecordRepository.save(new StockDailyRecord("NRG",new Timestamp(117,8,10,18,0,0,0),123.5,28.909999084472656));
        stockDailyRecordRepository.save(new StockDailyRecord("NUE",new Timestamp(117,8,10,18,0,0,0),123.5,51.409999084472656));
        stockDailyRecordRepository.save(new StockDailyRecord("NVDA",new Timestamp(117,8,10,18,0,0,0),123.5,15.94000015258789));
        stockDailyRecordRepository.save(new StockDailyRecord("TSO",new Timestamp(117,8,10,18,0,0,0),123.5,6.299999809265137));
        stockDailyRecordRepository.save(new StockDailyRecord("TXN",new Timestamp(117,8,10,18,0,0,0),123.5,61.449998474121094));










        return orderBookRepository.findAll();
    }


}
