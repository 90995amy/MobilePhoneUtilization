package com.example.kirandeep.phoneutilization.localStorage;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by abc on 24-10-2017.
 */

public class QueryRepository {
    public ArrayList<StatsModel> getAllStats(){
        Realm realm = Realm.getDefaultInstance();
        ArrayList<StatsModel> temp = new ArrayList<StatsModel>(realm.where(StatsModel.class).findAll());
        return temp;
    }
}
