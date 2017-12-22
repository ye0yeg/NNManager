package sjhj.niuniushop.ye.nnmanager.base.wrapper;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;

public class PhotoWrapper extends DialogFragment {

    @BindView(R.id.image_photo)
    ImageView ivPhoto;

    private Unbinder mUnbinder;
    private String mUrl;

    public PhotoWrapper() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));  // 无背景色
        window.requestFeature(Window.FEATURE_NO_TITLE);  // 无标题栏

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_dialog, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ivPhoto.setImageDrawable(new ColorDrawable());

        Glide.with(this)
                .load(mUrl)
                .into(ivPhoto);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;

        window.setAttributes(windowParams);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mUnbinder = null;
    }

    public void showPhoto(BaseFragment fragment, String url) {
        if (isAdded()) return;
        mUrl = url;
        show(fragment.getChildFragmentManager(), "PhotoWrapper");
    }

    public void showPhoto(BaseActivity activity, String url) {
        if (isAdded()) return;
        mUrl = url;
        show(activity.getSupportFragmentManager(), "PhotoWrapper");
    }
}
