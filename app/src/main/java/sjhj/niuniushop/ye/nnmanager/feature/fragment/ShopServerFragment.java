package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dd.processbutton.FlatButton;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.listener.DialogUIListener;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopServerFragment extends BaseFragment {

    @BindView(R.id.fb_car_wash)
    FlatButton fbCarWash;

    @BindView(R.id.fb_car_improve)
    FlatButton fbCarImporve;

    @BindView(R.id.fb_car_keep)
    FlatButton fbKeep;

    @BindView(R.id.fb_car_tyre)
    FlatButton fbTyre;

    @BindView(R.id.fb_car_install)
    FlatButton fbInstall;
    private View rootView;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_server;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(getContext());
        initViewHolder();

    }

    private void pickUp() {
        DialogUIUtils.showAlert(getActivity(), "填写价格", "", "普通价格", "VIP价格", "OK", "取消", false, true, true, new DialogUIListener() {
            private String i1;
            private String i2;

            @Override
            public void onPositive() {

            }

            @Override
            public void onNegative() {

            }

            @Override
            public void onGetInput(CharSequence input1, CharSequence input2) {
                super.onGetInput(input1, input2);

                i1 = input1 + "";
                i2 = input2 + "";
            }
        }).show();
    }

    private void initViewHolder() {
        rootView = View.inflate(getActivity(), R.layout.custom_carwash_choose, null);
        CheckBox cb_wash_car = rootView.findViewById(R.id.cb_washcar);
        CheckBox cb_wash_5 = rootView.findViewById(R.id.cb_normal_5);
        CheckBox cb_wash_7 = rootView.findViewById(R.id.cb_normal_7);
        CheckBox cb_inside = rootView.findViewById(R.id.cb_inside);
        CheckBox cb_ad_wash = rootView.findViewById(R.id.cb_ad_wash);
        CheckBox cb_ad_wash_deep = rootView.findViewById(R.id.cb_ad_wash_deep);
        CheckBox cb_deodorization = rootView.findViewById(R.id.cb_deodorization);

        cb_wash_car.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                    pickUp();
                } else {
                    //没选中
                }
            }


        });

        cb_wash_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });

        cb_wash_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });

        cb_inside.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });

        cb_ad_wash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });

        cb_ad_wash_deep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });

        cb_deodorization.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //选中
                } else {
                    //没选中
                }
            }
        });


    }


    @OnClick({R.id.fb_car_wash})
    void onClickTab(View view) {
        switch (view.getId()) {
            case R.id.fb_car_wash:


                DialogUIUtils.showCustomAlert(getActivity(), rootView, Gravity.CENTER, true, false).show();
                break;
        }

    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }
}
