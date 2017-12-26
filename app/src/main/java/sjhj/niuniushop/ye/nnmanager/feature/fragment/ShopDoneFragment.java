package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.view.View;

import com.dd.processbutton.FlatButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.event.UpLoadEvent;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopDoneFragment extends BaseFragment {

    @BindView(R.id.btn_done)
    FlatButton btnDone;

    @BindView(R.id.btnUpload)
    FlatButton btnUpload;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_done;
    }

    @Override
    protected void initView() {
        initEvent();
    }

    private void initEvent() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new UpLoadEvent());
            }
        });
    }


    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }
}
