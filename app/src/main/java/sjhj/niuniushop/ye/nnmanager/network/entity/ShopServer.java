package sjhj.niuniushop.ye.nnmanager.network.entity;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/12/26.
 */

public class ShopServer extends BmobObject {
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getServerAvail() {
        return serverAvail;
    }

    public void setServerAvail(String serverAvail) {
        this.serverAvail = serverAvail;
    }

    public ArrayList<ServerDetail> getServerDetails() {
        return serverDetails;
    }

    public void setServerDetails(ArrayList<ServerDetail> serverDetails) {
        this.serverDetails = serverDetails;
    }

    private String serverAvail;

    private ArrayList<ServerDetail> serverDetails;

    public static class ServerDetail {

        private String serverName;

        private String serverPrice;

        private String serverVIPPrice;

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }

        public String getServerPrice() {
            return serverPrice;
        }

        public void setServerPrice(String serverPrice) {
            this.serverPrice = serverPrice;
        }

        public String getServerVIPPrice() {
            return serverVIPPrice;
        }

        public void setServerVIPPrice(String serverVIPPrice) {
            this.serverVIPPrice = serverVIPPrice;
        }
    }

}
