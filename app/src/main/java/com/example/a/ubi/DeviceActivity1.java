package com.example.a.ubi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity1 extends AppCompatActivity {
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
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        btnAdd_device=(Button) findViewById(R.id.btn_add_device);
        mRecyclerView =(RecyclerView) findViewById(R.id.card_device_recycler_view);
        //mRecyclerView.setNestedScrollingEnabled(false);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(DeviceActivity1.this,MainActivity2.class);
                                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mMain_Ac);
                                finish();
                                break;

                            case R.id.navigation_favorite:
                                Intent mfavorite_Ac=new Intent(DeviceActivity1.this,FavActivity.class);
                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mfavorite_Ac);
                                finish();
                              break;


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
                Intent mPatient_Ac=new Intent(DeviceActivity1.this,MainActivity2.class);
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



        mDeviceAdapter = new DeviceAdapter(list,DeviceActivity1.this);
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDeviceAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;

        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }
}
