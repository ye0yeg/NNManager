package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;

/**
 * Created by ye on 2017/11/8.
 */

public class MenuActivity extends BaseActivity {


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


    @BindView(R.id.button_sign_out)
    Button btnSignOut;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
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
            flStorage.setVisibility(View.VISIBLE);
        } else if (rank_level == 6) {
            //显示仓库管理
            flStorage.setVisibility(View.VISIBLE);

        }


    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    @OnClick({R.id.text_customer_manager, R.id.text_manage_payment, R.id.text_help
            , R.id.button_sign_out, R.id.text_update, R.id.text_customer_manager_by_seller, R.id.text_financial_manager, R.id.text_storage_manager})
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
        }


    }
}
