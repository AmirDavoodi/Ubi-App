package com.example.a.ubi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {


    private Toolbar mToolbar;

    private EventAdapter mEventAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private TextView tv_title_signal,tv_user_name,tv_user_device;

    private ImageView iv_user_img_profile,iv_heart_rate,iv_heart_rate_v,iv_respiratory,iv_BO2,iv_posture,iv_steps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        mRecyclerView=(RecyclerView)  findViewById(R.id.card_event_recycler_view);
        tv_title_signal=(TextView) findViewById(R.id.tv_title_signal);
        tv_user_name=(TextView) findViewById(R.id.tv_user_name);
        tv_user_device=(TextView) findViewById(R.id.tv_user_device);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_user_img_profile=(ImageView) findViewById(R.id.iv_user_img_profile);
        iv_heart_rate=(ImageView) findViewById(R.id.iv_heart_rate);
        iv_heart_rate.setColorFilter(R.color.white);

        iv_heart_rate_v=(ImageView)  findViewById(R.id.iv_heart_rate_v);
        iv_heart_rate_v.setColorFilter(getResources().getColor(R.color.gray));

        iv_respiratory=(ImageView) findViewById(R.id.iv_respiratory);
        iv_respiratory.setColorFilter(getResources().getColor(R.color.gray));

        iv_BO2=(ImageView) findViewById(R.id.iv_BO2);
        iv_BO2.setColorFilter(getResources().getColor(R.color.gray));

        iv_posture=(ImageView) findViewById(R.id.iv_posture);
        iv_posture.setColorFilter(getResources().getColor(R.color.gray));

        iv_steps=(ImageView) findViewById(R.id.iv_steps);
        iv_steps.setColorFilter(getResources().getColor(R.color.gray));
        setUp();

    }

    private void setUp() {

        setSupportActionBar(mToolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent mPatient_Ac=new Intent(DetailActivity.this,MainActivity.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });


        tv_user_name.setText("Name: Ehsan");
        tv_user_device.setText("Device: UBI104");

        //String time, String day, String date, String msg
        ArrayList<EventModel> list = new ArrayList<EventModel>();
        EventModel eventModel=new EventModel("12am","12 Sep","2018-09-22","heart beat 340");
        list.add(eventModel);


        list.add(new EventModel("3am","09 Sep","2018-09-01","asprin taken & sleep"));
        list.add(new EventModel("2am","01 Aug","2018-10-01","he is stable"));
        list.add(new EventModel("12pm","01 jun","2018-02-01","ne two three four five six seven eight nine ten  "));

        mEventAdapter = new EventAdapter(list,DetailActivity.this);
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mEventAdapter);

        tv_title_signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mGraph_Ac=new Intent(DetailActivity.this,LineChartActivity1.class);
                mGraph_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mGraph_Ac);
            }
        });


    }
}
