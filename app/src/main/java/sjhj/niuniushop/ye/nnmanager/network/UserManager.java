package sjhj.niuniushop.ye.nnmanager.network;


import android.support.annotation.NonNull;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import sjhj.niuniushop.ye.nnmanager.network.api.ApiUserInfo;
import sjhj.niuniushop.ye.nnmanager.network.core.IUserManager;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.core.UiCallback;
import sjhj.niuniushop.ye.nnmanager.network.entity.Address;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobCarGoods;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobSession;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;
import sjhj.niuniushop.ye.nnmanager.network.event.AddressEvent;
import sjhj.niuniushop.ye.nnmanager.network.event.CartEvent;
import sjhj.niuniushop.ye.nnmanager.network.event.UserEvent;

public class UserManager implements IUserManager {

    private static IUserManager sInstance = new UserManager();

    public static IUserManager getInstance() {
        return sInstance;
    }


    private EventBus mBus = EventBus.getDefault();

    private MyBmobSession mSession;

    private MyBmobUser mUser;

    private List<MyBmobCarGoods> mCartGoodsList;

    private MyBmobCarGoods.CartBill mCartBill;

    private List<Address> mAddressList;

    @Override
    public void setUser(@NonNull MyBmobUser user, @NonNull MyBmobSession session) {
        mUser = user;
        mSession = session;

        mBus.postSticky(new UserEvent());
        retrieveCartList();
        retrieveAddressList();
    }

    public void setCarList(List<MyBmobCarGoods> goodslist, MyBmobCarGoods.CartBill cartBill) {
        mCartGoodsList = goodslist;
        mCartBill = cartBill;

        mBus.postSticky(new UserEvent());
        retrieveCartList();
        retrieveAddressList();
    }

    @Override
    public void retrieveUserInfo() {
        ApiUserInfo apiUserInfo = new ApiUserInfo();
        UiCallback callback = new UiCallback() {
            @Override
            public void onBusinessResponse(boolean success, ResponseEntity responseEntity) {
                if (success) {
//                    ApiUserInfo.Rsp userRsp = (ApiUserInfo.Rsp) responseEntity;
                    mUser = getUser();
                }
                mBus.postSticky(new UserEvent());
            }
        };
//        mClient.enqueue(apiUserInfo, callback, getClass().getSimpleName());
    }

    @Override
    public void retrieveCartList() {

//        mClient.enqueue(apiCartList, cb, getClass().getSimpleName());
    }

    @Override
    public void retrieveAddressList() {

//        mClient.enqueue(apiAddressList, uiCallback, getClass().getSimpleName());
    }

    @Override
    public Address getDefaultAddress() {
        if (hasAddress()) {
            for (Address address : mAddressList) {
                if (address.isDefault()) return address;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        mUser = null;
        mSession = null;
        mCartBill = null;
        mCartGoodsList = null;

//        mClient.cancelByTag(getClass().getSimpleName());

        mBus.postSticky(new UserEvent());
        mBus.postSticky(new CartEvent());
        mBus.postSticky(new AddressEvent());
    }

    @Override
    public boolean hasUser() {
        return mSession != null && mUser != null;
    }

    @Override
    public boolean hasCart() {
        return mCartGoodsList != null && !mCartGoodsList.isEmpty();
    }

    @Override
    public boolean hasAddress() {
        return mAddressList != null && !mAddressList.isEmpty();
    }

    @Override
    public MyBmobSession getSession() {
        return mSession;
    }

    @Override
    public MyBmobUser getUser() {
        return mUser;
    }

    @Override
    public List<MyBmobCarGoods> getCartGoodsList() {
        return mCartGoodsList;
    }

    @Override
    public MyBmobCarGoods.CartBill getCartBill() {
        return mCartBill;
    }

    @Override
    public List<Address> getAddressList() {
        return mAddressList;
    }

}
