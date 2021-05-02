package com.thima.my_tutor_admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;
import com.thima.my_tutor_admin.R;
import com.thima.my_tutor_admin.interfaces.CircleTransform;
import com.thima.my_tutor_admin.models.TutorModel;

import java.util.ArrayList;
import java.util.List;

public class TutorsAdapter extends RecyclerView.Adapter<TutorsViewHolder> {
    private List<TutorModel> Items = new ArrayList<>();
    private FragmentManager fm;

    public TutorsAdapter(List<TutorModel> items, FragmentManager fm) {
        Items = items;
        this.fm = fm;
    }

    @NonNull
    @Override
    public TutorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tutor, parent, false);
        return new TutorsViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorsViewHolder holder, int position) {

        holder.TxtNames.setText(String.format("%s %s", Items.get(position).getName(), Items.get(position).getSurname()));
        holder.TxtDescription.setText(Items.get(position).getRole());

        Picasso.get().load(Items.get(position).getImgUrl())
                .resize(150, 150)
                .transform(new CircleTransform())
                .placeholder(R.drawable.baseline_account_circle_black_24dp)
                .into(holder.Img);
        holder.BtnProfile.setVisibility(View.GONE);
        holder.BtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    @Override
    public int getItemCount() {
        return Items.size();
    }
}
class TutorsViewHolder extends RecyclerView.ViewHolder{

    public MaterialButton BtnProfile;
    public MaterialTextView TxtNames;
    public MaterialTextView TxtDescription;
    public AppCompatImageView Img;
    public TutorsViewHolder(@NonNull View itemView) {
        super(itemView);
        BtnProfile = (MaterialButton)itemView.findViewById(R.id.row_m_btn_profile);

        TxtDescription = (MaterialTextView) itemView.findViewById(R.id.row_m_description);
        TxtNames = (MaterialTextView) itemView.findViewById(R.id.row_m_names);
        Img = (AppCompatImageView) itemView.findViewById(R.id.row_m_profile);
    }
}
