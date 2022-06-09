package com.edutech.ui.Attendance;

import android.app.Dialog;
import android.content.Context;
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
import com.edutech.utils.AppUtils;


public class MyLeaveFragment extends Fragment implements View.OnClickListener {
    FragmentMyLeaveBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;
    public MyLeaveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_leave, container, false);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        binding.txtReqLeave.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.txt_reqLeave:
                navController.navigate(R.id.action_myLeaveFragment_to_requestLeaveFragment);
                break;
        }
    }
}