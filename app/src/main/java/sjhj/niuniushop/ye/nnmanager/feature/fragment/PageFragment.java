package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by ye on 2017/12/21.
 */

public class PageFragment extends Fragment{
    private TextView lblPage;

    public static PageFragment newInstance(int page, boolean isLast) {
        Bundle args = new Bundle();
        //如果是最后一个，就是last
        args.putInt("page", page);
        if (isLast)
            args.putBoolean("isLast", true);
        //这里回传PageFragment的东西
        final PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    //View

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final int page = getArguments().getInt("page", 0);
        if (getArguments().containsKey("isLast"))
            lblPage.setText("You're done!");
        else
            lblPage.setText(Integer.toString(page));
    }

}
