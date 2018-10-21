package com.example.a.ubi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;

    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;


    private Button btnAdd_device;
    List<UserModelDao> list;

    private DaoSession mDaoSession;
    private Menu menu;

    public static final String MY_PREFS_USER="MY_PREFS_USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav1);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//
//        View headerView = navigationView.getHeaderView(0);
//        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_name);
//
//        prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);
//        String restoredNameText = prefs.getString("name", null);
//        if (restoredNameText != null) {
//            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
//            navUsername.setText(name);
//        }
//
//        TextView navEmail = (TextView) headerView.findViewById(R.id.tv_gmail);
//        String restoredEmailText = prefs.getString("email", null);
//        if (restoredEmailText != null) {
//            String email = prefs.getString("email", "No email defined");//"No name defined" is the default value.
//            navEmail.setText(email);
//        }


        mRecyclerView =(RecyclerView) findViewById(R.id.card_device_recycler_view);
        //mRecyclerView.setNestedScrollingEnabled(false);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(FavActivity.this,MainActivity2.class);
                                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mMain_Ac);
                                finish();
                                break;

                            case R.id.navigation_device:
                                Intent mDevice_Ac=new Intent(FavActivity.this,DeviceActivity1.class);
                                mDevice_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mDevice_Ac);
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
                Intent mPatient_Ac=new Intent(FavActivity.this,MainActivity2.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });


        if(mDaoSession.getDeviceModelDaoDao().loadAll().size() == 0)
            mDaoSession.getDeviceModelDaoDao().insert(new DeviceModelDao("UBI104","2018","2019","+41","6","98","pp","300"));

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(FavActivity.this, "greendao_demo.db").getWritableDb()).newSession();

        list = new ArrayList<UserModelDao>();

        if(mDaoSession.getUserModelDaoDao().loadAll().size() == 0)
            mDaoSession.getUserModelDaoDao().insert(new UserModelDao("Ehsan",30,"Asprine taken","","+41","120","","95 cc","258","ii","500"));
        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserModelDaoDao().loadAll().size() != 0){
            list=mDaoSession.getUserModelDaoDao().loadAll();

        }
        Log.i("UserModelDao",list.size()+"");

        mUserAdapter = new UserAdapter(list,FavActivity.this);
        // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(FavActivity.this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUserAdapter);



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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        }
//        else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
