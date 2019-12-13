package com.example.myapplication5.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication5.R;
import com.example.myapplication5.model.SimDetails;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    private ArrayList<SimDetails> simList;
    private ListItemClickListener listItemClickListener;

    public ItemListAdapter(ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
        simList = new ArrayList<>();
    }

    public void setSimList(ArrayList<SimDetails> simList) {
        this.simList = simList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SimDetails simDetails = simList.get(position);
        //holder.imageView.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(R.drawable.ic_home_black_24dp);
        holder.itemTitle.setText(simDetails.getNetworkProvider());
        String message = simDetails.getDataBand() + " / " + simDetails.getDataLimit() + " / "
                + simDetails.getPrice();
        holder.itemMessage.setText(message);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "
                        + simDetails.getNetworkProvider(),Toast.LENGTH_LONG).show();
                listItemClickListener.onMyListItemClicked(simDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return simList == null ? 0 : simList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView itemTitle;
        public TextView itemMessage;
        public ConstraintLayout itemLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            this.itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            this.itemMessage = (TextView) itemView.findViewById(R.id.item_message);
            this.itemLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}
