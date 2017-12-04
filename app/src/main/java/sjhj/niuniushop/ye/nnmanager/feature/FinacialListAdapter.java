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
    private String mState = "无";
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

    protected abstract String getRecom(String myBmobPayment);

    protected abstract String getLevel(String myBmobPayment);


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
        @BindView(R.id.text_apply)
        TextView tvState;
        @BindView(R.id.text_order_person)
        TextView tvPerson;
        @BindView(R.id.text_order_recom)
        TextView tvRecom;
        @BindView(R.id.text_order_level)
        TextView tvLevel;
        @BindView(R.id.text_payment_payment_type)
        TextView tvPayType;

        @BindView(R.id.button_confirm)
        Button btnConfirm;

        ViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        protected void bind(int position) {
            mMyBmobPayment = getItem(position);


            String recom = getRecom(mMyBmobPayment.getName());
            String rankLevel = getLevel(mMyBmobPayment.getName());


            String orderSn = getContext().getString(R.string.payment_is_detail, mMyBmobPayment.getGoodTitle());
            tvDetail.setText(orderSn);

            String orderTime = getContext().getString(R.string.payment_is_order_time, mMyBmobPayment.getCreatedAt());
            tvOrderTime.setText(orderTime);

            String orderNumber = getContext().getString(R.string.payment_is_order_number, mMyBmobPayment.getOrdernumber());
            tvOrderNumber.setText(orderNumber);

            String orderPerson = getContext().getString(R.string.payment_is_recipients, mMyBmobPayment.getBuyer());
            tvPerson.setText(orderPerson);


//            String attach = getContext().getString(R.string.payment_is_address, mMyBmobPayment
            String thePayType = "未填写";
            String typeCode;
            //
            if (mMyBmobPayment.getPayType() == null) {
                //跳出判断
            } else {
                typeCode = mMyBmobPayment.getPayType();

                if (typeCode.equals("1001")) {
                    thePayType = "支付宝支付";
                } else if (typeCode.equals("1002")) {
                    thePayType = "微信支付";
                } else if (typeCode.equals("1003")) {
                    thePayType = "银联支付";
                } else if (typeCode.equals("1004")) {
                    thePayType = "牛牛余额支付";
                } else if (typeCode.equals("1005")) {
                    thePayType = "货到付款";
                }
            }


            String payType = getContext().getString(R.string.payment_is_order_type, thePayType);
            tvPayType.setText(payType);


            //推荐人
            String recomPerson = getContext().getString(R.string.payment_is_order_recom, recom);
            tvRecom.setText(recomPerson);

            //LEVLE
            String level = getContext().getString(R.string.payment_is_order_level, rankLevel);
            tvLevel.setText(level);

            String total = getContext().getString(R.string.payment_is_order_total, mMyBmobPayment.getGoodTotal() + "");
            tvTotal.setText(total);

            if (mMyBmobPayment.getConfirm() == null) {

            } else {

                if (mMyBmobPayment.getConfirm()) {
                    mState = "通过审核！";
                } else {
                    mState = "未通过审核！";
                }

            }
            String State = getContext().getString(R.string.payment_is_order_confirm, mState);
            tvState.setText(State);
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