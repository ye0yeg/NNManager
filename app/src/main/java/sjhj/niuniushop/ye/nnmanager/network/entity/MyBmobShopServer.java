package sjhj.niuniushop.ye.nnmanager.network.entity;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/12/8.
 */

public class MyBmobShopServer extends BmobObject {
    //店名
    private String shopName;

    //店主
    private String shopOwner;

    //店铺等级
    private String level;

    //介绍店铺
    private String intro;

    //评分
    private String star;

    //店铺评论
    private String comment;

    //店铺是否显示
    private String isShow;

    //店铺地址
    private String address;

    //店铺经纬度
    private String jindu;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJindu() {
        return jindu;
    }

    public void setJindu(String jindu) {
        this.jindu = jindu;
    }

    public String getWeidu() {
        return weidu;
    }

    public void setWeidu(String weidu) {
        this.weidu = weidu;
    }

    public String getShowPolicy() {
        return showPolicy;
    }

    public void setShowPolicy(String showPolicy) {
        this.showPolicy = showPolicy;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsefulTime() {
        return usefulTime;
    }

    public void setUsefulTime(String usefulTime) {
        this.usefulTime = usefulTime;
    }

    public String getUnusefulTime() {
        return unusefulTime;
    }

    public void setUnusefulTime(String unusefulTime) {
        this.unusefulTime = unusefulTime;
    }

    public String getNumberOfUse() {
        return numberOfUse;
    }

    public void setNumberOfUse(String numberOfUse) {
        this.numberOfUse = numberOfUse;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getShopServer() {
        return shopServer;
    }

    public void setShopServer(String shopServer) {
        this.shopServer = shopServer;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    private String weidu;

    //客户消费记录。 （客户在使用的时候留下的订单，需要加入店家统计中）
    //店家权利（）

    //优惠政策
    private String showPolicy;

    //套餐内容
    private String contain;

    //备注
    private String note;

    //有效期
    private String usefulTime;

    //除去日期
    private String unusefulTime;

    //使用次数
    private String numberOfUse;

    //规则提醒
    private String rule;

    //商家服务
    private String shopServer;

    //温馨提示
    private String tips;

    private List<Comment> mComments;

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public static class Comment {
        String avator;
        String userName;

        public String getAvator() {
            return avator;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContain() {
            return contain;
        }

        public void setContain(String contain) {
            this.contain = contain;
        }

        String level;
        String star;
        String time;
        String type;
        String contain;
    }

}