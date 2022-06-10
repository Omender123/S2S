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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.edutech.Presenter.AttendancePresenter;
import com.edutech.R;
import com.edutech.SharedPerfence.MyPreferences;
import com.edutech.SharedPerfence.PrefConf;
import com.edutech.databinding.FragmentAttendanceBinding;
import com.edutech.utils.AppUtils;
import com.irozon.sneaker.Sneaker;

import okhttp3.ResponseBody;


public class AttendanceFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, AttendancePresenter.AttendanceView, View.OnClickListener {
    FragmentAttendanceBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;
    String currentDate, beforeDate, AttendanceType, status;
    AttendancePresenter presenter;

    public AttendanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_attendance, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_attendance, container, false);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        presenter = new AttendancePresenter(this);
        currentDate = AppUtils.getCurrentDate("dd-MM-yyyy");
        binding.txtDate.setText(currentDate);
        beforeDate = MyPreferences.getInstance(getActivity()).getString(PrefConf.CheckDate, "");
        AttendanceType = MyPreferences.getInstance(getActivity()).getString(PrefConf.AttendanceType, "");

        if (currentDate.equalsIgnoreCase(beforeDate)) {
            if (AttendanceType.equalsIgnoreCase("Absent")) {
                binding.radioP.setClickable(false);
                binding.radioA.setClickable(false);
                binding.radioL.setClickable(false);
                binding.radioA.setChecked(true);
                binding.radioP.setChecked(false);
                binding.radioL.setChecked(false);

            } else if (AttendanceType.equalsIgnoreCase("Present")) {
                binding.radioP.setClickable(false);
                binding.radioA.setClickable(false);
                binding.radioL.setClickable(false);
                binding.radioA.setChecked(false);
                binding.radioP.setChecked(true);
                binding.radioL.setChecked(false);

            } else if (AttendanceType.equalsIgnoreCase("Leave")) {
                binding.radioP.setClickable(false);
                binding.radioA.setClickable(false);
                binding.radioL.setClickable(false);
                binding.radioA.setChecked(false);
                binding.radioP.setChecked(false);
                binding.radioL.setChecked(true);

            }
        } else {
            binding.radioP.setClickable(true);
            binding.radioA.setClickable(true);
            binding.radioL.setClickable(false);
        }


        binding.radioGroup.setOnCheckedChangeListener(this);
        binding.textMLeave.setOnClickListener(this);

        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radio_A:
                Log.d("radio", "Absent");
                status = "Absent";
                presenter.AttendanceTeacher(getContext(),status);
                break;
            case R.id.radio_P:
                Log.d("radio", "Present");
                status = "Present";
                presenter.AttendanceTeacher(getContext(),status);
                break;
            case R.id.radio_L:
                Log.d("radio", "Leave");
                status = "Leave";
                presenter.AttendanceTeacher(getContext(),status);
                break;
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Sneaker.with(getActivity())
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onAttendanceSuccess(ResponseBody response, String message) {
        if (message.equalsIgnoreCase("ok")) {
            binding.radioA.setClickable(false);
            binding.radioL.setClickable(false);
            binding.radioP.setClickable(false);
            MyPreferences.getInstance(getActivity()).putString(PrefConf.CheckDate, currentDate);
            MyPreferences.getInstance(getActivity()).putString(PrefConf.AttendanceType, status);

        }

    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(getActivity())
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.text_mLeave:
                navController.navigate(R.id.action_attendance_to_myLeaveFragment);
                break;
        }
    }
}