package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/11/8.
 */

public class MenuActivity extends BaseActivity {


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
    @BindView(R.id.button_sign_out)
    Button btnSignOut;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {

        tvMe.setText("您好："+UserManager.getInstance().getUser().getName());
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    @OnClick({R.id.text_customer_manager, R.id.text_manage_payment, R.id.text_help, R.id.button_sign_out, R.id.text_update})
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
        }


    }
}
