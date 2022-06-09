package com.edutech;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.edutech.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        changeStatusBarColor();
        setSupportActionBar(binding.layout.toolbar);
        SetBottomBarNavigationView();
    }

    private void SetBottomBarNavigationView() {
        navController = Navigation.findNavController(this, R.id.main);


        appBarConfiguration = new AppBarConfiguration.Builder(new int[]{R.id.dashboardFragment, R.id.classes, R.id.attendance, R.id.profile})
                .setDrawerLayout(binding.drawer)
                .build();
        NavigationUI.setupWithNavController(binding.navigationView, navController);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupActionBarWithNavController(this,navController);
        NavigationUI.setupWithNavController(binding.layout.toolbar, navController, binding.drawer);

        binding.layout.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_noun_down_slide_menu_left_2085948));
        NavigationUI.setupWithNavController(binding.layout.bottomNavigation, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.myLeaveFragment || destination.getId() == R.id.requestLeaveFragment) {
                    binding.layout.txtTittle.setText(destination.getLabel());
                    binding.layout.bottomNavigation.setVisibility(View.GONE);
                    binding.layout.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_awesome_arrow_right));

                } else {
                    binding.layout.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_noun_down_slide_menu_left_2085948));
                    binding.layout.txtTittle.setText(destination.getLabel());
                    binding.layout.bottomNavigation.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        // toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_icon_menu));
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}