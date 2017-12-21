package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

import static android.app.Activity.RESULT_OK;
import static com.dou361.dialogui.DialogUIUtils.showToast;

/**
 * Created by ye on 2017/12/21.
 */

public class ShopInfoFragment extends BaseFragment {

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
    @BindView(R.id.edit_upload_photo)
    AppCompatEditText edUploadPhoto;
    @BindView(R.id.text_upload)
    TextView tvUpload;


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
                        if(position == 0){

                        }else if(position ==1){
                            PhotoPicker.builder()
                                    .setPhotoCount(9)
                                    .setShowCamera(true)
                                    .setShowGif(true)
                                    .setPreviewEnabled(false)
                                    .start(getActivity(), PhotoPicker.REQUEST_CODE);
                        }
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("取消");
                    }
                }).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

}
