package com.example.a.ubi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MyPatientDialogFragment.UserListener {
    
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        names = getResources().getStringArray(R.array.status);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView=(RecyclerView)  findViewById(R.id.card_user_recycler_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                break;

                            case R.id.navigation_device:
                                Intent mPatient_Ac=new Intent(MainActivity.this,PatientActivity.class);
                                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mPatient_Ac);
                                break;

                            case R.id.navigation_favorite:
                                Intent mEdit_Ac=new Intent(MainActivity.this,EditActivity.class);
                                mEdit_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mEdit_Ac);
                                //finish();
                                break;


                        }
                        return true;
                    }
                });

        //btn_add_patient=(Button) findViewById(R.id.btn_add_patient);

        setUp();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        mUserAdapter.notifyDataSetChanged();

    }


    private void setUp() {

        setSupportActionBar(mToolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        list = new ArrayList<UserModelDao>();

        if(mDaoSession.getUserModelDaoDao().loadAll().size() == 0)
        mDaoSession.getUserModelDaoDao().insert(new UserModelDao("Ehsan",30,"Asprine taken","","+41","120","","95 cc","258","ii","500"));
        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserModelDaoDao().loadAll().size() != 0){
            list=mDaoSession.getUserModelDaoDao().loadAll();

        }
        Log.i("UserModelDao",list.size()+"");

        mUserAdapter = new UserAdapter(list,MainActivity.this);
       // mUserAdapter.addItems(list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mUserAdapter);




//        btn_add_patient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // close existing dialog fragments
//                FragmentManager manager = getFragmentManager();
//                Fragment frag = manager.findFragmentByTag("fragment_patient");
//                if (frag != null) {
//                    manager.beginTransaction().remove(frag).commit();
//                }
//                MyPatientDialogFragment editPatientDialog = new MyPatientDialogFragment();
//                editPatientDialog.show(manager, "fragment_patient");
//            }
//        });

        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
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


    @Override
    public void onFinishUserDialog(int id, String name, int age,String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps) {
        //Toast.makeText(MainActivity.this,"Main",Toast.LENGTH_SHORT).show();

        mDaoSession.getUserModelDaoDao().insert(new UserModelDao(name,age,medicine,health_info,phone,heart_rate,"",respiratory,bo2,posture,steps));

        List<UserModelDao> listEdit = new ArrayList<UserModelDao>();
        listEdit.add(new UserModelDao(name,age,medicine,health_info,phone,heart_rate,"",respiratory,bo2,posture,steps));

        Log.i("UserModelDao 11",mDaoSession.getUserModelDaoDao().loadAll().size()+" tt");

            mUserAdapter.addItems(listEdit);



        Log.i("UserModelDao 11",mDaoSession.getUserModelDaoDao().loadAll().size()+"");
    }
}
