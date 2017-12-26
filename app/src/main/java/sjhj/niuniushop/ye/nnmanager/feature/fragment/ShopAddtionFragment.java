package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dd.processbutton.FlatButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopServer;
import sjhj.niuniushop.ye.nnmanager.network.event.NextFragment;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopAddtionFragment extends BaseFragment {


    @BindView(R.id.met_addtion)
    MaterialEditText etAddtion;

    @BindView(R.id.fb_next)
    FlatButton flatButton;

    private ShopAddtionAdapter shopAddtionAdapter;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_addtion;
    }

    @Override
    protected void initView() {
        initEvent();


    }

    private void initEvent() {
        flatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listterner.processAddtion(etAddtion.getText().toString());
                EventBus.getDefault().post(new NextFragment());
            }
        });
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private ShopAddtionFragment.FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        //方法在这里
        void processAddtion(String addtion);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof ShopInfoFragment.FragmentInteraction) {
            listterner = (ShopAddtionFragment.FragmentInteraction) getActivity(); // 2.2 获取到宿主activity并赋值
        } else {
            Log.e("GOODSINFOFRAGMENT", "activity must implements FragmentInteraction");
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }
}
