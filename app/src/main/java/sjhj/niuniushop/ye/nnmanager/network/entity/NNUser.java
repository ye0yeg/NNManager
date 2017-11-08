package sjhj.niuniushop.ye.nnmanager.network.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by ye on 2017/10/10.
 */

public class NNUser extends BmobUser{

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //可扩展的用户形态。
    private String sex;
}
