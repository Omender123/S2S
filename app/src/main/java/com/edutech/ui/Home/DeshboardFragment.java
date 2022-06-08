package com.edutech.ui.Home;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edutech.Model.ActivityModel;
import com.edutech.Model.BannerModel;
import com.edutech.R;
import com.edutech.databinding.FragmentDeshboardBinding;
import com.edutech.ui.Home.Adapter.ActivityAdapter;
import com.edutech.ui.Home.Adapter.BannerAdapter;
import com.edutech.utils.AppUtils;
import com.edutech.utils.SpacesItemDecoration;

import java.util.ArrayList;

public class DeshboardFragment extends Fragment {
    FragmentDeshboardBinding binding;
    private Context context;
    private Dialog dialog;
    private View view;
    NavController navController;

    public DeshboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.fragment_deshboard, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deshboard, container, false);
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());

        setBannerData();
        setActivityData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    public void setBannerData() {
        ArrayList<BannerModel> bannerModels = new ArrayList<BannerModel>();
        bannerModels.clear();

        bannerModels.add(new BannerModel("Class \nAttendance",
                "You are the class teacher of this class Take Class Attendance",
                "Take Now",
                "#2d31fa",
                R.mipmap.schedule,
                getContext().getResources().getDrawable(R.drawable.banner_gradient1)));

        bannerModels.add(new BannerModel("Daily Questions \n& Answers ",
                "Add Daily question and answer \nfor Students",
                "Add",
                "#F45247",
                R.mipmap.help,
                getContext().getResources().getDrawable(R.drawable.banner_gradient2)));


        BannerAdapter bannerAdapter = new BannerAdapter(bannerModels, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),  LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setHasFixedSize(false);
        binding.recyclerView.setAdapter(bannerAdapter);
        binding.indicator.attachToRecyclerView(binding.recyclerView);

    }


    public void setActivityData() {
        ArrayList<ActivityModel>list = new ArrayList<ActivityModel>();
        list.clear();
        list.add(new ActivityModel("Chat",R.mipmap.chat));
        list.add(new ActivityModel("Noticeboard",R.mipmap.corkboard));
        list.add(new ActivityModel("Notes",R.mipmap.essay));
        list.add(new ActivityModel("PTM",R.mipmap.conversation_1));
        list.add(new ActivityModel("Video lectures",R.mipmap.facebook));

        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(),3,RecyclerView.VERTICAL,false);
        binding.recyclerViewActivity.setLayoutManager(mLayoutManager);
        binding.recyclerViewActivity.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewActivity.setHasFixedSize(true);
        binding.recyclerViewActivity.setAdapter(new ActivityAdapter(list,getContext()));
        binding.recyclerViewActivity.addItemDecoration(new SpacesItemDecoration(15));

    }
}