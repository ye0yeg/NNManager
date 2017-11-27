package sjhj.niuniushop.ye.nnmanager.feature;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by ye on 2017/11/24.
 */

public class StorageActivity extends BaseActivity {

    private ArrayList<MyBmobPayment> mMyBmobPaymentsList;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_manager;
    }

    @Override
    protected void initView() {
        mMyBmobPaymentsList = new ArrayList<>();
        getData();
        //需要用到的数据
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


    private void getData() {
        reset();
        BmobQuery<MyBmobPayment> query = new BmobQuery<>();
        query.order("-createdAt");
        query.setLimit(500);
        query.findObjects(getApplicationContext(), new FindListener<MyBmobPayment>() {
            @Override
            public void onSuccess(List<MyBmobPayment> list) {
//                if (list.size() > 0) {
//                    //获得总数据
//                    BmobQuery<MyBmobUser> myBmobUserBmobQuery = new BmobQuery<MyBmobUser>();
//                    myBmobUserBmobQuery.order("-createdAt");
//                    myBmobUserBmobQuery.setLimit(500);
//                    myBmobUserBmobQuery.findObjects(StorageActivity.this, new FindListener<MyBmobUser>() {
//                        @Override
//                        public void onSuccess(List<MyBmobUser> list) {
//                            for (int i = 0; i < list.size(); i++) {
//                                allMyBmobUser.add(list.get(i));
//                                if (list.get(i).getRecomNumber().equals(UserManager.getInstance().getUser().getName())) {
//                                    mMyBmobUsers.add(list.get(i));
//                                    System.out.println(list.get(i).toString());
//                                }
//                            }
//
//                            mHandler.sendEmptyMessage(GETDATASUCCESS);
//                        }
//
//                        @Override
//                        public void onError(int i, String s) {
//
//                        }
//                    });
//
//                } else {
//                    //无信息提示
//                }
//

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    private void reset() {

    }
}
