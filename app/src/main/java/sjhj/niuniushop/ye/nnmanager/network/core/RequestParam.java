package sjhj.niuniushop.ye.nnmanager.network.core;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import sjhj.niuniushop.ye.nnmanager.network.UserManager;
import sjhj.niuniushop.ye.nnmanager.network.entity.MyBmobSession;

/**
 * 请求参数的基类.
 */
public abstract class RequestParam {

    public static final int SESSION_NO_NEED = 0;
    public static final int SESSION_OPTIONAL = 1;
    public static final int SESSION_MANDATORY = 2;

    @IntDef({SESSION_NO_NEED, SESSION_OPTIONAL, SESSION_MANDATORY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionUsage {
    }

    @SerializedName("session") private MyBmobSession mSession;

    public RequestParam() {

        int usage = sessionUsage();

        if (usage == SESSION_NO_NEED) return;

        MyBmobSession session = UserManager.getInstance().getSession();

//        if (usage == SESSION_MANDATORY && session == null) {
//            throw new IllegalStateException("Session is mandatory.");
//        }
        mSession = session;
    }

    @SessionUsage protected abstract int sessionUsage();
}
