package com.app.listtestwithapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.listtestwithapi.R;
import com.app.listtestwithapi.model.ResultItem;

import java.util.ArrayList;


/**
 * Created by User122 on 02-09-2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //private ArrayList<ActorsItem> arrayList;
    //private ArrayList<AlbumList> arrayList;
    private ArrayList<ResultItem> arrayList;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<ResultItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    //Replace the content of the view
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        //get data from the item data
        if (arrayList.get(position).getSHindi().equals("") || arrayList.get(position).getSHindi().equals(null)) {
            viewHolder.txtName.setText(arrayList.get(position).getSHen());
        } else {
            viewHolder.txtName.setText(arrayList.get(position).getSHindi());
        }
    }

    //Inner class to hold a reference to each item of RecylcerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            txtName = (TextView) itemLayoutView.findViewById(R.id.txtName);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}