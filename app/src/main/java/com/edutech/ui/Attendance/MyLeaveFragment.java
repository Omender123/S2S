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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edutech.Model.Response.TeacherLeaveResponse;
import com.edutech.Presenter.TeacherLeavePresenter;
import com.edutech.R;
import com.edutech.databinding.FragmentMyLeaveBinding;
import com.edutech.ui.Attendance.Adapter.TeacherLeaveAdapter;
import com.edutech.ui.Home.Adapter.BannerAdapter;
import com.edutech.utils.AppUtils;
import com.irozon.sneaker.Sneaker;

import java.util.List;

import okhttp3.ResponseBody;


public class MyLeaveFragment extends Fragment implements View.OnClickListener, TeacherLeavePresenter.TeacherLeaveView, TeacherLeaveAdapter.onClickedListener {
    FragmentMyLeaveBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;
    TeacherLeavePresenter presenter;

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
        presenter = new TeacherLeavePresenter(this);

        presenter.GetAllTeacherLeave(getContext());


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

            case R.id.txt_reqLeave:
                navController.navigate(R.id.action_myLeaveFragment_to_requestLeaveFragment);
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
    public void onTeacherLeaveSuccess(ResponseBody response, String message) {

    }

    @Override
    public void onGetAllTeacherLeaveSuccess(List<TeacherLeaveResponse> response, String message) {
        if (message.equalsIgnoreCase("ok") && response != null && response.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),  LinearLayoutManager.VERTICAL, false);
            binding.recyclerView.setLayoutManager(layoutManager);
            binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
            binding.recyclerView.setHasFixedSize(false);
            binding.recyclerView.setAdapter(new TeacherLeaveAdapter(getContext(),response,this));
            binding.recyclerView.setVisibility(View.VISIBLE);


        } else {
            binding.recyclerView.setVisibility(View.GONE);

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
    public void onItemClickedListener(List<TeacherLeaveResponse> list, int position) {

    }
}