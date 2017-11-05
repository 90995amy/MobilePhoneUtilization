package com.example.kirandeep.phoneutilization.usageStats;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kirandeep.phoneutilization.R;
import com.example.kirandeep.phoneutilization.localStorage.QueryRepository;
import com.example.kirandeep.phoneutilization.localStorage.StatsModel;
import com.example.kirandeep.phoneutilization.networkConnection.Service;
import com.example.kirandeep.phoneutilization.networkConnection.ServiceCallback;
import com.example.kirandeep.phoneutilization.networkConnection.StatsRequest;

import java.util.ArrayList;

public class AppUsageStatisticsActivity extends AppCompatActivity {

    private Service mService;
    private QueryRepository mQueryRepository;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_sync:
                mService = new Service();
                mQueryRepository = new QueryRepository();
                ArrayList<StatsModel> data = mQueryRepository.getAllStats();
                for (StatsModel statsModel: data){
                    StatsRequest statsRequest = new StatsRequest(statsModel);
                    mService.sendPost(statsRequest, new ServiceCallback() {
                        @Override
                        public void onSuccess(AsyncTask.Status statusResponse, Object response) {

                        }

                        @Override
                        public void onSuccess(Object response) {
                            Snackbar.make(getCurrentFocus(),"Successfully synced", Snackbar.LENGTH_LONG);
                            Toast.makeText(getApplicationContext(),"Successfully synced", Toast.LENGTH_LONG);
                            Log.i("Response", response.toString());
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            Log.e("Alert",exception.getMessage());
                        }
                    });
                }


                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
