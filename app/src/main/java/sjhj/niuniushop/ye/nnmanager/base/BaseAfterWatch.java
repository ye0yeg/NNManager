package sjhj.niuniushop.ye.nnmanager.base;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by ye on 2017/12/25.
 */

public abstract class BaseAfterWatch implements TextWatcher {


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        BaseAfterChange(editable);

    }

    protected abstract void BaseAfterChange(Editable editable);

}
