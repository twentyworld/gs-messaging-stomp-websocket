package example.service;

/**
 * Created by temper on 2017/8/7,下午4:29.
 * copy as you like, but with these word.
 * at last, The forza horizon is really fun, buy is made, looking forward to driving together in the hurricane.
 */
public class IOrderServiceFactory {
    private IOrderService orderService;

    public static IOrderService getOrderService(String self){
        if(self.equals(FOKOrderService.printSelf()))
            return new FOKOrderService();
        else return null;
    }
}
