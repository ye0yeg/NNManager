package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.badoualy.stepperindicator.StepperIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopAddtionFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopDoneFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopInfoFragment;
import sjhj.niuniushop.ye.nnmanager.feature.fragment.ShopServerFragment;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;

/**
 * Created by ye on 2017/12/21.
 */

public class UploadShopActivity extends BaseActivity {

    @BindView(R.id.vp_upload)
    ViewPager mViewPager;

    @BindView(R.id.si_show_process)
    StepperIndicator mIndicator;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_uploadshop;
    }

    @Override
    protected void initView() {
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
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

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


