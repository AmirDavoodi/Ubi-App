package com.example.a.ubi;

import android.app.Application;

public class DemoApp extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        mDaoSession = new DaoMaster(
//                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();
//
//        // USER CREATION FOR DEMO PURPOSE
//        if(mDaoSession.getUserModelDaoDao().loadAll().size() == 0){
//            //(String name, int age, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps)
//            mDaoSession.getUserModelDaoDao().insert(new UserModelDao("aria",15,"ttttt","hhhhhhhhhh","989995555","25","20","95mm","standing","90000"));
//        }
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}