package com.example.a.ubi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

import static com.example.a.ubi.RegisterActivity.MY_PREFS_USER;

public class DeviceActivity extends AppCompatActivity implements MyDialogFragment.UserDeviceListener{
    private Toolbar mToolbar;

    private DeviceAdapter mDeviceAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;


    private Button btnAdd_device;
    private ArrayList<DeviceModel> list;

    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        btnAdd_device=(Button) findViewById(R.id.btn_add_device);
        mRecyclerView =(RecyclerView) findViewById(R.id.card_device_recycler_view);
        //mRecyclerView.setNestedScrollingEnabled(false);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(DeviceActivity.this,MainActivity.class);
                                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mMain_Ac);
                                finish();
                                break;

                            case R.id.navigation_device:
                                Intent mPatient_Ac=new Intent(DeviceActivity.this,PatientActivity.class);
                                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mPatient_Ac);
                                finish();

                                break;

                            case R.id.navigation_favorite:
                                Intent mEdit_Ac=new Intent(DeviceActivity.this,EditActivity.class);
                                mEdit_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mEdit_Ac);
                                finish();


                        }
                        return true;
                    }
                });


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
                Intent mPatient_Ac=new Intent(DeviceActivity.this,MainActivity.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });


        if(mDaoSession.getDeviceModelDaoDao().loadAll().size() == 0)
        mDaoSession.getDeviceModelDaoDao().insert(new DeviceModelDao("UBI104","2018","2019","+41","6","98","pp","300"));

        List<DeviceModelDao> list = new ArrayList<DeviceModelDao>();
        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getDeviceModelDaoDao().loadAll().size() != 0){
            list=mDaoSession.getDeviceModelDaoDao().loadAll();
        }



        mDeviceAdapter = new DeviceAdapter(list,DeviceActivity.this);
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDeviceAdapter);

        btnAdd_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close existing dialog fragments
                FragmentManager manager = getFragmentManager();
                Fragment frag = manager.findFragmentByTag("fragment_device");
                if (frag != null) {
                    manager.beginTransaction().remove(frag).commit();
                }
                MyDialogFragment editNameDialog = new MyDialogFragment();
                getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                editNameDialog.show(manager, "fragment_device");
//                MyAlertDialogFragment alertDialogFragment = new MyAlertDialogFragment();
//                alertDialogFragment.show(manager, "fragment_device");
            }
        });

    }

//    @Override
//    public void onFinishUserDialog(String user) {
//
//    }

    @Override
    public void onFinishUserDialog(String id, String order_date, String expire_date, String phone, String respiratory, String bo2, String posture, String steps) {

        mDaoSession.getDeviceModelDaoDao().insert(new DeviceModelDao(id,order_date,expire_date,phone,respiratory,bo2,posture,steps));



        ArrayList<DeviceModelDao>
                listEdit = new ArrayList<DeviceModelDao>();
        DeviceModelDao userModel=new DeviceModelDao(id,order_date,expire_date,phone,respiratory,bo2,posture,steps);
        listEdit.add(userModel);


        mDeviceAdapter.addItems(listEdit);

        Log.i("DeviceModelDao 11",mDaoSession.getDeviceModelDaoDao().loadAll().size()+"");
    }
}
