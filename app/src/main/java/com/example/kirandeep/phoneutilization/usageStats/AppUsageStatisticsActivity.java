package com.example.kirandeep.phoneutilization.usageStats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kirandeep.phoneutilization.R;

public class AppUsageStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_usage_statistics);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, AppUsageStatisticsFragment.newInstance())
                    .commit();
        }
    }
}
