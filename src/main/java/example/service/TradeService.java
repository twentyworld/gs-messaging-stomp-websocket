package example.service;

import example.entity.RawOrder;
import example.repository.OrderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * Created by temper on 2017/8/7,上午11:15.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */

@Service
public class TradeService {

    @Autowired
    private OrderBookRepository orderBookRepository;

    public List<RawOrder> BidSort(RawOrder order){
        List<RawOrder> bids = orderBookRepository.findBySymbol("order");
        return bids;
    }

    public List<RawOrder> init(){
        //BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/temperlee/Desktop")));
        File file = new File("/Users/temperlee/Desktop/tradedata.csv");
        try {
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine())!= null){
                String[] lines = line.split(",");
                int isBuy = lines[1].equals("B")?1:0;
                float price = Float.parseFloat(lines[2].trim());
                int quantity = Integer.parseInt(lines[3].trim());
                RawOrder order = new RawOrder(isBuy,lines[0],price,quantity);
                orderBookRepository.save(order);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return orderBookRepository.findAll();
    }


}
