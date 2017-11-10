package sjhj.niuniushop.ye.nnmanager.feature;

import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.dou361.dialogui.listener.DialogUIItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.ProgressWrapper;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.PtrWrapper;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by chwin on 2017/11/9.
 */

public class CustomerActivity extends BaseActivity {


    private static final int DONE = 1001;
    private static final int CHANGESUCCESS = 1002;

    private ProgressWrapper mProgressWrapper;

    private PtrWrapper mPtrWrapper;

    private OrderListAdapter mOrderListAdapter;

    private ArrayList<MyBmobUser> mBmobUserArrayList;


    @BindView(R.id.list_cust)
    ListView userListView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == DONE) {
                mOrderListAdapter.reset(mBmobUserArrayList);
                mPtrWrapper.stopRefresh();
            } else if (msg.what == CHANGESUCCESS) {
                mPtrWrapper.postRefresh(50);
            }

        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_customer_list;
    }

    @Override
    protected void initView() {
        DialogUIUtils.init(this);
        mBmobUserArrayList = new ArrayList<>();

        mOrderListAdapter = new OrderAdapter();
        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                getUserData();
            }
        };
        mPtrWrapper.postRefresh(50);
        mProgressWrapper = new ProgressWrapper();
        userListView.setAdapter(mOrderListAdapter);

    }

    private void getUserData() {
        reset();
        BmobQuery<MyBmobUser> myBmobUserBmobQuery = new BmobQuery<>();
        myBmobUserBmobQuery.order("-createdAt");
        myBmobUserBmobQuery.findObjects(CustomerActivity.this, new FindListener<MyBmobUser>() {
            @Override
            public void onSuccess(List<MyBmobUser> list) {
                MyBmobUser myBmobUser = new MyBmobUser();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getRecomNumber().equals(UserManager.getInstance().getUser().getName())) {
                        mBmobUserArrayList.add(list.get(i));
                    }
                    mHandler.sendEmptyMessage(DONE);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    private void reset() {
        mBmobUserArrayList.clear();
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    /**
     * 逻辑 : 显示所有该用户旗下的所有用户
     */

    public class OrderAdapter extends OrderListAdapter {

        String[] levelName = {"车主", "经销商", "VIP"};

        @Override
        protected void onSudoChanger(final MyBmobUser myBmobUser) {

            int level = myBmobUser.getRank_level();
            Toasty.info(getApplication(), "修改的用户为:" + myBmobUser.getName()).show();
            //修改权限的
            DialogUIUtils.showSingleChoose(CustomerActivity.this, "权限修改", level, levelName, new DialogUIItemListener() {
                @Override
                public void onItemClick(CharSequence text, int position) {
                    myBmobUser.setRank_level(position);
                    myBmobUser.update(CustomerActivity.this, myBmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void onSuccess() {
                            Toasty.success(getApplicationContext(), "权限修改成功!").show();
                            System.out.println("权限修改成功！");
                            mHandler.sendEmptyMessage(CHANGESUCCESS);
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toasty.error(getApplicationContext(), "权限修改失败").show();
                        }
                    });
                }
            }).show();

        }
    }


}
