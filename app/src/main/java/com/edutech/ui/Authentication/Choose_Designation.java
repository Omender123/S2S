package com.edutech.ui.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edutech.R;
import com.edutech.databinding.ActivityChooseDesignationBinding;
import com.irozon.sneaker.Sneaker;

public class Choose_Designation extends AppCompatActivity implements View.OnClickListener {
    ActivityChooseDesignationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_designation);

        binding.txtTeacher.setOnClickListener(this);
        binding.txtOther.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_teacher:
                startActivity(new Intent(this,LoginActivity.class));
                break;

            case R.id.txt_other:
                Sneaker.with(Choose_Designation.this)
                        .setTitle("Coming Soon")
                        .setMessage("")
                        .setCornerRadius(4)
                        .setDuration(1500)
                        .sneakSuccess();
                break;
        }
    }
}