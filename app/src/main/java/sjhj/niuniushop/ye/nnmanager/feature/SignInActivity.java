package sjhj.niuniushop.ye.nnmanager.feature;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseActivity;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.ProgressWrapper;
import sjhj.niuniushop.ye.nnmanager.base.wrapper.ToolbarWrapper;
import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobSession;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;
import sjhj.niuniushop.ye.nnmanager.network.entity.NNUser;

/**
 * Created by ye on 2017/11/8.
 */

public class SignInActivity extends BaseActivity {

    private static final int HASUSER = 1001;
    private static final int LOGINSUCCESS = 1002;

    @BindView(R.id.edit_name)
    EditText etName;
    @BindView(R.id.edit_password)
    EditText etPassword;
    @BindView(R.id.button_signin)
    Button btnSignIn;

    private ProgressWrapper mProgressWrapper;

    private String mUsername;
    private String mPassword;
    private MyBmobUser mMyBmobUser;
    private MyBmobSession mMyBmobSession;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == HASUSER) {

            } else if (msg.what == LOGINSUCCESS) {
                Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initView() {
        mMyBmobUser = new MyBmobUser();
        mMyBmobSession = new MyBmobSession();
        Intent intent = getIntent();
        Boolean logout = intent.getBooleanExtra("LOGINOUT", false);
        new ToolbarWrapper(this).setCustomTitle(R.string.mine_title_sign_in);
        mProgressWrapper = new ProgressWrapper();
        final BmobUser nnUser = NNUser.getCurrentUser(getApplicationContext());
        if (nnUser != null && logout == false) {
            BmobQuery<MyBmobSession> querySeeeion = new BmobQuery<MyBmobSession>();
            querySeeeion.addWhereEqualTo("name", nnUser.getUsername());
            querySeeeion.findObjects(SignInActivity.this, new FindListener<MyBmobSession>() {
                @Override
                public void onSuccess(List<MyBmobSession> list) {
//                        Toasty.success(SignInActivity.this, "查询到：" + list.size() + "条数据").show();
                    if (list.size() == 0) {
                        finish();
                        return;
                    }
                    mMyBmobSession = list.get(0);
                    BmobQuery<MyBmobUser> queryUser = new BmobQuery<MyBmobUser>();
                    queryUser.addWhereEqualTo("name", nnUser.getUsername());
                    queryUser.findObjects(SignInActivity.this, new FindListener<MyBmobUser>() {
                        @Override
                        public void onSuccess(List<MyBmobUser> list) {
//                                Toasty.success(SignInActivity.this, "查询到：" + list.size() + "条数据").show();
                            mMyBmobUser = list.get(0);
                            UserManager.getInstance().setUser(
                                    mMyBmobUser, mMyBmobSession
                            );
                            Toasty.success(getApplicationContext(), "欢迎回来！   " + mMyBmobUser.getName()).show();
                            mHandler.sendEmptyMessage(LOGINSUCCESS);
                        }

                        @Override
                        public void onError(int i, String s) {
                            Toasty.error(SignInActivity.this, "ERROR" + s).show();
                        }
                    });

                }

                @Override
                public void onError(int i, String s) {
                    Toasty.error(SignInActivity.this, "ERROR" + s).show();
                }
            });
        } else {

        }


    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {

    }


    @OnTextChanged({R.id.edit_password, R.id.edit_name})
    void onTextChanged() {
        mUsername = etName.getText().toString();
        mPassword = etPassword.getText().toString();

        // 简单的条件判断: 用户名和密码不能为空.
        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)) {
            btnSignIn.setEnabled(false);
        } else {
            btnSignIn.setEnabled(true);
        }
    }

    @OnClick(R.id.button_signin)
    void signIn() {
        mProgressWrapper.showProgress(this);
        NNUser nnUser = new NNUser();
        nnUser.setUsername(mUsername);
        nnUser.setPassword(mPassword);
        nnUser.login(getApplicationContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                Toasty.success(getApplicationContext(), "登陆成功！").show();
                //登陆成功以后就可以将该用户的信息拉取出来使用。
                mHandler.sendEmptyMessage(HASUSER);
                BmobQuery<MyBmobSession> querySeeeion = new BmobQuery<MyBmobSession>();
                querySeeeion.addWhereEqualTo("name", mUsername);
                querySeeeion.findObjects(SignInActivity.this, new FindListener<MyBmobSession>() {
                    @Override
                    public void onSuccess(List<MyBmobSession> list) {
//                        Toasty.success(SignInActivity.this, "查询到：" + list.size() + "条数据").show();
                        mMyBmobSession = list.get(0);
                        BmobQuery<MyBmobUser> queryUser = new BmobQuery<MyBmobUser>();
                        queryUser.addWhereEqualTo("name", mUsername);
                        queryUser.findObjects(SignInActivity.this, new FindListener<MyBmobUser>() {
                            @Override
                            public void onSuccess(List<MyBmobUser> list) {
//                                Toasty.success(SignInActivity.this, "查询到：" + list.size() + "条数据").show();
                                mMyBmobUser = list.get(0);
                                UserManager.getInstance().setUser(
                                        mMyBmobUser, mMyBmobSession
                                );

                                mHandler.sendEmptyMessage(LOGINSUCCESS);
                            }

                            @Override
                            public void onError(int i, String s) {
                                Toasty.error(SignInActivity.this, "ERROR" + s).show();
                            }
                        });

                    }

                    @Override
                    public void onError(int i, String s) {
                        Toasty.error(SignInActivity.this, "ERROR" + s).show();
                    }
                });




                /*
               if (success) {
            ToastWrapper.show(R.string.mine_msg_sign_in_success);
            ApiSignIn.Rsp signInRsp = (ApiSignIn.Rsp) rsp;
            UserManager.getInstance().setUser(
               signInRsp.getData().getUser(), signInRsp.getData().getSession()
            );
            finish();
        }
                * */
                mProgressWrapper.dismiss();
            }

            @Override
            public void onFailure(int i, String s) {
                Toasty.error(getApplicationContext(), "登陆失败！" + s).show();
                mProgressWrapper.dismiss();
            }
        });
    }
}
