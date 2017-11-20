package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.dou361.dialogui.listener.DialogUIListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.ProgressWrapper;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.PtrWrapper;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by chwin on 2017/11/9.
 */

public class ManagerPaymentActivity extends BaseActivity {

    private static final int GETDATASUCCESS = 1001;
    private static final int CHANGESUCCESS = 1002;
    public static final int FAILD = 1003;

    private ArrayList<MyBmobPayment> allData;
    private ArrayList<MyBmobPayment> mMyBmobPayments;
    private ArrayList<MyBmobUser> mMyBmobUsers;
    private ArrayList<MyBmobPayment> allPayData;
    private ProgressWrapper mProgressWrapper;

    private PtrWrapper mPtrWrapper;

    @BindView(R.id.list_cust)
    ListView userListView;

    private MyAdapter mMyAdapter;

    private MyBmobUser mMyBmobUser;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATASUCCESS) {

                mMyAdapter = new MyAdapter(mMyBmobUsers);
                userListView.setAdapter(mMyAdapter);
                //获得信息，分类信息
                for (int i = 0; i < mMyBmobUsers.size(); i++) {
                    if (mMyBmobUsers.get(i).getRecomNumber().equals(UserManager.getInstance().getUser().getName())) {
                        //双循环，可以实现信息
                        for (int j = 0; j < allData.size(); j++) {
                            if (allData.get(j).getName().equals(mMyBmobUsers.get(i).getName()) && allData.get(j).getPay()) {
                                //查看所有已经支付的订单。加入订单管理
                                mMyBmobPayments.add(allData.get(j));
                                System.out.println(allData.get(j).toString());
                            }
                        }
                    }
                }


                if (UserManager.getInstance().getUser().getName().equals("15959207612") || UserManager.getInstance().getUser().getRank_level().equals("101")) {
                    // 过滤信息
                    for (int j = 0; j < allData.size(); j++) {
                        if (allData.get(j).getPay()) {
                            //查看所有已经支付的订单。加入订单管理
                            allPayData.add(allData.get(j));
                        }
                    }
                    mMyAdapter.reset(allPayData);

                } else {
                    mMyAdapter.reset(mMyBmobPayments);
                }
                mPtrWrapper.stopRefresh();
            } else if (msg.what == CHANGESUCCESS) {
                mPtrWrapper.postRefresh(50);
            }
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_manager;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(this);
        allData = new ArrayList<>();
        mMyBmobUsers = new ArrayList<>();
        mMyBmobPayments = new ArrayList<>();
        allPayData = new ArrayList<>();
        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                getData();
            }
        };
        mPtrWrapper.postRefresh(50);

    }

    public void reset() {
        allData.clear();
        mMyBmobPayments.clear();
        mMyBmobUsers.clear();
        allPayData.clear();
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
                    allData = (ArrayList<MyBmobPayment>) list;
                    //获得总数据
                    BmobQuery<MyBmobUser> myBmobUserBmobQuery = new BmobQuery<MyBmobUser>();
                    myBmobUserBmobQuery.order("-createdAt");
                    myBmobUserBmobQuery.setLimit(500);
                    myBmobUserBmobQuery.findObjects(ManagerPaymentActivity.this, new FindListener<MyBmobUser>() {
                        @Override
                        public void onSuccess(List<MyBmobUser> list) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getRecomNumber().equals(UserManager.getInstance().getUser().getName())) {
                                    mMyBmobUsers.add(list.get(i));
                                    System.out.println(list.get(i).toString());
                                }
                            }

                            mHandler.sendEmptyMessage(GETDATASUCCESS);
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });

                } else {
                    //无信息提示
                }


            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    public class MyAdapter extends PaymentManagerListAdapter {

        public MyAdapter(ArrayList<MyBmobUser> myBmobUserArrayList) {
            super(myBmobUserArrayList);
        }

        @Override
        protected void onSudoChanger(final MyBmobPayment myBmobPayment) {

//            if (UserManager.getInstance().getUser().getName().equals("15959207612")) {
//                Toasty.error(ManagerPaymentActivity.this, "您无权进行修改").show();
//                return;
//            }

            int pos = 0;
            if (myBmobPayment.getGoodState().getShipping()) {
                pos = 0;
            } else if (myBmobPayment.getGoodState().getDeliery()) {
                pos = 1;
            }
            //修改订单状态
            String[] ShippingName = {"待发货", "已发货"};
            DialogUIUtils.showSingleChoose(ManagerPaymentActivity.this, "发货修改", pos, ShippingName, new DialogUIItemListener() {
                @Override
                public void onItemClick(CharSequence text, int position) {

                    if (position == 0) {
                        // 待发货
                        myBmobPayment.getGoodState().setDeliery(false);
                        myBmobPayment.getGoodState().setShipping(true);
                        Toasty.success(getApplicationContext(), "状态修改成功!").show();
                        myBmobPayment.update(ManagerPaymentActivity.this, myBmobPayment.getObjectId(), new UpdateListener() {

                            @Override
                            public void onSuccess() {
                                Toasty.success(getApplicationContext(), "状态修改成功!").show();

                                mHandler.sendEmptyMessage(CHANGESUCCESS);
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toasty.error(getApplicationContext(), "状态修失败" + s).show();
                                mHandler.sendEmptyMessage(FAILD);
                            }
                        });


                    } else if (position == 1) {

                        DialogUIUtils.showAlert(ManagerPaymentActivity.this, "物流", "", "输入物流公司", "输入物流编号", "确定", "取消"
                                , false, true, true, new DialogUIListener() {

                                    private String mOrdernumber;
                                    private String mCom;

                                    @Override
                                    public void onPositive() {
                                        //已发货
                                        myBmobPayment.getGoodState().setDeliery(true);
                                        myBmobPayment.getGoodState().setShipping(false);


                                        Toasty.success(ManagerPaymentActivity.this, "已修改为发货").show();


                                    }

                                    @Override
                                    public void onNegative() {
                                        Toasty.info(ManagerPaymentActivity.this, "您已取消修改").show();
                                    }

                                    @Override
                                    public void onGetInput(CharSequence input1, CharSequence input2) {
                                        super.onGetInput(input1, input2);
                                        mCom = input1 + "";
                                        mOrdernumber = input2 + "";
                                        myBmobPayment.setShippingCom(mCom);
                                        myBmobPayment.setShippingOrder(mOrdernumber);

                                        myBmobPayment.update(ManagerPaymentActivity.this, myBmobPayment.getObjectId(), new UpdateListener() {

                                            @Override
                                            public void onSuccess() {
                                                Toasty.success(getApplicationContext(), "状态修改成功!").show();
                                                mHandler.sendEmptyMessage(CHANGESUCCESS);
                                            }

                                            @Override
                                            public void onFailure(int i, String s) {
                                                Toasty.error(getApplicationContext(), "状态修失败" + s).show();
                                                mHandler.sendEmptyMessage(FAILD);
                                            }
                                        });
                                    }
                                }).show();


                    }

                }
            }).show();

            //查看物流

        }

        @Override
        protected void onShippingCheck(MyBmobPayment myBmobPayment) {

            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", myBmobPayment.getShippingOrder());
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toasty.info(ManagerPaymentActivity.this, "已将运单复制到剪切板 !").show();
            Uri uri = Uri.parse("http://www.kuaidi100.com/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


}
