package com.edutech.ui.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edutech.Model.ActivityModel;
import com.edutech.databinding.CustomActivityLayoutBinding;
import com.edutech.databinding.CustomBannerLayoutBinding;

import java.util.ArrayList;

public class ActivityAdapter  extends RecyclerView.Adapter<ActivityAdapter.ActivityView>{
    ArrayList<ActivityModel> list;
    Context context;

    public ActivityAdapter(ArrayList<ActivityModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CustomActivityLayoutBinding binding = CustomActivityLayoutBinding.inflate(inflater, parent, false);

        return new ActivityView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityView holder, int position) {
        holder.binding.img.setImageResource(list.get(position).getIcon());
        holder.binding.txt.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ActivityView extends RecyclerView.ViewHolder {

        CustomActivityLayoutBinding binding;

        public ActivityView(@NonNull CustomActivityLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
