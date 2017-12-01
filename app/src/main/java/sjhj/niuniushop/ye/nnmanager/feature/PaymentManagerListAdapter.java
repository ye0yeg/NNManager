package sjhj.niuniushop.ye.nnmanager.feature;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.util.ArrayList;

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


    private ArrayList<MyBmobUser> myBmobUserArrayList1 = new ArrayList<>();


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_order_payment;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    protected abstract void onSudoChanger(MyBmobPayment myBmobPayment);

    protected abstract void onShippingCheck(MyBmobPayment myBmobPayment);

    protected abstract String getRecom(MyBmobPayment myBmobPayment);

    public PaymentManagerListAdapter(final ArrayList<MyBmobUser> myBmobUserArrayList) {
        //这玩意儿一直是空的。 但是那玩意儿都有值
//        myBmobUserArrayList1 = myBmobUserArrayList;
    }

    public PaymentManagerListAdapter() {

    }

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

        //订单信息的ll
        @BindView(R.id.ll_payment_info)
        LinearLayout llOrderInfo;

        //支付信息
        @BindView(R.id.text_payment_info)
        TextView tvOrderInfo;

        //推荐人
        @BindView(R.id.text_payment_recom_person)
        TextView tvRecomPerson;

        //备注
        @BindView(R.id.text_payment_note)
        TextView tvNote;

        @BindView(R.id.text_payment_payment_type)
        TextView tvPayType;

        private MyBmobPayment mMyBmobPayment;


        @BindView(R.id.layout_goods)
        LinearLayout goodsLayout;

        private MyBmobPayment.GoodState mGoodState;

        ViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        protected void bind(int position) {
            mMyBmobPayment = getItem(position);


            String recom = "无";
            //查找推荐人
            for (int i = 0; i < myBmobUserArrayList1.size(); i++) {
                if (mMyBmobPayment.getName().equals(myBmobUserArrayList1.get(i).getRecomNumber())) {
                    recom = myBmobUserArrayList1.get(i).getName();
                    continue;
                }
            }

            mGoodState = mMyBmobPayment.getGoodState();
//            String shippingState = null;
//            String orderInfo = null;
            tvDetail.setVisibility(View.VISIBLE);
            tvPhone.setVisibility(View.VISIBLE);
            tvAddress.setVisibility(View.VISIBLE);
            tvRecipients.setVisibility(View.VISIBLE);
            tvSigninTime.setVisibility(View.VISIBLE);
            tvOrderNUmber.setVisibility(View.VISIBLE);
            tvRecomPerson.setVisibility(View.VISIBLE);


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

            //How to get Those Thing

            //分类支付方式

            /*
            * 1001
            * 支付宝
            * 1002
            * 微信
            * 1003
            * 银联
            * 1004
            * 余额
            * 1005
            * 货到付款
    ALI_PAY = 1001;
    WECHAT_PAY = 1002;
    BANKPAY = 1003;
    NIUNIU_PAY = 1004;
    COD = 1005;
            * */

            System.out.println(mMyBmobPayment.getPayType());
            String thePayType = "未填写";
            String typeCode;
            if (mMyBmobPayment.getPayType() != null && !mMyBmobPayment.getPayType().isEmpty()) {
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
            tvRecomPerson.setText(recomPerson);
            //可查看所有已支付和未支付的订单，并且看其类型

            //订单。
            //查看所有订单

//            if (mGoodState.getDeliery()) {
//                llOrderInfo.setVisibility(View.VISIBLE);
//                //已发货  -    > 可查看物流 ， 可更新订单号
//                shippingState = getContext().getString(R.string.payment_is_shiping);
//                btnSudo.setText(getContext().getString(R.string.payment_text_checkshipping));
//                orderInfo = "快递名称：" + mMyBmobPayment.getShippingCom() +
//                        "    单号：" + mMyBmobPayment.getShippingOrder();
//                tvOrderInfo.setText(orderInfo);
//            } else if (mGoodState.getShipping()) {
//                llOrderInfo.setVisibility(View.GONE);
//                // 等待发货
//                shippingState = "待发货!";
//                btnSudo.setText("修改发货");
//            } else if (!mMyBmobPayment.getPay()) {
//                llOrderInfo.setVisibility(View.GONE);
//                //未付款
//                shippingState = "未支付!";
//                btnSudo.setText("修改状态");
//            }

            //金额
            String total = getContext().getString(R.string.payment_is_order_total, mMyBmobPayment.getGoodTotal() + "");
            tvSudo.setText(total);

//            String recom = getContext().getString(R.string.payment_is_order_recom, getRecom(mMyBmobPayment));
//            tvRecomPerson.setText(recom);

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