package com.example.a.ubi;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PatientActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    private UserAdapter mPatientAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private BottomNavigationView bottomNavigationView;

    private Button btnAdd_patient;
    private ImageView user_image;
    private EditText user_name,gender,birthdate,user_avs,user_address,user_phone,user_insurance,user_company_insurance,user_natinal_code,user_reasons_visit,user_condition;
    private ArrayList<UserModel> list;
    private Spinner spinner_gender;

    private DaoSession mDaoSession;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    private void setUp() {

        setSupportActionBar(mToolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent mPatient_Ac=new Intent(PatientActivity.this,MainActivity.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();
                
            }
        });
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "greendao_demo.db").getWritableDb()).newSession();
        btnAdd_patient=(Button)  findViewById(R.id.btn_add_patient);
        user_image=(ImageView) findViewById(R.id.iv_user_image);
        user_name=(EditText) findViewById(R.id.tv_user_name) ;
        spinner_gender=(Spinner) findViewById(R.id.tv_gender);
        birthdate=(EditText) findViewById(R.id.tv_birthdate);
        user_avs=(EditText) findViewById(R.id.tv_user_avs);
        user_address=(EditText) findViewById(R.id.tv_user_address);
        user_phone=(EditText) findViewById(R.id.tv_user_phone);
        user_insurance=(EditText) findViewById(R.id.tv_user_insurance);
        user_company_insurance=(EditText) findViewById(R.id.tv_user_company_insurance);
        user_natinal_code=(EditText)  findViewById(R.id.tv_user_natinal_code);
        user_reasons_visit=(EditText) findViewById(R.id.tv_user_reasons_visit);
        user_condition=(EditText) findViewById(R.id.tv_user_condition);

        user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int MyVersion = Build.VERSION.SDK_INT;
                if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (!checkIfAlreadyhavePermission()) {
                        requestForSpecificPermission();
                    }

                }
                else{
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }

            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mPatient_Ac=new Intent(PatientActivity.this,MainActivity.class);
                                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mPatient_Ac);
                                finish();
                                break;

                            case R.id.navigation_device:

                                break;

                            case R.id.navigation_favorite:
                                Intent mEdit_Ac=new Intent(PatientActivity.this,EditActivity.class);
                                mEdit_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mEdit_Ac);
                                finish();
                                break;



                        }
                        return true;
                    }
                });

//        user_name=(EditText) findViewById(R.id.tv_user_name) ;
//        gender=(EditText) findViewById(R.id.tv_gender);
//        birthdate=(EditText) findViewById(R.id.tv_birthdate);
//        user_avs=(EditText) findViewById(R.id.tv_user_avs);
//        user_address=(EditText) findViewById(R.id.tv_user_address);
//        user_phone=(EditText) findViewById(R.id.tv_user_phone);
//        user_insurance=(EditText) findViewById(R.id.tv_user_insurance);
//        user_company_insurance=(EditText) findViewById(R.id.tv_user_company_insurance);
//        user_natinal_code=(EditText)  findViewById(R.id.tv_user_natinal_code);
//        user_reasons_visit=(EditText) findViewById(R.id.tv_user_reasons_visit);
//        user_condition=(EditText) findViewById(R.id.tv_user_condition);

        btnAdd_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_name.getText().toString().length()>0){
                    if(gender.getText().toString().length()>0){
                        if(birthdate.getText().toString().length()>0){
                            if(user_avs.getText().toString().length()>0){
                               // if(user_address.getText().toString().length()>0){
                                    if(user_phone.getText().toString().length()>0){
                                        if(user_insurance.getText().toString().length()>0){
                                            if(user_company_insurance.getText().toString().length()>0){
                                                if(user_natinal_code.getText().toString().length()>0){
                                                    if(user_reasons_visit.getText().toString().length()>0){
                                                        if(user_condition.getText().toString().length()>0){
                                                            //String name, String pathImg, String gender, String birthDate, String address, String phone,
                                                            // String insurence, String number_insurence, String natinal_code, String reasons_visit,
                                                            // String condition, String device)
                                                            mDaoSession.getRegisterUserModelDao().insert(new
                                                                    RegisterUserModel(user_name.getText().toString(),"",
                                                                    gender.getText().toString(),
                                                                    birthdate.getText().toString(),
                                                                    user_address.getText().toString(),
                                                                    user_phone.getText().toString(),
                                                                    user_insurance.getText().toString(),
                                                                    user_company_insurance.getText().toString(),
                                                                    user_natinal_code.getText().toString(),
                                                                    user_reasons_visit.getText().toString(),
                                                                    user_condition.getText().toString(),""));

                                                            mDaoSession.getUserModelDaoDao().insert(new UserModelDao(user_name.getText().toString(),30,"Asprine taken","",user_phone.getText().toString(),"120","95 cc","100","258","ii","500"));
                                                            // USER CREATION FOR DEMO PURPOSE

                                                        }
                                                        else{
                                                            //user_condition
                                                            Toast.makeText(PatientActivity.this,"Please fill field condition.",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                    else{
                                                        //user_reasons_visit
                                                        Toast.makeText(PatientActivity.this,"Please fill field reasons visit.",Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                                else{
                                                    //user_natinal_code
                                                    Toast.makeText(PatientActivity.this,"Please fill field national code.",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else{
                                                //user_company_insurance
                                                Toast.makeText(PatientActivity.this,"Please fill field insurance company name.",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            //user_insurance
                                            Toast.makeText(PatientActivity.this,"Please fill field insurance name.",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else{
                                        Toast.makeText(PatientActivity.this,"Please fill field phone.",Toast.LENGTH_SHORT).show();
                                    }
//                                }
//                                else{
//                                    Toast.makeText(PatientActivity.this,"Please fill field address.",Toast.LENGTH_SHORT).show();
//                                }
                            }
                            else{
                                Toast.makeText(PatientActivity.this,"Please fill field avs.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(PatientActivity.this,"Please fill field birthdate.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(PatientActivity.this,"Please fill field gender.",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(PatientActivity.this,"Please fill field name.",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(PatientActivity.this, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
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
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            android.database.Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            user_image.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
}
