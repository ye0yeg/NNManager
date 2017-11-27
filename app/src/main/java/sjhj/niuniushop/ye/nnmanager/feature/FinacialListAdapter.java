package sjhj.niuniushop.ye.nnmanager.feature;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.base.BaseListAdapter;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobPayment;

/**
 * Created by ye on 2017/11/9.
 */

abstract class FinacialListAdapter extends BaseListAdapter<MyBmobPayment, FinacialListAdapter.ViewHolder> {
    // 仓库审核，需要添加一个字段用来审核仓库。

    //refresh_logo_inactive , activited
    //财务是审核订单，当审核通过以后，才可进入仓库

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_finacail;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    protected abstract void onSudoChanger(MyBmobPayment myBmobUser);


    class ViewHolder extends BaseListAdapter.ViewHolder {

        private MyBmobPayment mMyBmobPayment;

        @BindView(R.id.layout_goods)
        LinearLayout goodsLayout;

        @BindView(R.id.text_order_sn)
         TextView tvDetail;
        @BindView(R.id.text_order_time)
         TextView tvOrderTime;
        @BindView(R.id.text_order_number)
         TextView tvOrderNumber;
        @BindView(R.id.text_order_attach)
         TextView tvAttach;
        @BindView(R.id.text_order_total)
         TextView tvTotal;

        @BindView(R.id.button_confirm)
         Button btnConfirm;

        ViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void bind(int position) {
            mMyBmobPayment = getItem(position);
            String orderSn = getContext().getString(R.string.payment_is_detail, mMyBmobPayment.getGoodTitle());
            tvDetail.setText(orderSn);

            String orderTime = getContext().getString(R.string.payment_is_order_time, mMyBmobPayment.getCreatedAt());
            tvOrderTime.setText(orderTime);

            String orderNumber = getContext().getString(R.string.payment_is_order_number, mMyBmobPayment.getOrdernumber());
            tvOrderNumber.setText(orderNumber);

//            String attach = getContext().getString(R.string.payment_is_address, mMyBmobPayment

            String total = getContext().getString(R.string.payment_is_order_total,mMyBmobPayment.getGoodTotal()+"");
            tvTotal.setText(total);

            goodsLayout.removeAllViews();
        }

        @OnClick(R.id.button_confirm)
        void onClick(View view) {
            if (view.getId() == R.id.button_confirm) {
                onSudoChanger(mMyBmobPayment);
            }

        }

    }
}