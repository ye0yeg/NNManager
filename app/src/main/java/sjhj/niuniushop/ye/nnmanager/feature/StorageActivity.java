package sjhj.niuniushop.ye.nnmanager.feature;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.dou361.dialogui.listener.DialogUIListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.PtrWrapper;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by ye on 2017/11/24.
 */

public class StorageActivity extends BaseActivity {

    private boolean mShipping = false;
    private static final int STORAGE_MANAGER = 999;
    @BindView(R.id.list_cust)
    ListView userListView;

    @BindViews({R.id.text_is_confirm, R.id.text_is_not_confirm})
    List<TextView> tvTextList;

    private static final int GETDATA = 1001;
    private static final int FAIL = 1000;
    private static final int CHANGESUCCESS = 1002;

    private ArrayList<MyBmobPayment> mMyBmobPaymentsList;
    private ArrayList<MyBmobPayment> myData;
    private ArrayList<MyBmobPayment> theData;
    private PtrWrapper mPtrWrapper;
    private MyAdapter mMyAdapter;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATA) {
                //开始处理数据
                if (mShipping) {

                    for (int i = 0; i < myData.size(); i++) {
                        //已经发
                        if (myData.get(i).getGoodState().getDeliery()) {
                            theData.add(myData.get(i));
                        }

                    }
                } else if (!mShipping) {
                    for (int i = 0; i < myData.size(); i++) {
                        //已经发
                        if (myData.get(i).getGoodState().getShipping()) {
                            theData.add(myData.get(i));
                        }

                    }
                }
                //如果，那么
                userListView.setAdapter(mMyAdapter);
                mMyAdapter.reset(theData);

                mPtrWrapper.stopRefresh();
            } else if (msg.what == FAIL) {
                mPtrWrapper.stopRefresh();
                Toasty.info(getApplicationContext(), "未能够获取数据！").show();
            } else if (msg.what == CHANGESUCCESS) {
                mPtrWrapper.postRefresh(50);
            }
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_finacialr;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(this);
        tvTextList.get(1).setActivated(true);
        tvTextList.get(1).setText("未发货");
        tvTextList.get(0).setText("已发货");
        mMyAdapter = new MyAdapter();

        mMyBmobPaymentsList = new ArrayList<>();
        myData = new ArrayList<>();
        theData = new ArrayList<>();

        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                getData();
            }
        };
        mPtrWrapper.postRefresh(50);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    @OnClick({R.id.text_is_confirm, R.id.text_is_not_confirm})
    void chooeQueryOrder(TextView textView) {
        if (textView.isActivated()) return;
        for (TextView tv : tvTextList) {
            tv.setActivated(false);
        }
        textView.setActivated(true);
        switch (textView.getId()) {
            case R.id.text_is_confirm:
                mShipping = true;
                break;
            case R.id.text_is_not_confirm:
                mShipping = false;
                break;
        }
        mPtrWrapper.autoRefresh();
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
                    mMyBmobPaymentsList = (ArrayList<MyBmobPayment>) list;
                    //处理数据 ,获得 仓库所需要的数据
                    for (int i = 0; i < mMyBmobPaymentsList.size(); i++) {
                        if (mMyBmobPaymentsList.get(i).getConfirm()) {
                            myData.add(mMyBmobPaymentsList.get(i));
                        }
                    }
                    mHandler.sendEmptyMessage(GETDATA);
                }
            }

            @Override
            public void onError(int i, String s) {
                mHandler.sendEmptyMessage(FAIL);
            }
        });
    }

    private void reset() {
        mMyBmobPaymentsList.clear();
        myData.clear();
        theData.clear();
    }

    class MyAdapter extends StoragerListAdapter {


        @Override
        protected void onSudoChanger(final MyBmobPayment myBmobPayment) {
            //对按钮的
            int pos = 0;
            if (myBmobPayment.getGoodState().getShipping()) {
                pos = 0;
            } else if (myBmobPayment.getGoodState().getDeliery()) {
                pos = 1;
            }
            //修改订单状态
            String[] ShippingName = {"待发货", "已发货"};
            DialogUIUtils.showSingleChoose(StorageActivity.this, "发货修改", pos, ShippingName, new DialogUIItemListener() {
                @Override
                public void onItemClick(CharSequence text, int position) {

                    if (position == 0) {
                        // 待发货
                        myBmobPayment.getGoodState().setDeliery(false);
                        myBmobPayment.getGoodState().setShipping(true);
                        Toasty.success(getApplicationContext(), "状态修改成功!").show();
                        myBmobPayment.update(StorageActivity.this, myBmobPayment.getObjectId(), new UpdateListener() {

                            @Override
                            public void onSuccess() {
                                Toasty.success(getApplicationContext(), "状态修改成功!").show();

                                mHandler.sendEmptyMessage(CHANGESUCCESS);
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toasty.error(getApplicationContext(), "状态修失败" + s).show();
                                mHandler.sendEmptyMessage(FAIL);
                            }
                        });


                    } else if (position == 1) {

                        DialogUIUtils.showAlert(StorageActivity.this, "物流", "", "输入物流公司", "输入物流编号", "确定", "取消"
                                , false, true, true, new DialogUIListener() {

                                    private String mOrdernumber;
                                    private String mCom;

                                    @Override
                                    public void onPositive() {
                                        //已发货
                                        myBmobPayment.getGoodState().setDeliery(true);
                                        myBmobPayment.getGoodState().setShipping(false);

                                        Toasty.success(StorageActivity.this, "已修改为发货").show();
                                    }

                                    @Override
                                    public void onNegative() {
                                        Toasty.info(StorageActivity.this, "您已取消修改").show();
                                    }

                                    @Override
                                    public void onGetInput(CharSequence input1, CharSequence input2) {
                                        super.onGetInput(input1, input2);
                                        mCom = input1 + "";
                                        mOrdernumber = input2 + "";
                                        myBmobPayment.setShippingCom(mCom);
                                        myBmobPayment.setShippingOrder(mOrdernumber);

                                        myBmobPayment.update(StorageActivity.this, myBmobPayment.getObjectId(), new UpdateListener() {

                                            @Override
                                            public void onSuccess() {
                                                Toasty.success(getApplicationContext(), "状态修改成功!").show();
                                                mHandler.sendEmptyMessage(CHANGESUCCESS);
                                            }

                                            @Override
                                            public void onFailure(int i, String s) {
                                                Toasty.error(getApplicationContext(), "状态修失败" + s).show();
                                                mHandler.sendEmptyMessage(FAIL);
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

        }
    }
}
