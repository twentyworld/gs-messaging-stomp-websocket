package example.controller;

import com.sun.org.apache.regexp.internal.RE;
import example.entity.Record;
import example.service.OrderService;
import example.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by teemper on 2017/8/8, 20:29.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Controller
public class RecordController {
    @Autowired
    RecordService recordService;

    @RequestMapping("/allRecord")
    public @ResponseBody List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }
    @RequestMapping("/getRecordBySymbol")
    public @ResponseBody List<Record> getRecordBySymbol(HttpServletRequest request){
        String symbol = request.getParameter("symbol");
        return recordService.getRecordByName(symbol);
    }

}
