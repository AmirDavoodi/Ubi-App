package com.example.a.ubi;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddDeviceActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private DeviceAdapter mDeviceAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;


    private Button btnAdd_device;
    List<DeviceModelDao> list;

    private DaoSession mDaoSession;
    private Menu menu;
    //et_order_date,et_expire_date,  ,mOrderDate,mExpireDate
    private TextView mOrderDate,mExpireDate;
    private EditText mIdDeviceEditText;
    private Button mAddDeviceButton;
    //tv_heart_rate,tv_respiratory,tv_BO2,tv_posture,tv_steps
    private CheckBox mHeart_rate,mRespiratory,mBO2,mPosture,mSteps;
    private ImageView iv_heart_rate,iv_respiratory,iv_BO2,iv_steps,iv_heart_rate_v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Add Device");
        setSupportActionBar(mToolbar);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        //iv_respiratory.setColorFilter(mContext.getResources().getColor(R.color.gray));
        iv_heart_rate=(ImageView) findViewById(R.id.iv_heart_rate);
        iv_heart_rate.setColorFilter(getResources().getColor(R.color.gray));

        iv_respiratory=(ImageView) findViewById(R.id.iv_respiratory);
        iv_respiratory.setColorFilter(getResources().getColor(R.color.gray));

        iv_BO2=(ImageView) findViewById(R.id.iv_BO2);
        iv_BO2.setColorFilter(getResources().getColor(R.color.gray));

        iv_steps=(ImageView) findViewById(R.id.iv_steps);
        iv_steps.setColorFilter(getResources().getColor(R.color.gray));

        iv_heart_rate_v=(ImageView) findViewById(R.id.iv_heart_rate_v);
        iv_heart_rate_v.setColorFilter(getResources().getColor(R.color.gray));

        mHeart_rate=(CheckBox) findViewById(R.id.tv_heart_rate);
        mRespiratory=(CheckBox) findViewById(R.id.tv_respiratory);
        mBO2=(CheckBox) findViewById(R.id.tv_BO2);
        mPosture=(CheckBox) findViewById(R.id.tv_posture);
        mSteps=(CheckBox) findViewById(R.id.tv_steps);

        list = new ArrayList<DeviceModelDao>();
        mDeviceAdapter = new DeviceAdapter(list,AddDeviceActivity.this);

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
                Intent mPatient_Ac=new Intent(AddDeviceActivity.this,MainActivity2.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });

        mIdDeviceEditText = (EditText) findViewById(R.id.et_device);
        mOrderDate=(TextView) findViewById(R.id.tv_order_date);
       // mExpireDate=(TextView) findViewById(R.id.tv_expire_date);


        //formattedDate
        String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String currentDateandTime = sdf.format(new Date());
        mOrderDate.setText(currentDateandTime);

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        //mExpireDate.setText(nextYear.toString());


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        // bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(AddDeviceActivity.this,MainActivity2.class);
                                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mMain_Ac);
                                finish();
                                break;

                            case R.id.navigation_device:
                                // get fragment manager
//                                FragmentManager fm = getSupportFragmentManager();
//                                fragment = fm.findFragmentByTag("Patient");
//                                if (fragment == null) {
//                                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
//                                    fragment =new PatientFragment();
//                                    ft.add(android.R.id.container_body,fragment,"myFragmentTag");
//                                    ft.commit();
//
//                                }

                                Intent mDevice_Ac=new Intent(AddDeviceActivity.this,DeviceActivity1.class);
                                mDevice_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mDevice_Ac);
                                finish();

                                break;

                            case R.id.navigation_favorite:
//                                Intent mfavorite_Ac=new Intent(MainActivity2.this,FavActivity.class);
//                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(mfavorite_Ac);
//                                finish();

                                Intent mfavorite_Ac = new Intent(AddDeviceActivity.this, FavActivity.class);
                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mfavorite_Ac);
                                finish();
                                break;


                        }
                        return true;
                    }
                });



        mAddDeviceButton=(Button)  findViewById(R.id.btn_add_device);

        mAddDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //formattedDate
                String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                String currentDateandTime = sdf.format(new Date());

                Calendar cal = Calendar.getInstance();
                Date today = cal.getTime();
                cal.add(Calendar.YEAR, 1); // to get previous year add -1
                Date nextYear = cal.getTime();
                mDaoSession = new DaoMaster(
                        new DaoMaster.DevOpenHelper(AddDeviceActivity.this, "greendao_demo.db").getWritableDb()).newSession();
//                Log.i("Raja",mIdDeviceEditText.getText().toString());
//                Log.i("Raja1",currentDateandTime.toString());
//                Log.i("Raja1",nextYear.toString());
//                Log.i("Raja2",valueCheckBox(mHeart_rate));
//                Log.i("Raja3",valueCheckBox(mRespiratory));
//                Log.i("Raja4",valueCheckBox(mBO2));
//
//                Log.i("Raja6",valueCheckBox(mSteps));


                mDaoSession.getDeviceModelDaoDao().insert(new DeviceModelDao((mIdDeviceEditText.getText().toString()),currentDateandTime,nextYear.toString(),
                        valueCheckBox(mHeart_rate), valueCheckBox(mRespiratory), valueCheckBox(mBO2), "0",valueCheckBox(mSteps)));




                ArrayList<DeviceModelDao>
                        listEdit = new ArrayList<DeviceModelDao>();
                DeviceModelDao userModel=new DeviceModelDao((mIdDeviceEditText.getText().toString()),currentDateandTime,nextYear.toString(),
                        valueCheckBox(mHeart_rate), valueCheckBox(mRespiratory), valueCheckBox(mBO2), "0",valueCheckBox(mSteps));
                listEdit.add(userModel);


                mDeviceAdapter.addItems(listEdit);
                finish();

            }
        });
    }


    public String valueCheckBox(CheckBox checkBox){
        if(checkBox.isChecked()){
            return "1";
        }
        return "0";
    }
}
