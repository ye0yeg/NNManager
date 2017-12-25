package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dd.processbutton.FlatButton;
import com.dd.processbutton.ProcessButton;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;
import es.dmoral.toasty.Toasty;
import me.iwf.photopicker.PhotoPicker;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseAfterWatch;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopInfo;
import sjhj.niuniushop.ye.nnmanager.network.event.NextFragment;

import static com.dou361.dialogui.DialogUIUtils.showToast;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopInfoFragment extends BaseFragment {

    private boolean isShopName, isShopOnwer, isShopMianName, isShopLevel, isShopIntro, isShopContact, isAddress, isUploadPic;

    private ArrayList<ShopInfo> mShopInfos;

    @BindView(R.id.edit_shop_name)
    EditText edShopName;
    @BindView(R.id.edit_owner_name)
    EditText edOwner;
    @BindView(R.id.edit_level)
    EditText edLevel;
    @BindView(R.id.edit_intro)
    EditText edIntro;
    @BindView(R.id.edit_address)
    EditText edAddress;

    @BindView(R.id.edit_contact)
    EditText edContact;
    @BindView(R.id.edit_main_name)
    EditText edMainName;

    @BindView(R.id.dect_shop_name)
    ImageView dectShopName;

    @BindView(R.id.dect_owner_name)
    ImageView dectOwnerName;

    @BindView(R.id.dect_main_name)
    ImageView dectMainName;

    @BindView(R.id.dect_level)
    ImageView dectLevel;

    @BindView(R.id.dect_intro)
    ImageView dectIntro;

    @BindView(R.id.dect_contact)
    ImageView dectContact;

    @BindView(R.id.dect_address)
    ImageView dectAddress;

    @BindView(R.id.btnUpload)
    ProcessButton btnUpload;

    @BindView(R.id.fb_next)
    FlatButton fbNext;

    private Context mContext = getContext();
    private ArrayList<String> mPhotos;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_info;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(getContext());
        initEvent();
        dectEditText();
    }

    private void initEvent() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TieBean> strings = new ArrayList<TieBean>();
                strings.add(new TieBean("拍照"));
                strings.add(new TieBean("从相册中选择"));
                DialogUIUtils.showSheet(getActivity(), strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        if (position == 0) {

                        } else if (position == 1) {
                            PhotoPicker.builder()
                                    .setPhotoCount(9)
                                    .setShowCamera(true)
                                    .setShowGif(true)
                                    .setPreviewEnabled(false)
                                    .start(getActivity(), ShopInfoFragment.this, PhotoPicker.REQUEST_CODE);
                        }
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("取消");
                    }
                }).show();
            }
        });


        fbNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showError();
            }
        });
    }

    private void dectEditText() {
        edShopName.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() < 4) {
                    isShopName = setRedLight(dectShopName);
                } else {
                    isShopName = setGreenLight(dectShopName);
                }
            }
        });


        edOwner.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() < 2 || editable.length() > 7) {
                    isShopOnwer = setRedLight(dectOwnerName);
                } else {
                    isShopOnwer = setGreenLight(dectOwnerName);
                }
            }
        });


        edMainName.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() != 13) {
                    isShopMianName = setRedLight(dectMainName);
                } else {
                    isShopMianName = setGreenLight(dectMainName);
                }
            }
        });

        edLevel.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() < 2 || editable.length() > 7) {
                    isShopLevel = setRedLight(dectLevel);
                } else {
                    isShopLevel = setGreenLight(dectLevel);
                }
            }
        });

        edIntro.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() == 0) {
                    isShopIntro = setRedLight(dectIntro);
                } else {
                    isShopIntro = setGreenLight(dectIntro);
                }
            }
        });

        edContact.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() == 0) {
                    isShopContact = setRedLight(dectContact);
                } else {
                    isShopContact = setGreenLight(dectContact);
                }
            }
        });
        edAddress.addTextChangedListener(new BaseAfterWatch() {
            @Override
            protected void BaseAfterChange(Editable editable) {
                if (editable.toString().length() == 0) {
                    isAddress = setRedLight(dectAddress);
                } else {
                    isAddress = setGreenLight(dectAddress);
                }
            }
        });


    }

    private boolean setGreenLight(ImageView light) {
        light.setVisibility(View.VISIBLE);
        light.setImageResource(R.drawable.ic_selected);
        return true;
    }

    private boolean setRedLight(ImageView light) {
        light.setVisibility(View.VISIBLE);
        light.setImageResource(R.drawable.ic_close);
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                //This photos is an ArrayList！！！ for url！！
                mPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final String[] heightArray = mPhotos.toArray(new String[0]);

                //选出来的照片
                //图片的链接
                BmobFile.uploadBatch(getActivity(), heightArray, new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list, List<String> list1) {
                        if (list1.size() == heightArray.length) {//如果数量相等，则代表文件全部上传完成
                            isUploadPic = true;
                            System.gc();
                        }
                        //图上传。
                        //list1 为回调的url - > 和手机（用户）
                    }

                    @Override
                    public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                        btnUpload.setProgress(totalPercent);
                    }

                    @Override
                    public void onError(int i, String s) {
                        btnUpload.setText("上传失败，请重试！");
                    }
                });

            }
        }
    }

    private void showError() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isShopName) {
                    Toasty.info(getActivity(), "请输入正确店名！").show();
                    return;
                } else if (!isShopOnwer) {
                    Toasty.info(getActivity(), "请输入正确店主名！").show();
                    return;
                } else if (!isShopMianName) {
                    Toasty.info(getActivity(), "请输入注册手机号！").show();
                    return;
                } else if (!isShopLevel) {
                    Toasty.info(getActivity(), "请输入店铺等级！").show();
                    return;
                } else if (!isShopIntro) {
                    Toasty.info(getActivity(), "请输入店铺介绍！").show();
                    return;
                } else if (!isShopContact) {
                    Toasty.info(getActivity(), "请输入联系人，可以逗号隔开！").show();
                    return;
                } else if (!isAddress) {
                    Toasty.info(getActivity(), "请输入正确地址！").show();
                    return;
                }
            }
        });


        if (isShopName && isShopOnwer && isShopMianName && isShopContact && isShopLevel && isShopIntro && isShopContact && isAddress) {
            //下一步操作
            // 收集信息
            ShopInfo.BaseInfo baseInfo = new ShopInfo.BaseInfo();
            baseInfo.setShopName(edShopName.getText().toString());
            baseInfo.setShopOwner(edShopName.getText().toString());
            baseInfo.setShopMainName(edMainName.getText().toString());
            baseInfo.setShopLevel(edLevel.getText().toString());
            baseInfo.setShopIntro(edIntro.getText().toString());
            baseInfo.setShopContact(edContact.getText().toString());
            baseInfo.setAddress(edAddress.getText().toString());
            baseInfo.setPics(mPhotos);
            //好了.
            ohSweet(); //Oh!Sweet!
            listterner.processBaseInfo(baseInfo);
            EventBus.getDefault().post(new NextFragment());
        }
    }


    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    private void ohSweet() {
        //ohSweet!  There is nothing!!
    }


    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private FragmentInteraction listterner;

    // 1 定义了所有activity必须实现的接口方法
    public interface FragmentInteraction {
        //方法在这里
        void processBaseInfo(ShopInfo.BaseInfo baseInfo);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listterner = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof FragmentInteraction) {
            listterner = (FragmentInteraction) getActivity(); // 2.2 获取到宿主activity并赋值
        } else {
            Log.e("GOODSINFOFRAGMENT", "activity must implements FragmentInteraction");
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }

    }


}
