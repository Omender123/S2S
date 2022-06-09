package com.edutech.ui.Attendance;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edutech.R;
import com.edutech.databinding.FragmentMyLeaveBinding;
import com.edutech.databinding.FragmentRequestLeaveBinding;
import com.edutech.utils.AppUtils;


public class RequestLeaveFragment extends Fragment implements View.OnClickListener {

    FragmentRequestLeaveBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;
    boolean check = false;


    public RequestLeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request_leave, container, false);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        binding.txtLeave.setOnClickListener(this);
        binding.txtManyLeave.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txt_leave:
                check = false;
                binding.endDate.setVisibility(View.GONE);
                binding.startDate.setText("Select Date");
                binding.txtLeave.setTextColor(Color.parseColor("#2d31fa"));
                binding.txtManyLeave.setTextColor(Color.parseColor("#919191"));

                break;

            case R.id.txt_manyLeave:
                check = true;
                binding.endDate.setVisibility(View.VISIBLE);
                binding.startDate.setText("Select Starting Date");
                binding.txtLeave.setTextColor(Color.parseColor("#919191"));
                binding.txtManyLeave.setTextColor(Color.parseColor("#2d31fa"));
                break;

        }
    }
}