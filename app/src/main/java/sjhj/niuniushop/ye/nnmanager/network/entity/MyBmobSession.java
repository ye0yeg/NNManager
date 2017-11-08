package sjhj.niuniushop.ye.nnmanager.network.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2017/10/11.
 */

public class MyBmobSession extends BmobObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private String sid;
}
