package sjhj.niuniushop.ye.nnmanager.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import sjhj.niuniushop.ye.nnmanager.network.core.ApiInterface;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.event.AddressSaveEvent;
import sjhj.niuniushop.ye.nnmanager.network.event.UserEvent;

/**
 * 通用Fragment基类.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewLayout(), container, false);
        initMyView();
        ButterKnife.bind(this, view);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected void initMyView() {
    }

    ;

    @Override
    public final void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        EventBus.getDefault().register(this);
    }


    @Override
    public final void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        mUnbinder.unbind();
        mUnbinder = null;
    }

    /*
    * Okhttp方法用来网络请求
    *
    * */
    protected final Call enqueue(final ApiInterface apiInterface, boolean isSJ) {
        return null;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(UserEvent event) {
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AddressSaveEvent event) {
    }

    @LayoutRes
    protected abstract int getContentViewLayout();

    protected abstract void initView();

    protected abstract void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp);

}
