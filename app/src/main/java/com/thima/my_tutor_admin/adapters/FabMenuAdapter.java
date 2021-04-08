package com.thima.my_tutor_admin.adapters;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.activities.MainActivity;
import com.thima.my_tutor_admin.interfaces.MenuAdapterClickInterface;
import com.thima.my_tutor_admin.models.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class FabMenuAdapter extends RecyclerView.Adapter<FabMenuViewHolder>  {

    List<MenuModel> Items = new ArrayList<>();
    MenuAdapterClickInterface menu_click;

    public FabMenuAdapter(MenuAdapterClickInterface menu_click, List<MenuModel> items) {
        Items = items;
        this.menu_click = menu_click;
    }

    @NonNull
    @Override
    public FabMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_fab_row, parent, false);

        return new FabMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FabMenuViewHolder holder, int position) {
        holder.txt_title.setText(Items.get(position).getTitle());
        holder.fab.setImageResource(Items.get(position).getIcon());
        holder.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor( holder.itemView.getContext(), Items.get(position).getColor())));
        //holder.fab.setColorFilter(R.color.blue_500);
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_click.OnMenuClick(position, holder.fab);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class FabMenuViewHolder extends RecyclerView.ViewHolder{
    public MaterialTextView txt_title;
    public FloatingActionButton fab;
    public FabMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_title = itemView.findViewById(R.id.menu_title);
        fab = itemView.findViewById(R.id.menu_fab);
    }
}
