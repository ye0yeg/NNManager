package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopAddtionFragment extends BaseFragment {

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_addtion;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }
}
