package sjhj.niuniushop.ye.nnmanager.feature;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by chwin on 2017/11/9.
 */

public class ManagerPaymentActivity extends BaseActivity {

    private static final int GETDATASUCCESS = 1001;
    private ArrayList<MyBmobPayment> allData;
    private ArrayList<MyBmobPayment> mMyBmobPayments;
    private ArrayList<MyBmobUser> mMyBmobUsers;

    private MyBmobUser mMyBmobUser;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATASUCCESS) {
                //获得信息，分类信息
                for (int i = 0; i < allData.size(); i++) {
                    //以

                }

            }
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_manager;
    }

    @Override
    protected void initView() {
        allData = new ArrayList<>();
        getData();
    }

    private void getData() {
        BmobQuery<MyBmobPayment> query = new BmobQuery<>();
        query.findObjects(getApplicationContext(), new FindListener<MyBmobPayment>() {
            @Override
            public void onSuccess(List<MyBmobPayment> list) {
                if (list.size() > 0) {
                    allData = (ArrayList<MyBmobPayment>) list;
                    //获得总数据
                    BmobQuery<MyBmobUser> myBmobUserBmobQuery = new BmobQuery<MyBmobUser>();
                    myBmobUserBmobQuery.findObjects(ManagerPaymentActivity.this, new FindListener<MyBmobUser>() {
                        @Override
                        public void onSuccess(List<MyBmobUser> list) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getRecomNumber().equals(UserManager.getInstance().getUser().getName())) {

                                }
                            }
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });

                } else {
                    //无信息提示
                }

                mHandler.sendEmptyMessage(GETDATASUCCESS);

            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


}
