package sjhj.niuniushop.ye.nnmanager.network.api;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import sjhj.niuniushop.ye.nnmanager.network.core.ApiInterface;
import sjhj.niuniushop.ye.nnmanager.network.core.ApiPath;
import sjhj.niuniushop.ye.nnmanager.network.core.RequestParam;
import sjhj.niuniushop.ye.nnmanager.network.core.ResponseEntity;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobUser;

public class ApiUserInfo implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.USER_INFO;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return new Req();
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data") private MyBmobUser mUser;

        public MyBmobUser getUser() {
            return mUser;
        }
    }
}
