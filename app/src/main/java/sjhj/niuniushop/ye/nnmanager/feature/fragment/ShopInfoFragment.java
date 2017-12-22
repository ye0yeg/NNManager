package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadBatchListener;
import es.dmoral.toasty.Toasty;
import me.iwf.photopicker.PhotoPicker;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

import static com.dou361.dialogui.DialogUIUtils.showToast;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopInfoFragment extends BaseFragment {

    private boolean isShopName, isShopOnwer, isShopMianName, isShopLevel, isShopIntro, isShopContact, isAddress, isUploadPic;

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
    @BindView(R.id.text_upload)
    TextView tvUpload;

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

    @BindView(R.id.dect_upload)
    ImageView dectUpload;


    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_shop_info;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(getContext());
        tvUpload.setOnClickListener(new View.OnClickListener() {
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
        dectEditText();

    }

    private void dectEditText() {
        edShopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() < 4) {
                    isShopName = setRedLight(dectShopName);
                } else {
                    isShopName = setGreenLight(dectShopName);
                }
            }
        });
        edOwner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() < 2 || editable.length() > 7) {
                    isShopOnwer = setRedLight(dectOwnerName);
                } else {
                    isShopOnwer = setGreenLight(dectOwnerName);
                }

            }
        });
        edMainName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() != 13) {
                    isShopMianName = setRedLight(dectMainName);
                } else {
                    isShopMianName = setGreenLight(dectMainName);
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
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final String[] heightArray = photos.toArray(new String[0]);

                //选出来的照片
                //图片的链接
                BmobFile.uploadBatch(getActivity(), heightArray, new UploadBatchListener() {
                    @Override
                    public void onSuccess(List<BmobFile> list, List<String> list1) {
                        if (list1.size() == heightArray.length) {//如果数量相等，则代表文件全部上传完成
                            Toasty.success(getActivity(), "上传成功。").show();
                        }
                        //图上传。
                        //list1 为回调的url - > 和手机（用户）


                    }

                    @Override
                    public void onProgress(int i, int i1, int i2, int i3) {

                        Toasty.info(getActivity(), "上传中。").show();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toasty.error(getActivity(), "上传失败，请重试！").show();
                    }
                });

            }
        }
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    private void ohSweet() {
        //ohSweet!  There is nothing!!
    }

}
