package example.service;

import example.entity.Record;
import example.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teemper on 2017/8/8, 20:05.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    public List<Record> getAllRecord(){
        return recordRepository.findAll();
    }

    public List<Record> getRecordByName(String symbol){
        return recordRepository.findBySymbol(symbol);
    }
}
