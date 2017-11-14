package sjhj.niuniushop.ye.nnmanager.feature;


import android.app.Application;

import cn.bmob.v3.Bmob;


public class NNApplication extends Application {

    private static final String URL = "https://openapi.alipay.com/gateway.do";

    @Override
    public void onCreate() {
        super.onCreate();
        //82e2ea8e47d85e35fad6af646def27f9 是实际使用
        //337b791be8fac8168dede0d44fbd9003 是测试
//        Bmob.initialize(this, "337b791be8fac8168dede0d44fbd9003"); //测试

        Bmob.initialize(this, "82e2ea8e47d85e35fad6af646def27f9"); //使用
    }
}
