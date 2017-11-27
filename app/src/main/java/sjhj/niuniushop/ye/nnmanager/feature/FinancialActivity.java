package sjhj.niuniushop.ye.nnmanager.feature;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.dou361.dialogui.DialogUIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.PtrWrapper;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;

/**
 * Created by ye on 2017/11/24.
 */

public class FinancialActivity extends BaseActivity {

    private static final int GETDATA = 1001;
    private static final int GETFAILT = 1000;
    private ArrayList<MyBmobPayment> mMyBmobPaymentArrayList;

    private ArrayList<MyBmobPayment> payMentList;

    @BindView(R.id.list_cust)
    ListView userListView;

    private PtrWrapper mPtrWrapper;

    private MyAdapter mMyAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATA) {
                //处理数据，数据放入adapter
                //将有的数据放置入这里
                for (int i = 0; i < mMyBmobPaymentArrayList.size(); i++) {
                    if (!mMyBmobPaymentArrayList.get(i).getConfirm()) {
                        payMentList.add(mMyBmobPaymentArrayList.get(i));
                    }
                }
                userListView.setAdapter(mMyAdapter);
                mMyAdapter.reset(payMentList);
//                mMyAdapter.reset(mMyBmobPaymentArrayList);
                mPtrWrapper.stopRefresh();
            } else {
                mHandler.sendEmptyMessage(GETFAILT);
            }


        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_finacialr;
    }

    @Override
    protected void initView() {
        //all data !
        DialogUIUtils.init(this);
        mMyBmobPaymentArrayList = new ArrayList<>();
        payMentList =new ArrayList<>();
        mMyAdapter = new MyAdapter();

        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                getData();
            }
        };
        mPtrWrapper.postRefresh(50);


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
                    mHandler.sendEmptyMessage(GETFAILT);
                }
            }

            @Override
            public void onError(int i, String s) {
                mHandler.sendEmptyMessage(GETFAILT);
            }
        });

    }

    private void reset() {
        payMentList.clear();
        mMyBmobPaymentArrayList.clear();
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


    class MyAdapter extends FinacialListAdapter {

        @Override
        protected void onSudoChanger(MyBmobPayment myBmobUser) {
            //点击审核
            DialogUIUtils.showSingleChoose(FinancialActivity.this,)
        }
    }
}
