package sjhj.niuniushop.ye.nnmanager.feature;

import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.listener.DialogUIListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
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

public class FinancialActivity extends BaseActivity {

    private static final int GETDATA = 1001;
    private static final int GETFAILT = 1000;
    private static final int REFRESH = 1009;
    private ArrayList<MyBmobPayment> mMyBmobPaymentArrayList;

    private ArrayList<MyBmobPayment> payMentList;

    private ArrayList<MyBmobUser> allMyBmobUser;

    @BindView(R.id.list_cust)
    ListView userListView;

    @BindViews({R.id.text_is_confirm, R.id.text_is_not_confirm})
    List<TextView> tvTextList;


    private PtrWrapper mPtrWrapper;

    private MyAdapter mMyAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATA) {
                //处理数据，数据放入adapter
                //将有的数据放置入这里
                if (!mConfirm) {
                    for (int i = 0; i < mMyBmobPaymentArrayList.size(); i++) {
                        if (!mMyBmobPaymentArrayList.get(i).getConfirm()) {
                            payMentList.add(mMyBmobPaymentArrayList.get(i));
                        }
                    }
                } else {
                    for (int i = 0; i < mMyBmobPaymentArrayList.size(); i++) {
                        if (mMyBmobPaymentArrayList.get(i).getConfirm()) {
                            payMentList.add(mMyBmobPaymentArrayList.get(i));
                        }
                    }
                }
                userListView.setAdapter(mMyAdapter);
                mMyAdapter.reset(payMentList);
//                mMyAdapter.reset(mMyBmobPaymentArrayList);
                mPtrWrapper.stopRefresh();
            } else if (msg.what == REFRESH) {
                mPtrWrapper.postRefresh(50);


            } else {
                mHandler.sendEmptyMessage(GETFAILT);
            }


        }
    };
    private boolean mConfirm;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_payment_finacialr;
    }

    @Override
    protected void initView() {
        //all data !
        DialogUIUtils.init(this);
        tvTextList.get(1).setActivated(true);
        mMyBmobPaymentArrayList = new ArrayList<>();
        allMyBmobUser = new ArrayList<>();
        payMentList = new ArrayList<>();
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
        query.addWhereEqualTo("isPay", true);
        String start = "2017-11-30 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        query.addWhereGreaterThanOrEqualTo("createdAt", new BmobDate(date));
        query.setLimit(500);
        query.findObjects(getApplicationContext(), new FindListener<MyBmobPayment>() {

            @Override
            public void onSuccess(List<MyBmobPayment> list) {
                if (list.size() > 0) {
                    //获得总数据
                    mMyBmobPaymentArrayList = (ArrayList<MyBmobPayment>) list;


                    //获得总数据
                    BmobQuery<MyBmobUser> myBmobUserBmobQuery = new BmobQuery<MyBmobUser>();
                    myBmobUserBmobQuery.order("-createdAt");
                    myBmobUserBmobQuery.setLimit(500);
                    myBmobUserBmobQuery.findObjects(FinancialActivity.this, new FindListener<MyBmobUser>() {
                        @Override
                        public void onSuccess(List<MyBmobUser> list) {
                            for (int i = 0; i < list.size(); i++) {
                                allMyBmobUser.add(list.get(i));
                            }
                            mHandler.sendEmptyMessage(GETDATA);
                        }

                        @Override
                        public void onError(int i, String s) {

                        }
                    });


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

    @OnClick({R.id.text_is_confirm, R.id.text_is_not_confirm})
    void chooeQueryOrder(TextView textView) {
        if (textView.isActivated()) return;
        for (TextView tv : tvTextList) {
            tv.setActivated(false);
        }
        textView.setActivated(true);
        switch (textView.getId()) {
            case R.id.text_is_confirm:
                mConfirm = true;
                break;
            case R.id.text_is_not_confirm:
                mConfirm = false;
                break;
        }
        mPtrWrapper.autoRefresh();
    }


    class MyAdapter extends FinacialListAdapter {

        @Override
        protected void onSudoChanger(final MyBmobPayment myBmobUser) {

            if (myBmobUser.getConfirm()) {
                //点击审核
                DialogUIUtils.showMdAlert(FinancialActivity.this, "撤销审核", "确定撤销审核吗？", new DialogUIListener() {
                    @Override
                    public void onPositive() {
                        //That good function!
                        myBmobUser.setConfirm(false);
                        myBmobUser.update(FinancialActivity.this, myBmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Toasty.success(FinancialActivity.this, "撤销成功！").show();
                                mHandler.sendEmptyMessage(REFRESH);
                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });
                    }

                    @Override
                    public void onNegative() {

                    }

                }).show();
                return;
            }


            //点击审核
            DialogUIUtils.showMdAlert(FinancialActivity.this, "审核确认", "确定审核订单吗？", new DialogUIListener() {
                @Override
                public void onPositive() {
                    //That good function!
                    myBmobUser.setConfirm(true);
                    myBmobUser.update(FinancialActivity.this, myBmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            Toasty.success(FinancialActivity.this, "审核成功！").show();
                            mHandler.sendEmptyMessage(REFRESH);
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                }

                @Override
                public void onNegative() {

                }

            }).show();
        }

        @Override
        protected String getRecom(String myBmobPayment) {

            String recom = "无";
            //查找推荐人 get data.
            for (int i = 0; i < allMyBmobUser.size(); i++) {
                if (allMyBmobUser.get(i).getName().toString().trim().equals(myBmobPayment)) {
                    recom = allMyBmobUser.get(i).getRecomNumber();
                    return recom;
                }
            }

            return recom;
        }

        @Override
        protected String getLevel(String name) {
            String level = "未识别";

            for (int i = 0; i < allMyBmobUser.size(); i++) {
                if (allMyBmobUser.get(i).getName().equals(name)) {
                    if (allMyBmobUser.get(i).getRank_level() == 0) {
                        level = "车主";
                        return level;
                    } else if (allMyBmobUser.get(i).getRank_level() == 1) {
                        level = "经销商";
                        return level;
                    } else if (allMyBmobUser.get(i).getRank_level() == 2) {
                        level = "VIP";
                        return level;
                    }
                }
            }
            return level;
        }
    }
}
