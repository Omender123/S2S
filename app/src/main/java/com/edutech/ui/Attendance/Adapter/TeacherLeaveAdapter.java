package com.edutech.ui.Attendance.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edutech.Model.Response.TeacherLeaveResponse;
import com.edutech.databinding.CustomActivityLayoutBinding;
import com.edutech.databinding.CustomLeaveLayoutBinding;
import com.edutech.utils.AppUtils;

import java.util.List;

public class TeacherLeaveAdapter extends RecyclerView.Adapter<TeacherLeaveAdapter.TeacherLeaveView> {
    private Context context;
    private List<TeacherLeaveResponse> list;
    private onClickedListener listener;

    public TeacherLeaveAdapter(Context context, List<TeacherLeaveResponse> list, onClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TeacherLeaveView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CustomLeaveLayoutBinding binding = CustomLeaveLayoutBinding.inflate(inflater, parent, false);

        return new TeacherLeaveView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherLeaveView holder, int position) {
        holder.binding.textDes.setText(list.get(position).getReason());
        holder.binding.txtDate.setText(AppUtils.getDateTime1(list.get(position).getDate(), "dd-MM-yyyy"));
        holder.binding.textStatus.setText(list.get(position).getStatus());
        if (list.get(position).getStatus().equalsIgnoreCase("Applied")) {
            holder.binding.textStatus.setTextColor(Color.parseColor("#FF9B2C"));
        } else if (list.get(position).getStatus().equalsIgnoreCase("Rejected")) {
            holder.binding.textStatus.setTextColor(Color.parseColor("#FD0000"));
        } else if (list.get(position).getStatus().equalsIgnoreCase("Accepted")) {
            holder.binding.textStatus.setTextColor(Color.parseColor("#649062"));
        } else if (list.get(position).getStatus().equalsIgnoreCase("Cancel")) {
            holder.binding.textStatus.setTextColor(Color.parseColor("#80FD0000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickedListener(list, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherLeaveView extends RecyclerView.ViewHolder {

        CustomLeaveLayoutBinding binding;

        public TeacherLeaveView(@NonNull CustomLeaveLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface onClickedListener {
        void onItemClickedListener(List<TeacherLeaveResponse> list, int position);
    }
}
