package sjhj.niuniushop.ye.nnmanager.feature;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.badoualy.stepperindicator.StepperIndicator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopAddtionFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopDoneFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopInfoFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopServerFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopInfo;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopServer;
import sjhj.niuniushop.ye.nnmanager.network.event.NextFragment;
import sjhj.niuniushop.ye.nnmanager.network.event.PrewFragment;
import sjhj.niuniushop.ye.nnmanager.network.event.UpLoadEvent;

/**
 * Created by ye on 2017/12/21.
 */

public class UploadShopActivity extends BaseActivity implements ShopInfoFragment.FragmentInteraction, ShopServerFragment.FragmentInteraction, ShopAddtionFragment.FragmentInteraction {

    @BindView(R.id.vp_upload)
    ViewPager mViewPager;

    @BindView(R.id.si_show_process)
    StepperIndicator mIndicator;

    private ShopInfo mShopInfo;

    private ShopServer mShopServer;

    private ShopInfo.BaseInfo mBaseInfo;
    private String mAddtion;
    private String mainUserName;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_uploadshop;
    }

    @Override
    protected void initView() {
        mShopInfo = new ShopInfo();
        mShopServer = new ShopServer();
        mBaseInfo = new ShopInfo.BaseInfo();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShopInfoFragment());
        fragments.add(new ShopServerFragment());
        fragments.add(new ShopAddtionFragment());
        fragments.add(new ShopDoneFragment());

        //添加进入fragment中
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        mIndicator.setViewPager(mViewPager, false);
    }


    @Override
    public void onEvent(PrewFragment event) {
        super.onEvent(event);
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        mIndicator.onPageSelected(mViewPager.getCurrentItem());
    }

    @Override
    public void onEvent(NextFragment event) {
        super.onEvent(event);
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        mIndicator.onPageSelected(mViewPager.getCurrentItem());
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(UpLoadEvent event) {

        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setAddtion(mAddtion);
        shopInfo.setBaseInfo(mBaseInfo);
        shopInfo.setUserName(mainUserName);
        shopInfo.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toasty.success(UploadShopActivity.this, "1").show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toasty.error(UploadShopActivity.this, "1+F" + s).show();
            }
        });

        mShopServer.setServerAvail("1");
        mShopServer.setShopName(mainUserName);
        mShopServer.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toasty.success(UploadShopActivity.this, "2").show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toasty.error(UploadShopActivity.this, "2+F" + s).show();
            }
        });


    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }

    @Override
    public void processBaseInfo(ShopInfo.BaseInfo baseInfo) {
        mBaseInfo = baseInfo;
        mainUserName = baseInfo.getShopMainName();
    }

    @Override
    public void processServer(ShopServer server) {
        mShopServer = server;
    }

    @Override
    public void processAddtion(String addtion) {
        mAddtion = addtion;
    }


    class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);
        }
    }

}


