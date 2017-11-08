package sjhj.niuniushop.ye.nnmanager.feature;

import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by chwin on 2017/11/9.
 */

public class ManagerPaymentActivity extends BaseActivity{
    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_manager;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }
}
