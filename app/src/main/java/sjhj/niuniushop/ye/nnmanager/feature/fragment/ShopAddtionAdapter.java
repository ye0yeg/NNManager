package sjhj.niuniushop.ye.nnmanager.feature.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import sjhj.niuniushop.ye.nnmanager.R;
import sjhj.niuniushop.ye.nnmanager.network.entity.ShopServer;

/**
 * Created by chwin on 2017/12/26.
 */

class ShopAddtionAdapter extends RecyclerView.Adapter<ShopAddtionAdapter.ViewHolder> {

    ArrayList<ShopServer.ServerDetail> serverDetails;

    ShopAddtionAdapter(ArrayList<ShopServer.ServerDetail> details) {
        serverDetails = new ArrayList<>();
        serverDetails = details;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addtion, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return serverDetails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
            TextView serverName = view.findViewById(R.id.server_item_name);
            MaterialEditText serverPrice = view.findViewById(R.id.server_item_price);
            MaterialEditText VIPServer = view.findViewById(R.id.server_item_VIP_price);

        }
    }
}
