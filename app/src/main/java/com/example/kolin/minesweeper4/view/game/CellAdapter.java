package com.example.kolin.minesweeper4.view.game;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.kolin.minesweeper4.R;
import com.example.kolin.minesweeper4.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 31.10.2016.
 */

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {

    private List<Cell> list = new ArrayList<>();

    private CellAdapterListener listener;
    private Context context;

    public interface CellAdapterListener {
        void onClickItemView(int xPos, int yPos);
    }

    @Override
    public CellAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CellAdapter.ViewHolder holder, int position) {
        Cell cell = list.get(position);
        if (cell.isOpen() && cell.isBomb() && !cell.isClicked()){

        } else {
            if (cell.isClicked()){
                if (cell.getValue() == -1){
                    holder.imageButton.setImageDrawable(getImageBomb());
                } else {
                    holder.imageButton.setImageDrawable(getImageNumber(cell.getValue()));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageButton imageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            imageButton = (ImageButton) itemView.findViewById(R.id.item_cell_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        Cell cell = list.get(getLayoutPosition());
                        listener.onClickItemView(cell.getX(), cell.getY());
                    }
                }
            });
        }
    }

    public void addAll(List<Cell> cells) {
        list.clear();
        list.addAll(cells);
        notifyDataSetChanged();
    }

    public void setCellAdapterListener(CellAdapterListener listener) {
        this.listener = listener;
    }

    private Drawable getImageNormal(){
        return ContextCompat.getDrawable(context, R.drawable.ic_rounded_black_square_shape);
    }

    private Drawable getImageBomb(){
        return ContextCompat.getDrawable(context, R.drawable.bomb);
    }

    private Drawable getImageNumber(int value) {
        Drawable drawable = null;
        switch (value) {
            case 0:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_check_box_empty);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_one);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_two);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_three);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_four);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_five);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_six);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_seven);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(context, R.drawable.ic_eight);
                break;
        }
        return drawable;
    }
}
