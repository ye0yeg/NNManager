package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.APKVersionCdeUtils;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.BadgeWrapper;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobShopServer;

/**
 * Created by ye on 2017/11/8.
 */

public class MenuActivity extends BaseActivity {
    private static final int GETNEWVERSION = 1002;
    private int mUpdateNumber;
    private String mVersionCode;

    private static final int SETCONFIRM = 1001;
    @BindView(R.id.text_me)
    TextView tvMe;
    @BindView(R.id.text_customer_manager)
    TextView tvManager;
    @BindView(R.id.text_manage_payment)
    TextView tvPayment;
    @BindView(R.id.text_help)
    TextView tvInfo;
    @BindView(R.id.text_update)
    TextView tvUpdate;
    //客户下单销售改价格用
    @BindView(R.id.text_customer_manager_by_seller)
    TextView tvSeller;
    //财务管理
    @BindView(R.id.text_financial_manager)
    TextView tvFinacial;
    //仓库管理
    @BindView(R.id.text_storage_manager)
    TextView tvStorage;
    //三个栏目
    @BindView(R.id.fl_customer_by_seller)
    FrameLayout flSeller;
    @BindView(R.id.fl_financial_manager)
    FrameLayout flFinacial;
    @BindView(R.id.fl_storage_manager)
    FrameLayout flStorage;

    private BadgeWrapper mUpadate;
    @BindView(R.id.button_sign_out)
    Button btnSignOut;

    @BindView(R.id.button_shop_upload)
    Button btnUpload;

    private List<MyBmobShopServer.Comment> mCommentList;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETNEWVERSION) {
                mUpadate.showNumber(mUpdateNumber);
            }
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
        mUpadate = new BadgeWrapper(tvUpdate);
        mCommentList = new ArrayList<>();
        initUpdate();
        int rank_level = UserManager.getInstance().getUser().getRank_level();
        tvMe.setText("您好：" + UserManager.getInstance().getUser().getName());
        //角色 分类。
        //根据角色分类添加菜单
        // 4 : 业务员
        // 5 : 财务
        // 6 : 仓库
        if (rank_level == 4) {
            //显示订单管理
            flSeller.setVisibility(View.VISIBLE);
        } else if (rank_level == 5) {
            //显示财务审核管理
            flFinacial.setVisibility(View.VISIBLE);
        } else if (rank_level == 6) {
            //显示仓库管理
            flStorage.setVisibility(View.VISIBLE);
        } else if (rank_level == 1001) {
            showAll();
        }

    }

    private void showAll() {
        flFinacial.setVisibility(View.VISIBLE);
        flStorage.setVisibility(View.VISIBLE);
        btnUpload.setVisibility(View.VISIBLE);
    }


    private void initUpdate() {
        BmobQuery<MyBackUpdateCheck> query = new BmobQuery<>();
        query.findObjects(getApplicationContext(), new FindListener<MyBackUpdateCheck>() {
            @Override
            public void onSuccess(List<MyBackUpdateCheck> list) {
                if (list.size() >= 0) {
                    mUpdateNumber = list.get(0).getHasUpdate();
                    mVersionCode = list.get(0).getVersionCode();
                }
                if ((mUpdateNumber == 1) && (Integer.valueOf(mVersionCode) != APKVersionCdeUtils.getVersionCode(getApplicationContext()))) {
                    mHandler.sendEmptyMessage(GETNEWVERSION);
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

    @OnClick({R.id.text_customer_manager, R.id.text_manage_payment, R.id.text_help
            , R.id.button_sign_out, R.id.text_update, R.id.text_customer_manager_by_seller, R.id.text_financial_manager, R.id.text_storage_manager,R.id.button_shop_upload})
    void click(View view) {
        switch (view.getId()) {
            case R.id.text_customer_manager:
                Intent intent = new Intent(this, CustomerActivity.class);
                startActivity(intent);
                break;
            case R.id.text_manage_payment:
                Intent payment = new Intent(this, ManagerPaymentActivity.class);
                startActivity(payment);
                break;
            case R.id.text_help:

                break;
            case R.id.text_update:
                Uri uri = Uri.parse("http://fir.im/t27e");
                Intent upintent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(upintent);
                break;
            case R.id.button_sign_out:
                UserManager.getInstance().clear();
                Intent loginout = new Intent(this, SignInActivity.class);
                loginout.putExtra("LOGINOUT", true);
                startActivity(loginout);
                break;
            case R.id.text_customer_manager_by_seller:
                // 业务员管理订单用
                Intent manager = new Intent(this, ManagerPaymentActivity.class);
                startActivity(manager);
                break;
            case R.id.text_financial_manager:
                // 财务
                Intent financial = new Intent(this, FinancialActivity.class);
                startActivity(financial);
                break;
            case R.id.text_storage_manager:
                //仓库
                Intent storage = new Intent(this, StorageActivity.class);
                startActivity(storage);
                break;
            case R.id.button_shop_upload:
                upload();


//                Intent upload = new Intent (this, UploadActivity.class);
//                startActivity(upload);
                break;

        }


    }

    private void upload() {
        MyBmobShopServer myBmobShopServer = new MyBmobShopServer();
        myBmobShopServer.setAddress("xx省xx市xx县xx村xx社xx拐角xx门牌号xx间xx床位");
        myBmobShopServer.setShopName("店名示例");
        myBmobShopServer.setShopOwner("店主实例");
        myBmobShopServer.setLevel("A店铺");
        myBmobShopServer.setIntro("这是一家很好的店铺");
        myBmobShopServer.setStar("10");
        myBmobShopServer.setComment("这是一家不好的店铺");
        myBmobShopServer.setIsShow("true");
        myBmobShopServer.setJindu("0");
        myBmobShopServer.setWeidu("0");
        myBmobShopServer.setShowPolicy("政策的东西");
        myBmobShopServer.setContain("内容的东西");
        myBmobShopServer.setNote("NoteStuff");
        myBmobShopServer.setUsefulTime("About 10 days");
        myBmobShopServer.setUnusefulTime("About 10days ,yep");
        myBmobShopServer.setNumberOfUse("About 10 times");
        myBmobShopServer.setRule("使用规则");
        myBmobShopServer.setShopServer("Some kind of shop server");
        myBmobShopServer.setTips("this, is , tip ,s");
        MyBmobShopServer.Comment comment =new MyBmobShopServer.Comment();
        comment.setAvator("头像");
        comment.setUserName("USerName");
        comment.setLevel("Level:1");
        comment.setStar("2");
        comment.setTime("20170101");
        comment.setType("type1");
        comment.setContain("大概这就是轮胎店");
        mCommentList.add(comment);
        myBmobShopServer.setComments(mCommentList);
        myBmobShopServer.save(getApplicationContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                Toasty.success(getApplicationContext(),"保存SUCCESS").show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toasty.error(getApplicationContext(),"保存不SUCCESS").show();
            }
        });

    }
}
