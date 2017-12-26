package sjhj.niuniushop.ye.nnmanager.network.entity;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/12/25.
 */

public class ShopInfo extends BmobObject {

    private String userName;

    private BaseInfo mBaseInfo;

    private String addtion;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BaseInfo getBaseInfo() {
        return mBaseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        mBaseInfo = baseInfo;
    }

    public String getAddtion() {
        return addtion;
    }

    public void setAddtion(String addtion) {
        this.addtion = addtion;
    }


    public static class BaseInfo {

        private String shopName;
        private String shopOwner;
        //这里的shopMainName其实就是userName.
        private String shopMainName;
        private String shopLevel;
        private String shopIntro;
        private String shopContact;
        private String address;

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

        public String getShopMainName() {
            return shopMainName;
        }

        public void setShopMainName(String shopMainName) {
            this.shopMainName = shopMainName;
        }

        public String getShopLevel() {
            return shopLevel;
        }

        public void setShopLevel(String shopLevel) {
            this.shopLevel = shopLevel;
        }

        public String getShopIntro() {
            return shopIntro;
        }

        public void setShopIntro(String shopIntro) {
            this.shopIntro = shopIntro;
        }

        public String getShopContact() {
            return shopContact;
        }

        public void setShopContact(String shopContact) {
            this.shopContact = shopContact;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public ArrayList<String> getPics() {
            return pics;
        }

        public void setPics(ArrayList<String> pics) {
            this.pics = pics;
        }

        private ArrayList<String> pics;
    }


}
