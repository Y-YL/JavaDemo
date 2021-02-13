package Demo2.src.luckdraw;

public class Prize {
    /*
        奖品序号
     */
    private  Integer prezeIndex;

    /*
        奖品ID
     */
    private Long prizeId;

    /*
        奖品名称
     */
    private String prizeName;

    /*
        库存
     */
    private Integer stock;

    /*
        概率
     */
    private double probability;

    public Prize() {
    }

    public Prize(Integer prezeIndex, Long prizeId, String prizeName, Integer stock, double probability) {
        this.prezeIndex = prezeIndex;
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.stock = stock;
        this.probability = probability;
    }

    public Integer getPrezeIndex() {
        return prezeIndex;
    }

    public void setPrezeIndex(Integer prezeIndex) {
        this.prezeIndex = prezeIndex;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "prezeIndex=" + prezeIndex +
                ", prizeId=" + prizeId +
                ", prizeName='" + prizeName + '\'' +
                ", stock=" + stock +
                ", probability=" + probability +
                '}';
    }
}
