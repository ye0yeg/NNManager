package sjhj.niuniushop.ye.nnmanager.network.core;


import android.support.annotation.NonNull;

import java.util.List;

import sjhj.niuniushop.ye.nnmanager.network.entity.Address;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobCarGoods;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobSession;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

public interface IUserManager {

    // --------账号管理-------- //
    void setUser(@NonNull MyBmobUser user, @NonNull MyBmobSession session);

    void setCarList(List<MyBmobCarGoods> goodslist, MyBmobCarGoods.CartBill cartBill);

    void retrieveUserInfo();

    void clear();

    boolean hasUser();

    MyBmobSession getSession();

    MyBmobUser getUser();


    // --------购物车管理-------- //
    void retrieveCartList();

    boolean hasCart();

    List<MyBmobCarGoods> getCartGoodsList();

//    List<CartGoods> getCartGoodsList();


    MyBmobCarGoods.CartBill getCartBill();
//    CartBill getCartBill();

    // --------收件地址管理-------- //

    void retrieveAddressList();

    boolean hasAddress();

    List<Address> getAddressList();

    Address getDefaultAddress();
}
