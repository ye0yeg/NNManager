package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/11/8.
 */

public class MenuActivity extends BaseActivity {

    @BindView(R.id.text_customer_manager)
    TextView tvManager;
    @BindView(R.id.text_manage_payment)
    TextView tvPayment;
    @BindView(R.id.text_help)
    TextView tvInfo;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    @OnClick({R.id.text_customer_manager,R.id.text_manage_address,R.id.text_help})
    void click(View view){
        switch (view.getId()){
            case R.id.text_customer_manager:
                Intent intent =new Intent(this ,CustomerActivity.class);
                startActivity(intent);
                break;
            case R.id.text_manage_payment:
                Intent payment =new Intent(this,ManagerPaymentActivity.class );
                startActivity(payment);
                break;

            case R.id.text_help:

                break;
        }



    }
}
