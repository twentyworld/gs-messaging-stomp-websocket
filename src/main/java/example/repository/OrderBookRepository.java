package example.repository;

import example.entity.RawOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by teemper on 2017/8/8, 20:42.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
public interface OrderBookRepository extends JpaRepository<RawOrder,Long> {

    RawOrder save(RawOrder bidOrder);
    List<RawOrder> findAll();
    void delete(RawOrder order);

    List<RawOrder> findBySymbol(String symbol);
    RawOrder findByTraderId(long id);

}
