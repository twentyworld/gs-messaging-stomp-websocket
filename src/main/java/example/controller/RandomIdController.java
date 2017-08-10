package example.controller;

import example.entity.UserId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by teemper on 2017/8/10, 2:29.
 *
 * @auther Zed.
 * copy as you like, but with these words.
 * from win.
 */
@Controller
public class RandomIdController {

    @RequestMapping("/getRandomId")
    public @ResponseBody UserId getRandomId(){
        return new UserId( new Random().nextLong());
    }
}
