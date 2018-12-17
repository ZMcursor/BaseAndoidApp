package com.zmcurosr.baseandoidapp.view.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zmcurosr.baseandoidapp.R;
import com.zmcurosr.baseandoidapp.model.Bean.ListItem;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<ListItem> list;
    private OnItemClickListener listener;

    public ListAdapter(ArrayList<ListItem> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView title = viewHolder.title;
        title.setText(list.get(i).content.replace("\n", " "));
        viewHolder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(v, i);
            }
        });
    }

//    public void setListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        private ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}



