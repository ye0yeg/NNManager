package sjhj.niuniushop.ye.nnmanager.network.entity;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/11/10.
 */

public class MyBmobPayment extends BmobObject {
    private String ordernumber;

    private String address;

    private ArrayList<MyBmobCarGoods> goodslist;

    private String name;

    private String buyer;

    private Boolean isPay;

    private GoodState mGoodState;

    private String PayResult;

    private String goodTitle;

    private Double goodTotal;

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    private String creatTime;

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public Double getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(Double goodTotal) {
        this.goodTotal = goodTotal;
    }

    public String getPayResult() {
        return PayResult;
    }

    public void setPayResult(String payResult) {
        PayResult = payResult;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
    }

    public GoodState getGoodState() {
        return mGoodState;
    }

    public void setGoodState(GoodState goodState) {
        mGoodState = goodState;
    }

    public static class GoodState {

        public Boolean isPaying;
        public Integer isPayingCount;

        public Boolean isShipping;
        public Integer isShippingCount;

        public Boolean isDeliery;
        public Integer isDelieryCount;

        public Boolean getPaying() {
            return isPaying;
        }

        public void setPaying(Boolean paying) {
            isPaying = paying;
        }

        public Integer getIsPayingCount() {
            return isPayingCount;
        }

        public void setIsPayingCount(Integer isPayingCount) {
            this.isPayingCount = isPayingCount;
        }

        public Boolean getShipping() {
            return isShipping;
        }

        public void setShipping(Boolean shipping) {
            isShipping = shipping;
        }

        public Integer getIsShippingCount() {
            return isShippingCount;
        }

        public void setIsShippingCount(Integer isShippingCount) {
            this.isShippingCount = isShippingCount;
        }

        public Boolean getDeliery() {
            return isDeliery;
        }

        public void setDeliery(Boolean deliery) {
            isDeliery = deliery;
        }

        public Integer getIsDelieryCount() {
            return isDelieryCount;
        }

        public void setIsDelieryCount(Integer isDelieryCount) {
            this.isDelieryCount = isDelieryCount;
        }
    }


    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<MyBmobCarGoods> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(ArrayList<MyBmobCarGoods> goodslist) {
        this.goodslist = goodslist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}
