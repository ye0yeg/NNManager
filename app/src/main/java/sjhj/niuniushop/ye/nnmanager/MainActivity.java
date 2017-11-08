package sjhj.niuniushop.ye.nnmanager;

import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


}
