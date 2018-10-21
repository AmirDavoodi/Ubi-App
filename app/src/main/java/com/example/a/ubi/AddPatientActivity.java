package com.example.a.ubi;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class AddPatientActivity  extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    private Toolbar mToolbar;

    private DeviceAdapter mDeviceAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private BottomNavigationView bottomNavigationView;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private ImageView iv_user_img;
    private static int RESULT_LOAD_IMAGE = 1;
    SimpleDateFormat simpleDateFormat;

    private EditText tv_user_name;
    private Spinner genderSpinner;
    private Button btn_add_patient;
    private String gender="female";
    private DaoSession mDaoSession;

    private String picturePath;

    private EditText tv_birthday,tv_user_address,tv_user_insurance,tv_user_company_insurance,tv_user_condition;
    private TextInputLayout Ly_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient3);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Add Patient");
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent mPatient_Ac=new Intent(AddPatientActivity.this,MainActivity2.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();

        setUp();
    }

    private void setUp() {
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        iv_user_img = (ImageView) findViewById(R.id.iv_user_img);
        tv_user_name=(EditText) findViewById(R.id.tv_user_name);
        btn_add_patient=(Button) findViewById(R.id.btn_add_patient);
        genderSpinner= (Spinner) findViewById(R.id.tv_gender);

//        String[] years = {"Female","Male"};
//        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(AddPatientActivity.this, R.layout.spiner_dialog, years );
//        langAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
//        genderSpinner.setAdapter(langAdapter);

        tv_birthday=(EditText) findViewById(R.id.tv_birthday);
        Ly_birth=(TextInputLayout) findViewById(R.id.Ly_birthday);

        tv_user_address=(EditText) findViewById(R.id.tv_user_address);
        tv_user_insurance=(EditText) findViewById(R.id.tv_user_insurance);
        tv_user_company_insurance=(EditText) findViewById(R.id.tv_user_company_insurance);
        tv_user_condition=(EditText) findViewById(R.id.tv_user_condition);

        tv_birthday.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()){
                    // Do whatever you want to do man, but don't trouble your mother

                    new SpinnerDatePickerDialogBuilder()
                            .context(AddPatientActivity.this)
                            .callback(AddPatientActivity.this)
                            .spinnerTheme(R.style.NumberPickerStyle)
                            .showTitle(true)
                            .showDaySpinner(true)
                            .defaultDate(2017, 0, 1)
                            .maxDate(2020, 0, 1)
                            .minDate(2000, 0, 1)
                            .build()
                            .show();
                    return false;
                }
                 return true;
            }

        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        // bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(AddPatientActivity.this,MainActivity2.class);
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

                                Intent mDevice_Ac=new Intent(AddPatientActivity.this,DeviceActivity1.class);
                                mDevice_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mDevice_Ac);
                                finish();

                                break;

                            case R.id.navigation_favorite:
//                                Intent mfavorite_Ac=new Intent(MainActivity2.this,FavActivity.class);
//                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(mfavorite_Ac);
//                                finish();

                                Intent mfavorite_Ac = new Intent(AddPatientActivity.this, FavActivity.class);
                                mfavorite_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mfavorite_Ac);
                                finish();
                                break;


                        }
                        return true;
                    }
                });


//        Ly_birth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new SpinnerDatePickerDialogBuilder()
//                        .context(AddPatientActivity.this)
//                        .callback(AddPatientActivity.this)
//                        .spinnerTheme(R.style.NumberPickerStyle)
//                        .showTitle(true)
//                        .showDaySpinner(true)
//                        .defaultDate(2017, 0, 1)
//                        .maxDate(2020, 0, 1)
//                        .minDate(2000, 0, 1)
//                        .build()
//                        .show();
//            }
//        });

        // you need to have a list of data that you want the spinner to display
//        List<String> spinnerArray =  new ArrayList<String>();
//        spinnerArray.add("Female");
//        spinnerArray.add("Male");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, spinnerArray);
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        genderSpinner.setAdapter(adapter);

        iv_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int MyVersion = Build.VERSION.SDK_INT;
                if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!checkIfAlreadyhavePermission()) {
                        requestForSpecificPermission();
                    }

                } else {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }


            }
        });

        btn_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fillData(tv_user_name.getText().toString().trim(),gender);
            }
        });

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                gender=item.toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private  void fillData(String name,String gender){
        if(tv_user_name.getText().toString().trim().length()>0){
            if(tv_birthday.getText().toString().trim().length()>0){
              if(tv_user_address.getText().toString().trim().length()>0){

                  if(tv_user_insurance.getText().toString().trim().length()>0){

                      if(tv_user_company_insurance.getText().toString().trim().length()>0){

                          if(tv_user_condition.getText().toString().trim().length()>0){
                              registerInDataSet(tv_user_name.getText().toString().trim(),
                                      gender,
                                      tv_birthday.getText().toString().trim(),
                                      tv_user_address.getText().toString().trim(),
                                      tv_user_insurance.getText().toString().trim(),
                                      tv_user_company_insurance.getText().toString().trim(),
                                      tv_user_condition.getText().toString().trim());

                              finish();
                          }
                          else{
                              Toast.makeText(AddPatientActivity.this,"Please fill field condition.",Toast.LENGTH_SHORT).show();
                          }

                      }
                      else{
                          Toast.makeText(AddPatientActivity.this,"Please fill field insurance name company.",Toast.LENGTH_SHORT).show();
                      }
                  }
                  else{
                      Toast.makeText(AddPatientActivity.this,"Please fill field insurance name.",Toast.LENGTH_SHORT).show();
                  }

              }
              else{
                  Toast.makeText(AddPatientActivity.this,"Please fill field address.",Toast.LENGTH_SHORT).show();
              }


            }
            else{
                Toast.makeText(AddPatientActivity.this,"Please select date.",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(AddPatientActivity.this,"Please fill field name.",Toast.LENGTH_SHORT).show();
        }
    }

//    tv_user_name.getText().toString().trim(),gender,
//                                      tv_birthday.getText().toString().trim(),
//                                      tv_user_insurance.getText().toString().trim(),
//                                      tv_user_company_insurance.getText().toString().trim(),
//                                      tv_user_condition.getText().toString().trim()

    private void registerInDataSet(String name,String genderFinal,String birthdayFinal,String address,String insurance_name,String insurance_company,String condotion){
        mDaoSession.getRegisterUserModelDao().insert(new
                RegisterUserModel(name,picturePath,
                genderFinal,
                birthdayFinal,
                address,
                "",
                insurance_name,
                insurance_company,
                "",
                "",
                condotion,""));

        mDaoSession.getUserModelDaoDao().insert(new UserModelDao(name,30,"Asprine taken",condotion,"","120","95 cc","100","258","34","500"));
        // USER CREATION FOR DEMO PURPOSE
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(AddPatientActivity.this, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted

                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            android.database.Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            iv_user_img.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);

        tv_birthday.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
