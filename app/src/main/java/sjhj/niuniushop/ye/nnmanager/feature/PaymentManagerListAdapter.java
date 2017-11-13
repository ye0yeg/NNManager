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
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

/**
 * Created by ye on 2017/11/9.
 */

abstract class PaymentManagerListAdapter extends BaseListAdapter<MyBmobPayment, PaymentManagerListAdapter.ViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_order_user;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    protected abstract void onSudoChanger(MyBmobPayment myBmobPayment);


    class ViewHolder extends BaseListAdapter.ViewHolder {

        //详情页
        @BindView(R.id.text_user_name)
        TextView tvDetail;

        @BindView(R.id.text_manager_phone_number)
        TextView tvPhone;

        //地址
        @BindView(R.id.text_manager_address)
        TextView tvAddress;

        //收件人
        @BindView(R.id.text_manager_people)
        TextView tvRecipients;

        //订单时间
        @BindView(R.id.text_signin_time)
        TextView tvSigninTime;

        //订单号
        @BindView(R.id.text_payment_ordernumber)
        TextView tvOrderNUmber;

        //订单情况
        @BindView(R.id.text_sudo)
        TextView tvSudo;

        //修改订单按钮
        @BindView(R.id.btn_change_sudo)
        Button btnSudo;

        @BindView(R.id.ll_payment_info)
        LinearLayout llOrderInfo;

        @BindView(R.id.text_payment_info)
        TextView tvOrderInfo;


        @BindView(R.id.layout_goods)
        LinearLayout goodsLayout;


        private MyBmobPayment mMyBmobPayment;

        ViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void bind(int position) {
            mMyBmobPayment = getItem(position);

            tvDetail.setVisibility(View.VISIBLE);
            tvPhone.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.VISIBLE);
            tvRecipients.setVisibility(View.VISIBLE);
            tvSigninTime.setVisibility(View.VISIBLE);
            tvOrderNUmber.setVisibility(View.VISIBLE);
            llOrderInfo.setVisibility(View.VISIBLE);

            String orderSn = getContext().getString(R.string.payment_is_detail, mMyBmobPayment.getGoodTitle());
            tvDetail.setText(orderSn);

            String phone = getContext().getString(R.string.payment_is_phone, mMyBmobPayment.getName());
            tvPhone.setText(phone);

            String address = getContext().getString(R.string.payment_is_address, mMyBmobPayment.getAddress());
            tvAddress.setText(address);

            String reci = getContext().getString(R.string.payment_is_recipients, mMyBmobPayment.getBuyer());
            tvRecipients.setText(reci);

            String orderTime = getContext().getString(R.string.payment_is_order_time, mMyBmobPayment.getCreatedAt());
            tvSigninTime.setText(orderTime);

            String orderNumber = getContext().getString(R.string.payment_is_order_number, mMyBmobPayment.getOrdernumber());
            tvOrderNUmber.setText(orderNumber);


            String shippingState = null;
            if (mMyBmobPayment.getGoodState().getDeliery()) {
                //已发货  -    > 可查看物流 ， 可更新订单号
                shippingState = "已发货！";
                btnSudo.setText("查看物流");

                String orderInfo = "快递名称：" + mMyBmobPayment.getShippingCom() +
                        "    单号：" + mMyBmobPayment.getShippingOrder();
                tvOrderInfo.setText(orderInfo);

            } else if (mMyBmobPayment.getGoodState().getShipping()) {
                // 等待发货
                shippingState = "待发货!";
                btnSudo.setText("修改发货");
            } else if (!mMyBmobPayment.getPay()) {
                //未付款
                shippingState = "未支付!";
                btnSudo.setText("修改状态");
            }
            //发货状态
            String level = getContext().getString(R.string.payment_is_order_state, shippingState);
            tvSudo.setText(level);

            goodsLayout.removeAllViews();
        }

        @OnClick(R.id.btn_change_sudo)
        void onClick(View view) {
            if (view.getId() == R.id.btn_change_sudo) {
                onSudoChanger(mMyBmobPayment);
            }

        }

    }
}