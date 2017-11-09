package sjhj.niuniushop.ye.nnmanager.feature;


import android.app.Application;

import cn.bmob.v3.Bmob;


public class NNApplication extends Application {

    private static final String URL = "https://openapi.alipay.com/gateway.do";

    @Override
    public void onCreate() {
        super.onCreate();
        //be66e72f9340a4aa5c23fc037469b87a 是测试
        Bmob.initialize(this, "82e2ea8e47d85e35fad6af646def27f9");

    }
}
