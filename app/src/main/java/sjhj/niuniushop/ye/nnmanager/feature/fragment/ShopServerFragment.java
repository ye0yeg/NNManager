package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dd.processbutton.FlatButton;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopInfo;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopServer;
import sjhj.niuniushop.ye.nnmanager.network.event.NextFragment;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopServerFragment extends BaseFragment {

    private static final int CARWASH = 1001;
    private static final int NOCARWASH = 1999;

    private static final int CARCASH5 = 1002;
    private static final int NOCARCASH5 = 1998;

    private static final int CARSH7 = 1003;
    private static final int NOCARSH7 = 1997;

    private static final int CARINSIDE = 1004;
    private static final int NOCARINSIDE = 1996;

    private static final int ADWASH = 1005;
    private static final int NOADWASH = 1995;

    private static final int ADWASHDEEP = 1006;
    private static final int NOADWASHDEEP = 1994;

    private static final int DEODORIZATION = 1007;
    private static final int NODEODORIZATION = 1993;


    private ShopServer mShopServer;

    private ArrayList<ShopServer.ServerDetail> allData;

    private ArrayList<ShopServer.ServerDetail> mServerDetailCarWashPart;

    private ArrayList<ShopServer.ServerDetail> mServerDetailCarImprPart;

    private ArrayList<ShopServer.ServerDetail> mServerDetailCarKeepPart;

    private ArrayList<ShopServer.ServerDetail> mServerDetailCarTyrePart;

    private ArrayList<ShopServer.ServerDetail> mServerDetailCarInstallPart;


    private boolean is0Wash, is5Wash, is7Wahs, isInside, isAdWash, isAdWashDeep, isDedo;

    private boolean isImpSticky, isImpCrystal, isImpCoating, isImpWax, isImpGlaze, isImpPolishing;

    private boolean isKeepChangeGas, isKeepGasCan, isKeepAirCan, isKeepChangeWiper;

    private boolean isTyreTyre, isTyreBalance, isTyreLocate, isTyreFix;

    private boolean isInstallCam, isInstallTemp, isInstallCamAndTemp;

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

    @BindView(R.id.fb_next)
    FlatButton fbNext;


    private View carWashView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    private View mCarImproveView;
    private BuildBean mShowCarWash;
    private BuildBean mShowCarImp;
    private BuildBean mShowCarKeep;
    private BuildBean mShowCarTyre;
    private BuildBean mShowInstall;
    private View mCarKeepView;
    private View mCarTyreView;
    private View mCarInstallView;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_server;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(getActivity());
        mShopServer = new ShopServer();
        allData = new ArrayList<>();
        mServerDetailCarWashPart = new ArrayList<>();
        mServerDetailCarImprPart = new ArrayList<>();
        mServerDetailCarKeepPart = new ArrayList<>();
        mServerDetailCarTyrePart = new ArrayList<>();
        mServerDetailCarInstallPart = new ArrayList<>();
//        initData();

        initViewCarWash();
        initViewCarImprove();
        initViewCarKeep();
        initViewCarTyre();
        initViewCarInstall();

        fbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allData.clear();
                gatherData();
            }
        });
    }

    private void initData() {
        ShopServer.ServerDetail temp = new ShopServer.ServerDetail();
        temp.setServerName("Null");
        temp.setServerPrice("Null");
        temp.setServerVIPPrice("Null");
        mServerDetailCarWashPart.add(temp);
        mServerDetailCarImprPart.add(temp);
        mServerDetailCarKeepPart.add(temp);
        mServerDetailCarTyrePart.add(temp);
        mServerDetailCarInstallPart.add(temp);
    }

    private void initViewCarWash() {
        carWashView = View.inflate(getActivity(), R.layout.custom_carwash_choose, null);
        final CheckBox cb_wash_car = carWashView.findViewById(R.id.cb_washcar);
        final CheckBox cb_wash_5 = carWashView.findViewById(R.id.cb_normal_5);
        final CheckBox cb_wash_7 = carWashView.findViewById(R.id.cb_normal_7);
        final CheckBox cb_inside = carWashView.findViewById(R.id.cb_inside);
        final CheckBox cb_ad_wash = carWashView.findViewById(R.id.cb_ad_wash);
        final CheckBox cb_ad_wash_deep = carWashView.findViewById(R.id.cb_ad_wash_deep);
        final CheckBox cb_deodorization = carWashView.findViewById(R.id.cb_deodorization);
        FlatButton fb_check = carWashView.findViewById(R.id.btn_check);
        FlatButton fb_check_off = carWashView.findViewById(R.id.btn_check_off);


        cb_wash_car.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                is0Wash = b;
            }
        });

        cb_wash_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                is5Wash = b;
            }
        });

        cb_wash_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                is7Wahs = b;
            }
        });

        cb_inside.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isInside = b;
            }
        });

        cb_ad_wash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAdWash = b;
            }
        });

        cb_ad_wash_deep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAdWashDeep = b;
            }
        });

        cb_deodorization.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isDedo = b;
            }
        });


        fb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //加入那些东西
                if (is0Wash) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_wash_car.getText().toString().trim()));
                }
                if (is5Wash) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_wash_5.getText().toString().trim()));
                }
                if (is7Wahs) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_wash_7.getText().toString().trim()));
                }
                if (isInside) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_inside.getText().toString().trim()));
                }
                if (isAdWash) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_ad_wash.getText().toString().trim()));
                }
                if (isAdWashDeep) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_ad_wash_deep.getText().toString().trim()));
                }
                if (isDedo) {
                    mServerDetailCarWashPart.add(setDefaultName(cb_deodorization.getText().toString().trim()));
                }
                DialogUIUtils.dismiss(mShowCarWash);
            }
        });

        fb_check_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUIUtils.dismiss(mShowCarWash);
            }
        });

    }

    private void initViewCarImprove() {
        mCarImproveView = View.inflate(getActivity(), R.layout.custom_carimprove_choose, null);
        final CheckBox cb_sticker = mCarImproveView.findViewById(R.id.cb_improve_car_cover);
        final CheckBox cb_crystal = mCarImproveView.findViewById(R.id.cb_improve_car_cover_shine_shine_thing);
        final CheckBox cb_coating = mCarImproveView.findViewById(R.id.cb_improve_car_dumo);
        final CheckBox cb_wax = mCarImproveView.findViewById(R.id.cb_improve_car_dala);
        final CheckBox cb_glaze = mCarImproveView.findViewById(R.id.cb_improve_car_fengyou);
        final CheckBox cb_polishing = mCarImproveView.findViewById(R.id.cb_improve_car_paoguang);

        FlatButton fb_check = mCarImproveView.findViewById(R.id.btn_check);
        FlatButton fb_check_off = mCarImproveView.findViewById(R.id.btn_check_off);

        cb_sticker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpSticky = b;
            }
        });
        cb_crystal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpCrystal = b;
            }
        });
        cb_coating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpCoating = b;
            }
        });
        cb_wax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpWax = b;
            }
        });
        cb_glaze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpGlaze = b;
            }
        });
        cb_polishing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isImpPolishing = b;
            }
        });

        fb_check.setOnClickListener(new View.OnClickListener() {
            //    private boolean isImpSticky, isImpCrystal, isImpCoating, isImpWax, isImpGlaze, isImpPolishing;
            @Override
            public void onClick(View view) {
                if (isImpSticky) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_sticker.getText().toString().trim()));
                }
                if (isImpCrystal) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_crystal.getText().toString().trim()));
                }
                if (isImpCoating) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_coating.getText().toString().trim()));
                }
                if (isImpWax) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_wax.getText().toString().trim()));
                }
                if (isImpGlaze) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_glaze.getText().toString().trim()));
                }
                if (isImpPolishing) {
                    mServerDetailCarImprPart.add(setDefaultName(cb_polishing.getText().toString().trim()));
                }
                DialogUIUtils.dismiss(mShowCarImp);
            }
        });

        fb_check_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUIUtils.dismiss(mShowCarImp);
            }
        });
    }

    private void initViewCarKeep() {
        mCarKeepView = View.inflate(getActivity(), R.layout.custom_carkeep_choose, null);
        final CheckBox cb_change_can = mCarKeepView.findViewById(R.id.cb_keep_car_change_gas);
        final CheckBox cb_gas_can = mCarKeepView.findViewById(R.id.cb_keep_car_gas_can);
        final CheckBox cb_air_can = mCarKeepView.findViewById(R.id.cb_keep_car_air_can);
        final CheckBox cb_change_wiper = mCarKeepView.findViewById(R.id.cb_keep_car_change_wiper);
        FlatButton fb_check = mCarKeepView.findViewById(R.id.btn_check);
        FlatButton fb_check_off = mCarKeepView.findViewById(R.id.btn_check_off);

        cb_change_can.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isKeepChangeGas = b;
            }
        });

        cb_gas_can.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isKeepGasCan = b;
            }
        });

        cb_air_can.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isKeepAirCan = b;
            }
        });

        cb_change_wiper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isKeepChangeWiper = b;
            }
        });
        fb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isKeepChangeGas) {
                    mServerDetailCarKeepPart.add(setDefaultName(cb_change_can.getText().toString().trim()));
                }
                if (isKeepGasCan) {
                    mServerDetailCarKeepPart.add(setDefaultName(cb_gas_can.getText().toString().trim()));
                }
                if (isKeepAirCan) {
                    mServerDetailCarKeepPart.add(setDefaultName(cb_air_can.getText().toString().trim()));
                }
                if (isKeepChangeWiper) {
                    mServerDetailCarKeepPart.add(setDefaultName(cb_change_wiper.getText().toString().trim()));
                }
                DialogUIUtils.dismiss(mShowCarKeep);
            }
        });

        fb_check_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUIUtils.dismiss(mShowCarKeep);
            }
        });
    }

    private void initViewCarTyre() {
        mCarTyreView = View.inflate(getActivity(), R.layout.custom_cartyre_choose, null);
        final CheckBox cb_tyre = mCarTyreView.findViewById(R.id.cb_tyre_tyre);
        final CheckBox cb_balance = mCarTyreView.findViewById(R.id.cb_tyre_balance);
        final CheckBox cb_locate = mCarTyreView.findViewById(R.id.cb_tyre_locate);
        final CheckBox cb_fix = mCarTyreView.findViewById(R.id.cb_tyre_fix);
        FlatButton fb_check = mCarTyreView.findViewById(R.id.btn_check);
        FlatButton fb_check_off = mCarTyreView.findViewById(R.id.btn_check_off);

        cb_tyre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTyreTyre = b;
            }
        });
        cb_balance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTyreBalance = b;
            }
        });
        cb_locate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTyreLocate = b;
            }
        });
        cb_fix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isTyreFix = b;
            }
        });

        fb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTyreTyre) {
                    mServerDetailCarTyrePart.add(setDefaultName(cb_tyre.getText().toString().trim()));
                }
                if (isTyreBalance) {
                    mServerDetailCarTyrePart.add(setDefaultName(cb_balance.getText().toString().trim()));
                }
                if (isTyreLocate) {
                    mServerDetailCarTyrePart.add(setDefaultName(cb_locate.getText().toString().trim()));
                }
                if (isTyreFix) {
                    mServerDetailCarTyrePart.add(setDefaultName(cb_fix.getText().toString().trim()));
                }
                DialogUIUtils.dismiss(mShowCarTyre);
            }
        });

        fb_check_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUIUtils.dismiss(mShowCarTyre);
            }
        });

    }

    private void initViewCarInstall() {
        mCarInstallView = View.inflate(getActivity(), R.layout.custom_carinstall_choose, null);
        final CheckBox cb_cam = mCarInstallView.findViewById(R.id.cb_install_cam);
        final CheckBox cb_temp = mCarInstallView.findViewById(R.id.cb_install_temp);
        final CheckBox cb_cam_temp = mCarInstallView.findViewById(R.id.cb_install_cam_temp);

        FlatButton fb_check = mCarInstallView.findViewById(R.id.btn_check);
        FlatButton fb_check_off = mCarInstallView.findViewById(R.id.btn_check_off);

        cb_cam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isInstallCam = b;

            }
        });
        cb_temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isInstallTemp = b;
            }
        });
        cb_cam_temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isInstallCamAndTemp = b;
            }
        });

        fb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInstallCam) {
                    mServerDetailCarInstallPart.add(setDefaultName(cb_cam.getText().toString().trim()));
                }
                if (isInstallTemp) {
                    mServerDetailCarInstallPart.add(setDefaultName(cb_temp.getText().toString().trim()));
                }
                if (isInstallCamAndTemp) {
                    mServerDetailCarInstallPart.add(setDefaultName(cb_cam_temp.getText().toString().trim()));
                }
                DialogUIUtils.dismiss(mShowInstall);

            }
        });

        fb_check_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUIUtils.dismiss(mShowInstall);
            }
        });


    }


    private ShopServer.ServerDetail setDefaultName(String name) {
        ShopServer.ServerDetail detail = new ShopServer.ServerDetail();
        detail.setServerName(name);
        detail.setServerPrice("0");
        detail.setServerVIPPrice("0");
        return detail;
    }

    private void gatherData() {

        if (mServerDetailCarWashPart != null) {
            allData.addAll(mServerDetailCarWashPart);
        }
        if (mServerDetailCarKeepPart != null) {
            allData.addAll(mServerDetailCarKeepPart);
        }
        if (mServerDetailCarImprPart != null) {
            allData.addAll(mServerDetailCarImprPart);
        }
        if (mServerDetailCarTyrePart != null) {
            allData.addAll(mServerDetailCarTyrePart);
        }
        if (mServerDetailCarInstallPart!= null) {
            allData.addAll(mServerDetailCarInstallPart);
        }
        //发送数据到XX，当点击一次以后变为不可点击
        ShopServer shopServer = new ShopServer();
        shopServer.setServerDetails(allData);
        listterner.processServer(shopServer);
        EventBus.getDefault().post(new NextFragment());
    }

    @OnClick({R.id.fb_car_wash, R.id.fb_car_improve, R.id.fb_car_keep, R.id.fb_car_tyre, R.id.fb_car_install})
    void onClickTab(View view) {
        switch (view.getId()) {
            case R.id.fb_car_wash:
                mServerDetailCarWashPart.clear();
                if (mShowCarWash != null) {

                    mShowCarWash.alertDialog.show();
                } else {

                    mShowCarWash = DialogUIUtils.showCustomAlert(getActivity(), carWashView, Gravity.CENTER, true, false);
                    mShowCarWash.show();
                }
                break;
            case R.id.fb_car_improve:
                mServerDetailCarImprPart.clear();
                if (mShowCarImp != null) {
                    mShowCarImp.alertDialog.show();

                } else {

                    mShowCarImp = DialogUIUtils.showCustomAlert(getActivity(), mCarImproveView, Gravity.CENTER, true, false);
                    mShowCarImp.show();
                }
                break;
            case R.id.fb_car_keep:
                mServerDetailCarKeepPart.clear();
                if (mShowCarKeep != null) {
                    mShowCarKeep.alertDialog.show();
                } else {

                    mShowCarKeep = DialogUIUtils.showCustomAlert(getActivity(), mCarKeepView, Gravity.CENTER, true, false);
                    mShowCarKeep.show();
                }
                break;
            case R.id.fb_car_tyre:
                mServerDetailCarTyrePart.clear();
                if (mShowCarTyre != null) {
                    mShowCarTyre.alertDialog.show();
                } else {
                    mShowCarTyre = DialogUIUtils.showCustomAlert(getActivity(), mCarTyreView, Gravity.CENTER, true, false);
                    mShowCarTyre.show();
                }
                break;

            case R.id.fb_car_install:
                mServerDetailCarInstallPart.clear();
                if (mShowInstall != null) {
                    mShowInstall.alertDialog.show();
                } else {
                    mShowInstall = DialogUIUtils.showCustomAlert(getActivity(), mCarInstallView, Gravity.CENTER, true, false);
                    mShowInstall.show();
                }
                break;
        }
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private ShopServerFragment.FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        //方法在这里
        void processServer(ShopServer server);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof ShopInfoFragment.FragmentInteraction) {
            listterner = (ShopServerFragment.FragmentInteraction) getActivity(); // 2.2 获取到宿主activity并赋值
        } else {
            Log.e("GOODSINFOFRAGMENT", "activity must implements FragmentInteraction");
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }

}
