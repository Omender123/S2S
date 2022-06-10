package com.edutech.ui.Attendance;

import android.app.DatePickerDialog;
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

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.edutech.Model.Request.TeacherLeaveBody;
import com.edutech.Model.Response.TeacherLeaveResponse;
import com.edutech.Presenter.TeacherLeavePresenter;
import com.edutech.R;
import com.edutech.databinding.FragmentMyLeaveBinding;
import com.edutech.databinding.FragmentRequestLeaveBinding;
import com.edutech.utils.AppUtils;
import com.irozon.sneaker.Sneaker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;


public class RequestLeaveFragment extends Fragment implements View.OnClickListener, TeacherLeavePresenter.TeacherLeaveView {
    FragmentRequestLeaveBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;
    boolean check = false;
    TeacherLeavePresenter presenter;


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

        presenter = new TeacherLeavePresenter(this);

        binding.txtLeave.setOnClickListener(this);
        binding.txtManyLeave.setOnClickListener(this);
        binding.startDate.setOnClickListener(this);
        binding.endDate.setOnClickListener(this);
        binding.btnSend.setOnClickListener(this);

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
                binding.startDate.setHint("Select Date");
                binding.txtLeave.setTextColor(Color.parseColor("#2d31fa"));
                binding.txtManyLeave.setTextColor(Color.parseColor("#919191"));

                break;

            case R.id.txt_manyLeave:
                check = true;
                binding.endDate.setVisibility(View.VISIBLE);
                binding.startDate.setHint("Select Starting Date");
                binding.txtLeave.setTextColor(Color.parseColor("#919191"));
                binding.txtManyLeave.setTextColor(Color.parseColor("#2d31fa"));
                break;

            case R.id.startDate:
                Calendar calendar = Calendar.getInstance();
                showPickerDialog(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
                break;

            case R.id.endDate:
                Calendar calendar1 = Calendar.getInstance();
                showPickerDialog1(calendar1.get(Calendar.DAY_OF_MONTH), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.YEAR));
                break;

            case R.id.btn_send:
                String startDate = binding.startDate.getText().toString().trim();
                String endDate = binding.endDate.getText().toString().trim();
                String des = binding.description.getText().toString().trim();
                Toast.makeText(getContext(), "" + startDate, Toast.LENGTH_SHORT).show();

                if (startDate.isEmpty()) {
                    Sneaker.with(getActivity())
                            .setTitle("please " + binding.startDate.getHint().toString())
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();
                } else if (check == true && endDate.isEmpty()) {
                    Sneaker.with(getActivity())
                            .setTitle("please " + binding.endDate.getHint().toString())
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();
                } else if (des.isEmpty()) {
                    Sneaker.with(getActivity())
                            .setTitle("please " + binding.description.getHint().toString())
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();
                } else {
                    if (check == false) {
                        List<String> dates = new ArrayList<String>();
                        dates.clear();
                        dates.add(startDate);
                        TeacherLeaveBody body = new TeacherLeaveBody(des,dates);
                        presenter.ApplyTeacherLeave(getContext(),body);
                    } else {
                        TeacherLeaveBody body = new TeacherLeaveBody(des,AppUtils.getAllDate(startDate,endDate));
                        presenter.ApplyTeacherLeave(getContext(),body);
                    }
                }

                break;

        }

    }

    private void showPickerDialog(int day, int month, int year) {

        DatePickerDialog dtPickerDlg = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, i);
                c.set(Calendar.MONTH, i1);
                c.set(Calendar.DAY_OF_MONTH, i2);

                String mDate = DateFormat.format("MM-dd-yyyy", c).toString();
                binding.startDate.setText(mDate);

            }
        }, year, month, day);
        dtPickerDlg.getDatePicker().setSpinnersShown(true);
        dtPickerDlg.getDatePicker().setCalendarViewShown(false);
        dtPickerDlg.setTitle(binding.startDate.getHint().toString());
        dtPickerDlg.show();
    }

    private void showPickerDialog1(int day, int month, int year) {

        DatePickerDialog dtPickerDlg = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, i);
                c.set(Calendar.MONTH, i1);
                c.set(Calendar.DAY_OF_MONTH, i2);

                String mDate = DateFormat.format("MM-dd-yyyy", c).toString();
                binding.endDate.setText(mDate);

            }
        }, year, month, day);
        dtPickerDlg.getDatePicker().setSpinnersShown(true);
        dtPickerDlg.getDatePicker().setCalendarViewShown(false);
        dtPickerDlg.setTitle(binding.endDate.getHint().toString());
        dtPickerDlg.show();
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
    public void onTeacherLeaveSuccess(ResponseBody response, String message) {
        if (message.equalsIgnoreCase("ok")) {
            getActivity().onBackPressed();
            Toast.makeText(getContext(), "SuccessFully Leave applied  ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onGetAllTeacherLeaveSuccess(List<TeacherLeaveResponse> response, String message) {

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


}