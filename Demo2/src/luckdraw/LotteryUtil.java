package Demo2.src.luckdraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  抽奖算法工具类
 */
public class LotteryUtil {

    /**
     *  抽奖算法
     */
    public static int lottery(List<Double> originRates){

        double sumRate = 0d;
        for (double rate:originRates){
            sumRate += rate;
        }

        List<Double> sortOriginRates = new ArrayList<>();

        double tempSumRate = 0d;
        for (double rate:originRates){
            tempSumRate += rate;
            sortOriginRates.add(tempSumRate);
        }

        double nextDouble = Math.random();
        sortOriginRates.add(nextDouble);
        Collections.sort(sortOriginRates);
        return sortOriginRates.indexOf(nextDouble);
    }

}
