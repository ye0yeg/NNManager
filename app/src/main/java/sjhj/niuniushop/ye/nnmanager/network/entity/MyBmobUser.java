package sjhj.niuniushop.ye.nnmanager.network.entity;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/10/11.
 */

public class MyBmobUser extends BmobObject {

    private Integer id;
    private String objId;
    private String name;
    private String rank_name;
    private Integer rank_level;
    private OrderNum orderNum;

    //余额
    @Override
    public String toString() {
        return "MyBmobUser{" +
                "id=" + id +
                ", objId='" + objId + '\'' +
                ", name='" + name + '\'' +
                ", rank_name='" + rank_name + '\'' +
                ", rank_level=" + rank_level +
                ", orderNum=" + orderNum +
                ", remain_money=" + remain_money +
                ", chargeHistroys=" + chargeHistroys +
                ", moneyUsingHistories=" + moneyUsingHistories +
                ", offLine=" + offLine +
                ", RecomNumber='" + RecomNumber + '\'' +
                '}';
    }

    private Double remain_money;

    //充值历史
    private ArrayList<ChargeHistroy> chargeHistroys;

    //使用历史

    private ArrayList<MoneyUsingHistory> moneyUsingHistories;

    private ArrayList<String> offLine;

    public String getRecomNumber() {
        return RecomNumber;
    }

    public void setRecomNumber(String recomNumber) {
        RecomNumber = recomNumber;
    }

    private String RecomNumber;


    public Double getRemain_money() {
        return remain_money;
    }

    public void setRemain_money(Double remain_money) {
        this.remain_money = remain_money;
    }

    public ArrayList<ChargeHistroy> getChargeHistroys() {
        return chargeHistroys;
    }

    public void setChargeHistroys(ArrayList<ChargeHistroy> chargeHistroys) {
        this.chargeHistroys = chargeHistroys;
    }

    public ArrayList<MoneyUsingHistory> getMoneyUsingHistories() {
        return moneyUsingHistories;
    }

    public void setMoneyUsingHistories(ArrayList<MoneyUsingHistory> moneyUsingHistories) {
        this.moneyUsingHistories = moneyUsingHistories;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public Integer getRank_level() {
        return rank_level;
    }

    public void setRank_level(Integer rank_level) {
        this.rank_level = rank_level;
    }


    public OrderNum getOrderNum() {
        return orderNum;
    }


    public void setOrderNum(OrderNum orderNum) {
        this.orderNum = orderNum;
    }

    public ArrayList<String> getOffLine() {
        return offLine;
    }

    public void setOffLine(ArrayList<String> offLine) {
        this.offLine = offLine;
    }


    public static class OrderNum {
        private Integer await_pay;
        private Integer await_ship;
        private Integer shipped;
        private Integer finished;

        public Integer getAwait_pay() {
            return await_pay;
        }

        public void setAwait_pay(Integer await_pay) {
            this.await_pay = await_pay;
        }

        public Integer getAwait_ship() {
            return await_ship;
        }

        public void setAwait_ship(Integer await_ship) {
            this.await_ship = await_ship;
        }

        public Integer getShipped() {
            return shipped;
        }

        public void setShipped(Integer shipped) {
            this.shipped = shipped;
        }

        public Integer getFinished() {
            return finished;
        }

        public void setFinished(Integer finished) {
            this.finished = finished;
        }

    }

    public class ChargeHistroy {

    }

    public class MoneyUsingHistory {

    }
}
