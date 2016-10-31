package com.example.kolin.minesweeper4.view.end;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolin.minesweeper4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by kolin on 31.10.2016.
 */

public class EndAdapter extends RecyclerView.Adapter<EndAdapter.ViewHolder> {


    private List<String> list = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s = list.get(position);
        int pos = position + 1;
        holder.textView.setText(pos + ". " + s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.item_result_text_view);
        }
    }

    public void addAll(Set<String> set) {
        list.clear();
        list.addAll(set);
        notifyDataSetChanged();
    }

}
