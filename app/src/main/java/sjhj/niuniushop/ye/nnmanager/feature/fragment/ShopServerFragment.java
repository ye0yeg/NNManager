package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.view.View;
import android.widget.TextView;

import com.dd.processbutton.FlatButton;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.listener.DialogUIListener;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopServerFragment extends BaseFragment {

    @BindView(R.id.fb_car_wash)
    FlatButton fbCarWash;

    @BindView(R.id.fb_car_improve)
    FlatButton fbCarImporve;

    @BindView(R.id.fb_car_keep)
    FlatButton fbKeep;

    @BindView(R.id.fb_car_tyre)
    FlatButton fbTyre;

    @BindView(R.id.fb_car_install)
    FlatButton fbInstall;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_server;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(getContext());

    }

    @OnClick({R.id.fb_car_wash})
    void onClickTab(View view) {
        switch (view.getId()) {
            case R.id.fb_car_wash:
                View rootView = View.inflate(getActivity(), R.layout.custom_carwash_choose, null);
                DialogUIUtils.showCustomAlert(getActivity(),rootView);
                break;
        }

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }
}
