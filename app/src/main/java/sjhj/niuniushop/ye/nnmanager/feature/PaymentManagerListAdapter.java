package sjhj.niuniushop.ye.nnmanager.feature;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseListAdapter;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by ye on 2017/11/9.
 */

abstract class PaymentManagerListAdapter extends BaseListAdapter<MyBmobUser, PaymentManagerListAdapter.ViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_order_user;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    protected abstract void onSudoChanger(MyBmobUser myBmobUser);


    class ViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.text_user_name)
        TextView tvUsername;
        @BindView(R.id.text_signin_time)
        TextView tvSigninTime;
        @BindView(R.id.text_sudo)
        TextView tvSudo;
        @BindView(R.id.btn_change_sudo)
        Button btnSudo;
        @BindView(R.id.layout_goods)
        LinearLayout goodsLayout;
        private MyBmobUser mMyBmobUser;

        ViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void bind(int position) {
            mMyBmobUser = getItem(position);
            String orderSn = getContext().getString(R.string.user_is_name, mMyBmobUser.getName());
            tvUsername.setText(orderSn);

            String orderTime = getContext().getString(R.string.user_is_time, mMyBmobUser.getCreatedAt());
            tvSigninTime.setText(orderTime);

            String levelName;
            if (mMyBmobUser.getRank_level() == 0) {
                levelName = "车主";
            } else if (mMyBmobUser.getRank_level() == 1) {
                levelName = "经销商";
            } else {
                levelName = "VIP";
            }

            String level = getContext().getString(R.string.user_is_sudo, levelName);
            tvSudo.setText(level);

            goodsLayout.removeAllViews();
        }

        @OnClick(R.id.btn_change_sudo)
        void onClick(View view) {
            if (view.getId() == R.id.btn_change_sudo) {
                onSudoChanger(mMyBmobUser);
            }

        }

    }
}