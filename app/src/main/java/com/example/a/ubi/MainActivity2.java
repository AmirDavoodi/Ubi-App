package com.example.a.ubi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Menu menu;
    private FloatingActionButton menu1,menu2 ;

    Fragment fragment ;
    private FrameLayout frameLayout;
    //private TabLayout tabLayout;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    public static final String MY_PREFS_USER="MY_PREFS_USER";

    private BottomNavigationView bottomNavigationView;
    //private Button btn_add_patient;

    private DaoSession mDaoSession;
    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private Handler mHandler;
    private int mInterval = 5000; // 5 seconds by default, can be changed later




    private int[] tabIcons = {
            R.drawable.ic_blood,
            R.drawable.ic_balance,
            R.drawable.ic_cards
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();


        frameLayout=(FrameLayout) findViewById(R.id.container_body);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.tv_name);

        prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);
        String restoredNameText = prefs.getString("name", null);
        if (restoredNameText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            navUsername.setText(name);
        }

        TextView navEmail = (TextView) headerView.findViewById(R.id.tv_gmail);
        String restoredEmailText = prefs.getString("email", null);
        if (restoredEmailText != null) {
            String email = prefs.getString("email", "No email defined");//"No name defined" is the default value.
            navEmail.setText(email);
        }




        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Patient");
        adapter.addFragment(new TwoFragment(), "Device");
        adapter.addFragment(new ThreeFragment(), "Message");
        viewPager.setAdapter(adapter);

        setupTabLayout(viewPager, adapter);




        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
       // bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
//                                popAllFragments();
//                                viewPager.setVisibility(View.GONE);
//                                frameLayout.setVisibility(View.VISIBLE);
//                                replaceFragment(R.id.container_body,
//                                        new PatientFragment(),
//                                        PatientFragment.FRAGMENT_TAG,null);
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

                                Intent mDevice_Ac=new Intent(MainActivity2.this,DeviceActivity1.class);
                                mDevice_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mDevice_Ac);
                                finish();

                                break;

                            case R.id.navigation_favorite:
//                                Intent mfavorite_Ac=new Intent(MainActivity2.this,FavActivity.class);
//                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(mfavorite_Ac);
//                                finish();

                                Intent mfavorite_Ac = new Intent(MainActivity2.this, FavActivity.class);
                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mfavorite_Ac);
                                finish();
                                break;


                        }
                        return true;
                    }
                });


//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getText().toString()) {
//                    case "Patient":
//                        //todo your code
//                        popAllFragments();
//                        viewPager.setVisibility(View.VISIBLE);
//                        frameLayout.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity2.this,"pa",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case "Device":
//                        //todo your code
//                        popAllFragments();
//                        viewPager.setVisibility(View.VISIBLE);
//                        frameLayout.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity2.this,"de",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case "Message":
//                        //todo your code
//                        popAllFragments();
//                        viewPager.setVisibility(View.VISIBLE);
//                        frameLayout.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity2.this,"me",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                switch (tab.getText().toString()) {
//                    case "yourTabTitle":
//                        //todo your code
//                        break;
//                }
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                switch (tab.getText().toString()) {
//                    case "yourTabTitle":
//                        //todo your code
//                        break;
//                }
//            }
//        });


        popAllFragments();
        viewPager.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        replaceFragment(R.id.container_body,
                new PatientFragment(),
                PatientFragment.FRAGMENT_TAG,null);



        menu1 = (FloatingActionButton)findViewById(R.id.subFloatingMenu1) ;
        menu2 = (FloatingActionButton)findViewById(R.id.subFloatingMenu2) ;

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mAdd_device_Ac = new Intent(MainActivity2.this, AddDeviceActivity.class);
                mAdd_device_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mAdd_device_Ac);
                //finish();

            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mAdd_device_Ac = new Intent(MainActivity2.this, AddPatientActivity.class);
                mAdd_device_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mAdd_device_Ac);

            }
        });

//        mHandler = new Handler();
//        startRepeatingTask();
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }



    private void setupTabLayout(ViewPager viewPager, ViewPagerAdapter viewPagerAdapter) {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        int length = tabLayout.getTabCount();
        for (int i = 0; i < length; i++) {
            tabLayout.getTabAt(i).setCustomView(viewPagerAdapter.getTabView(i));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Patient");
        adapter.addFragment(new TwoFragment(), "Device");
        adapter.addFragment(new ThreeFragment(), "Message");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private Activity context;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public View getTabView(int position) {
            View view = LayoutInflater.from(MainActivity2.this).inflate(R.layout.tab_layout, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView title_count = (TextView) view.findViewById(R.id.title_count);
            ImageView icon = (ImageView) view.findViewById(R.id.icon);
            ViewGroup layout = (ViewGroup) view.findViewById(R.id.layout);

            title.setText(this.getPageTitle(position));
            if(position==0){
                icon.setImageResource(R.drawable.ic_blood);
                title_count.setText("23");
            }


            if(position==1){
                icon.setImageResource(R.drawable.ic_usb1);
                title_count.setText("6");
            }


            if(position==2){
                icon.setImageResource(R.drawable.ic_speech_bubble);
                title_count.setText("9");
            }


            return view;
        }

        @Override
        public Fragment getItem(int position) {
            viewPager.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //getFragmentManager().beginTransaction().detach(MainActivity2.this).attach(this).commit();
        replaceFragment(R.id.container_body,
                new PatientFragment(),
                PatientFragment.FRAGMENT_TAG,null);
        //getFragmentManager().beginTransaction().replace(R.id.container_body,new PatientFragment(),PatientFragment.FRAGMENT_TAG,null).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_add) {
            return true;
        }

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

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
    Fragment mContent;

//    public void switchContent(Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().
//                remove(getSupportFragmentManager().findFragmentById(R.id.frame)).commit();
//        mContent= fragment;
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container_body, fragment)
//                .commit();
//      getSlidingMenu().showContent();
//    }

    public void popAllFragments() {
        // TODO Auto-generated method stub
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}

