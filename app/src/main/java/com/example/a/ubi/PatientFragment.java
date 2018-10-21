package com.example.a.ubi;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatientFragment extends Fragment {

    public static final String FRAGMENT_TAG ="Patient" ;

    private Toolbar mToolbar;

    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;
    //private Button btn_add_patient;

    private DaoSession mDaoSession;

    private Handler mHandler;
    private int mInterval = 5000; // 5 seconds by default, can be changed later

    List<UserModelDao> list;

    private String[] names;

    final int minHeart_rate = 100;
    final int maxHeart_rate = 500;

    final int minHeart_rate_v = 68;
    final int maxHeart_rate_v = 219;

    final int minbeat = 9;
    final int maxbeat = 30;

    final int minTemp = 5;
    final int maxTemp = 10;

    final int minFoot = 10;
    final int maxFoot = 30;

    public PatientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        //bottomNavigationView.getMenu().getItem(0).setChecked(true);
        mUserAdapter.notifyDataSetChanged();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        names = getResources().getStringArray(R.array.status);
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(getActivity(), "greendao_demo.db").getWritableDb()).newSession();

        list = new ArrayList<UserModelDao>();

        if(mDaoSession.getUserModelDaoDao().loadAll().size() == 0)
            mDaoSession.getUserModelDaoDao().insert(new UserModelDao("Ehsan",30,"Asprine taken","","+41","120","","95 cc","258","ii","500"));
        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserModelDaoDao().loadAll().size() != 0){
            list=mDaoSession.getUserModelDaoDao().loadAll();

        }
        Log.i("UserModelDao",list.size()+"");

        mUserAdapter = new UserAdapter(list,getActivity());
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUserAdapter);

        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_patient_new, container, false);
        mRecyclerView=(RecyclerView)  view.findViewById(R.id.card_user_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        return view;
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus(); //this function can change value of mInterval.

                int randomHeart_rate = new Random().nextInt((maxHeart_rate - minHeart_rate) + 1) + minHeart_rate;

                //minHeart_rate_v
                int randomHeart_rate_v = new Random().nextInt((maxHeart_rate_v - minHeart_rate_v) + 1) + minHeart_rate_v;


                int randombeat = new Random().nextInt((maxbeat- minbeat) + 1) + minbeat;

                int randomTemp=new Random().nextInt((maxTemp- minTemp) + 1) + minTemp;

                int randomFoot=new Random().nextInt((maxFoot- minFoot) + 1) + minFoot;

                int randomIndex = new Random().nextInt(names.length);
                String randomName = names[randomIndex];

                Log.i("raha -------",randombeat+"");
                //List<UserModelDao> list = new ArrayList<>();
                UserModelDao newValue = new UserModelDao("EhsanT", 30, "Asprine taken", "", "+41", (Integer.valueOf("95")+randomHeart_rate)+"",(Integer.valueOf("0")+randomHeart_rate_v)+"", (Integer.valueOf("20")+randombeat)+"", (Integer.valueOf("30")+randomTemp)+"", randomName, (Integer.valueOf("3650")+randomFoot)+"");
                int updateIndex = 3;
                // getDaoSession().getItemsDao().update(itemsList.get(i))

                list.set(0, newValue);
                mUserAdapter.notifyItemChanged(updateIndex);
                mUserAdapter.notifyDataSetChanged();



            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }



}

