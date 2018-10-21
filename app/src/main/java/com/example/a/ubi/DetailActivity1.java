package com.example.a.ubi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a.ubi.humansignal.HumanSignalActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity1 extends AppCompatActivity {
    private Toolbar mToolbar;

    private DeviceAdapter mDeviceAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private EventAdapter mEventAdapter;

    private Button btn_signal;
    List<DeviceModelDao> list;

    private DaoSession mDaoSession;
    private Menu menu;
    //et_order_date,et_expire_date,  ,mOrderDate,mExpireDate
    private TextView mOrderDate,mExpireDate,tv_name,tv_age,tv_device;
    private EditText mIdDeviceEditText;
    private Button mAddDeviceButton;
    //tv_heart_rate,tv_respiratory,tv_BO2,tv_posture,tv_steps
    private CheckBox mHeart_rate,mRespiratory,mBO2,mPosture,mSteps;
    private ImageView iv_heart_rate,iv_respiratory,iv_BO2,iv_steps,iv_heart_rate_v,iv_user_image,iv_posture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Patient Detail");
        setSupportActionBar(mToolbar);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        mRecyclerView=(RecyclerView)  findViewById(R.id.card_event_recycler_view);
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_age=(TextView) findViewById(R.id.tv_age);
        tv_device=(TextView) findViewById(R.id.tv_device);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        iv_user_image=(ImageView) findViewById(R.id.iv_user_image);
        iv_heart_rate=(ImageView) findViewById(R.id.iv_heart_rate);
        iv_heart_rate.setColorFilter(getResources().getColor(R.color.gray));

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

        btn_signal=(Button) findViewById(R.id.btn_signal);
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
                Intent mPatient_Ac=new Intent(DetailActivity1.this,MainActivity2.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });
        //String time, String day, String date, String msg
        ArrayList<EventModel> list = new ArrayList<EventModel>();
        EventModel eventModel=new EventModel("12am","Sat","5 Aug 2018","heart beat 340");
        list.add(eventModel);


        list.add(new EventModel("3am","Sun"," 09 Sep 2018","asprin taken & sleep"));
        list.add(new EventModel("2am","Tue","10 Oct 2018","he is stable"));
        list.add(new EventModel("12pm","Fri","2 May 2018","ne two three four five six seven eight nine ten  "));

        mEventAdapter = new EventAdapter(list,DetailActivity1.this);
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mEventAdapter);

        btn_signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mMain_Ac=new Intent(DetailActivity1.this,HumanSignalActivity.class);
                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mMain_Ac);
                //finish();
            }
        });
    }
}
