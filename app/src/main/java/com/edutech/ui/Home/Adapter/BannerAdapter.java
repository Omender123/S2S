package com.edutech.ui.Home.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edutech.Model.BannerModel;
import com.edutech.databinding.CustomBannerLayoutBinding;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerView> {
    ArrayList<BannerModel> list;
    Context context;

    public BannerAdapter(ArrayList<BannerModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CustomBannerLayoutBinding binding = CustomBannerLayoutBinding.inflate(inflater, parent, false);

        return new BannerView(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull BannerView holder, int position) {
        holder.binding.txt.setText(list.get(position).getTxt());
        holder.binding.txt2.setText(list.get(position).getTxt1());
        holder.binding.imgIcon.setImageResource(list.get(position).getIcon());
        holder.binding.relative.setBackground(list.get(position).getBackImg());
        holder.binding.btn.setText(list.get(position).getBtnTxt());
        holder.binding.btn.setTextColor(Color.parseColor(list.get(position).getBtnTxtColor()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class BannerView extends RecyclerView.ViewHolder {

        CustomBannerLayoutBinding binding;

        public BannerView(@NonNull CustomBannerLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
