package sjhj.niuniushop.ye.nnmanager.feature;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;

/**
 * Created by ye on 2017/11/24.
 */

public class FinancialActivity extends BaseActivity {


    private static final int GETDATA = 1001;
    private ArrayList<MyBmobPayment> mMyBmobPaymentArrayList;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == GETDATA){
                //处理数据，数据放入adapter

                mMyBmobPaymentArrayList;

            }


        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_manager;
    }

    @Override
    protected void initView() {
        //all data !
        mMyBmobPaymentArrayList = new ArrayList<>();

        getData();


    }

    private void getData() {
        reset();
        BmobQuery<MyBmobPayment> query = new BmobQuery<>();
        query.order("-createdAt");
        query.setLimit(500);
        query.findObjects(getApplicationContext(), new FindListener<MyBmobPayment>() {

            @Override
            public void onSuccess(List<MyBmobPayment> list) {
                if (list.size() > 0) {
                    //获得总数据
                    mMyBmobPaymentArrayList = (ArrayList<MyBmobPayment>) list;
                    mHandler.sendEmptyMessage(GETDATA);
                } else {
                    //无信息提示
                    Toasty.info(getApplicationContext(), "暂时查无信息！").show();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    private void reset() {

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }
}
