package sjhj.niuniushop.ye.nnmanager.base.wrapper;


import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.ButterKnife;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.BaseFragment;

public class ToolbarWrapper {

    private BaseActivity mBaseActivity;

    private ImageView mLogo;

    @Nullable
    private TextView mTvTitle;

    public ToolbarWrapper(BaseActivity activity) {
        mBaseActivity = activity;
        Toolbar toolbar = ButterKnife.findById(activity, R.id.standard_toolbar);
        init(toolbar);

        setShowBack(true);
        setShowTitle(false);
    }

    public ToolbarWrapper(BaseFragment fragment) {
        mBaseActivity = (BaseActivity) fragment.getActivity();
        //noinspection ConstantConditions
        Toolbar toolbar = ButterKnife.findById(fragment.getView(), R.id.standard_toolbar);
        init(toolbar);
        fragment.setHasOptionsMenu(true); // 设置Fragment有选项菜单.

        setShowBack(false);
        setShowTitle(false);
    }

    public ToolbarWrapper setShowTitle(boolean enable) {
        getSupportActionBar().setDisplayShowTitleEnabled(enable);
        return this;
    }

    public ToolbarWrapper setShowBack(boolean enable) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        return this;
    }

    public ToolbarWrapper setLogoShow(boolean enable) {
        if (enable) {
            mLogo.setVisibility(View.VISIBLE);
        } else {
            mLogo.setVisibility(View.GONE);
        }
        return this;
    }

    public ToolbarWrapper setCustomTitle(int resId) {
        if (mTvTitle == null) {
            throw new UnsupportedOperationException("No title textview in toolbar.");
        }
        mTvTitle.setText(resId);
        return this;
    }

    public ToolbarWrapper setCustomTitle(String resId) {
        mTvTitle.setText("");
        return this;
    }

    private void init(Toolbar toolbar) {
        mTvTitle = ButterKnife.findById(toolbar, R.id.standard_toolbar_title);
        mLogo = ButterKnife.findById(toolbar, R.id.logo_with_word);
        mBaseActivity.setSupportActionBar(toolbar);
    }

    private ActionBar getSupportActionBar() {
        return mBaseActivity.getSupportActionBar();
    }
}
