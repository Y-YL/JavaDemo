package Demo2.src.luckdraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽奖算法测试类
 */
public class LotteryTest {

    public static void main(String[] args) {

        List<Prize> prizes = new ArrayList<>();
        // 奖品序号 - 奖品ID - 奖品名称 - 库存 - 概率
        prizes.add(new Prize(2580, 11L, "再抽一次", -1, 0.2d));
        prizes.add(new Prize(1234, 22L, "腾讯QQ会员", 20, 0.1d));
        prizes.add(new Prize(5678, 33L, "谢谢参与", -1, 0.4d));
        prizes.add(new Prize(1230, 55L, "100Q币", 0, 0.3d));
        prizes.add(new Prize(3726, 66L, "Iphone12", 0, 0d));
        prizes.add(new Prize(3737, 77L, "Ipad Air8", 0, -0.1d));
        prizes.add(new Prize(2568, 88L, "100元手机话费", 5, 0.008d));

        if (prizes.isEmpty()){
            System.out.println("无奖品数据");
            return;
        }
        List<Double> originRates = new ArrayList<>();
        for (Prize prize:prizes){
            double probability = prize.getProbability();
            int stock = prize.getStock();
            if (probability < 0 || stock == 0){
                probability = 0;
            }
            originRates.add(probability);
        }

        for (int i=0;i<1000;i++){
            int originIndex = LotteryUtil.lottery(originRates);
            Prize prize = prizes.get(originIndex);
            if (prize.getProbability() <=0 || prize.getStock() == 0){
                System.out.println("库存不足或概率小于等于0 ========谢谢参与=========");
            }else{
                System.out.println(prize);
            }
        }

    }


}
