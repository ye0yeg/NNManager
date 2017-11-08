package sjhj.niuniushop.ye.nnmanager.network.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/10/11.
 */

public class MyBmobCarGoods extends BmobObject {


    private Double single_price;

    private String name;

    private Integer rec_id;

    private Integer goods_id;

    private String goods_name;

    private Integer goods_number;

    private String img;

    private String subtotal;

    private CartBill total;

    public Double getSingle_price() {
        return single_price;
    }

    public void setSingle_price(Double single_price) {
        this.single_price = single_price;
    }

    public CartBill getTotal() {
        return total;
    }

    public void setTotal(CartBill total) {
        this.total = total;
    }


    public Integer getRec_id() {
        return rec_id;
    }

    public void setRec_id(Integer rec_id) {
        this.rec_id = rec_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(Integer goods_number) {
        this.goods_number = goods_number;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class CartBill {
        private String goods_price;

        private Integer real_goods_count;

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public Integer getReal_goods_count() {
            return real_goods_count;
        }

        public void setReal_goods_count(Integer real_goods_count) {
            this.real_goods_count = real_goods_count;
        }
    }
}


/*
*
*     @SerializedName("rec_id") private int mRecId;

    @SerializedName("goods_id") private int mGoodsId;

    @SerializedName("goods_name") private String mGoodsName;

    @SerializedName("goods_number") private int mGoodsNumber;

    @SerializedName("img") private Picture mImg;

    @SerializedName("subtotal") private String mTotalPrice;
* */