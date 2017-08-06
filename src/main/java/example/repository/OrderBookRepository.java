package example.repository;


//import com.example.training.entity.OrderBook;
import example.entity.OrderBook;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by temper on 2017/8/6,下午4:55.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public interface OrderBookRepository extends Repository<OrderBook,Long> {
    OrderBook save(OrderBook orderBook);
    List<OrderBook> findAll();
}
