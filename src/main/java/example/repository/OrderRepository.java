package example.repository;

import example.entity.RawOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by temper on 2017/8/7,上午10:52.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public interface OrderRepository extends JpaRepository<RawOrder,Long> {

    RawOrder save(RawOrder bidOrder);
    List<RawOrder> findAll();
    void delete(RawOrder order);

    List<RawOrder> findBySymbol(String symbol);
    RawOrder findByTraderId(long id);

}
