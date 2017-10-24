package com.example.kirandeep.phoneutilization.usageStats;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirandeep.phoneutilization.R;
import com.example.kirandeep.phoneutilization.localStorage.QueryRepository;
import com.example.kirandeep.phoneutilization.localStorage.StatsModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

/**
 * Created by abc on 27-09-2017.
 */

public class UsageListAdapter extends RecyclerView.Adapter<UsageListAdapter.ViewHolder> {

private List<CustomUsageStats> mCustomUsageStatsList = new ArrayList<>();
private DateFormat mDateFormat = new SimpleDateFormat();

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView mPackageName;
    private final TextView mLastTimeUsed;
    private final ImageView mAppIcon;

    public ViewHolder(View v) {
        super(v);
        mPackageName = (TextView) v.findViewById(R.id.textview_package_name);
        mLastTimeUsed = (TextView) v.findViewById(R.id.textview_last_time_used);
        mAppIcon = (ImageView) v.findViewById(R.id.app_icon);
    }

    public TextView getLastTimeUsed() {
        return mLastTimeUsed;
    }

    public TextView getPackageName() {
        return mPackageName;
    }

    public ImageView getAppIcon() {
        return mAppIcon;
    }
}

    public UsageListAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.usage_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getPackageName().setText(
                mCustomUsageStatsList.get(position).usageStats.getPackageName());
        final long lastTimeUsed = mCustomUsageStatsList.get(position).usageStats.getLastTimeUsed();
        viewHolder.getLastTimeUsed().setText(mDateFormat.format(new Date(lastTimeUsed)));
        viewHolder.getAppIcon().setImageDrawable(mCustomUsageStatsList.get(position).appIcon);

        updateLocalDb(mCustomUsageStatsList);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateLocalDb(final List<CustomUsageStats> mCustomUsageStatsList) {
        final StatsModel statsModel = new StatsModel();
        Realm realm = Realm.getDefaultInstance();
        for (final CustomUsageStats customUsageStats : mCustomUsageStatsList){
            if(realm.where(StatsModel.class).equalTo("packageName", customUsageStats.usageStats.getPackageName()) == null){
                realm.executeTransaction(new Realm.Transaction() {

                    @Override
                    public void execute(Realm realm) {
                        statsModel.setPackageName(customUsageStats.usageStats.getPackageName());
                        statsModel.setLastTimeUsed(customUsageStats.usageStats.getLastTimeUsed());
                        statsModel.setFirstTimestamp(customUsageStats.usageStats.getFirstTimeStamp());
                        statsModel.setLastTimestamp(customUsageStats.usageStats.getLastTimeStamp());
                        statsModel.setTotalTimeInForeground(customUsageStats.usageStats.getTotalTimeInForeground());
                    }
                });
                Log.i("New Entry", statsModel.getPackageName());
            }
            else
            {
                final StatsModel oldStatsModel = new StatsModel();
                oldStatsModel.setFirstTimestamp(customUsageStats.usageStats.getFirstTimeStamp());
                oldStatsModel.setLastTimestamp(customUsageStats.usageStats.getLastTimeStamp());
                oldStatsModel.setLastTimeUsed(customUsageStats.usageStats.getLastTimeUsed());
                oldStatsModel.setTotalTimeInForeground(customUsageStats.usageStats.getTotalTimeInForeground());
                oldStatsModel.setPackageName(customUsageStats.usageStats.getPackageName());
                //realm.where(StatsModel.class).equalTo("packageName", customUsageStats.usageStats.getPackageName()).findFirst();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        realm.copyToRealmOrUpdate(oldStatsModel);
                    }
                });
                Log.i("Update", oldStatsModel.getPackageName());

                QueryRepository m = new QueryRepository();
                Log.i("Committed", m.getAllStats().toString());


            }

        }

    }

    @Override
    public int getItemCount() {
        return mCustomUsageStatsList.size();
    }

    public void setCustomUsageStatsList(List<CustomUsageStats> customUsageStats) {
        mCustomUsageStatsList = customUsageStats;
    }
}
