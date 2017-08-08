package example.repository;

import example.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by temper on 2017/8/8,下午2:49.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public interface RecordRepository extends JpaRepository<Record, Long> {

    //全部record
    public List<Record> findAll();
    //通过symbol查询。
    List<Record> findBySymbol();
    //通过指定的时间来查询。

    List<Record> findByTime(Timestamp time1,Timestamp time2);

    //add
    List<Record> save(Record record);


}
