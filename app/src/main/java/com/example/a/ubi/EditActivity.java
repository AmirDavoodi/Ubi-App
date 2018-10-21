package com.example.a.ubi;

import android.Manifest;
import android.app.Activity;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dd.morphingbutton.MorphingButton;

import java.io.File;

public class EditActivity extends BaseActivity  {
    private Toolbar mToolbar;

    private EventAdapter mEventAdapter;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private ImageView profile_img;
    private EditText profile_name;
    private EditText profile_phone;
    private EditText profile_email;


    //private Button btn_save;
    private MorphingButton btn_save;

    private int mMorphCounter1 = 1;

    public static final int PICK_IMAGE = 1;
    private static int RESULT_LOAD_IMAGE = 1;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private BottomNavigationView bottomNavigationView;

    public static final String MY_PREFS_USER="MY_PREFS_USER";
    public static final String MY_PREFS_NAME="MY_PREFS_NAME";
    public static final String MY_PREFS_EMAIL="MY_PREFS_EMAIl";
    public static final String MY_PREFS_PHONE="MY_PREFS_PHONE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

//et_phone
        profile_phone = (EditText) findViewById(R.id.et_phone);
        profile_name = (EditText) findViewById(R.id.et_name);
        profile_email = (EditText) findViewById(R.id.et_email);
        profile_img = (ImageView) findViewById(R.id.iv_user_image);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //btn_save=(Button) findViewById(R.id.btn_register);
        btn_save = (MorphingButton) findViewById(R.id.btnMorph1);


        prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);
        String restoredNameText = prefs.getString("name", null);
        if (restoredNameText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            profile_name.setText(name, TextView.BufferType.EDITABLE);
        }

        String restoredPhoneText = prefs.getString("phone", null);
        if (restoredPhoneText != null) {
            String name = prefs.getString("phone", "No phone defined");//"No name defined" is the default value.
            profile_phone.setText(name, TextView.BufferType.EDITABLE);
        }

        String restoredEmailText = prefs.getString("email", null);
        if (restoredEmailText != null) {
            String name = prefs.getString("email", "No email defined");//"No name defined" is the default value.
            profile_email.setText(name, TextView.BufferType.EDITABLE);
        }

        String restoredImageText = prefs.getString("image", null);
        if (restoredImageText != null) {
            profile_img.setImageBitmap(BitmapFactory.decodeFile(restoredImageText));
        }


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                Intent mMain_Ac=new Intent(EditActivity.this,MainActivity.class);
                                mMain_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mMain_Ac);
                                finish();
                                break;

                            case R.id.navigation_device:
                                Intent mPatient_Ac=new Intent(EditActivity.this,PatientActivity.class);
                                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(mPatient_Ac);
                                finish();
                                break;

                            case R.id.navigation_favorite:

                                break;



                        }
                        return true;
                    }
                });


        setUp();





    }
    @Override
    public void onResume()
    {
        super.onResume();

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
                Intent mPatient_Ac=new Intent(EditActivity.this,MainActivity.class);
                mPatient_Ac.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mPatient_Ac);
                finish();

            }
        });


        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
//

//============================================================================================

        //final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(profile_name.getText().toString().length()>0){

                    if(profile_phone.getText().toString().length()>0){

                        if(profile_email.getText().toString().length()>0){
                            editor = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE).edit();
                            editor.putString("name", profile_name.getText().toString());
                            editor.putString("email", profile_email.getText().toString());
                            editor.putString("phone", profile_phone.getText().toString());
                            editor.commit(); // commit changes
                            onMorphButton1Clicked(btn_save);
                        }
                        else{
                            Toast.makeText(EditActivity.this,"Please fill your email.",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(EditActivity.this,"Please fill your number.",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(EditActivity.this,"Please fill your name.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        morphToSquare(btn_save, 0);



    }


    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(EditActivity.this, Manifest.permission.READ_CONTACTS);
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



    private void onMorphButton1Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter1 == 0) {
            mMorphCounter1++;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        } else if (mMorphCounter1 == 1) {
            mMorphCounter1 = 0;
            morphToSuccess(btnMorph);
        }
    }

    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(dimen(R.dimen.mb_width_200))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.colorPrimary))
                .colorPressed(color(R.color.darkblue))
                .text(getString(R.string.register));
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(integer(R.integer.mb_animation))
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_height_56))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_green))
                .colorPressed(color(R.color.mb_green_dark))
                .icon(R.drawable.tick);
        btnMorph.morph(circle);
        Toast.makeText(EditActivity.this,getString(R.string.profile_changed),Toast.LENGTH_LONG).show();
    }

    private void morphToFailure(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_height_56))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_red))
                .colorPressed(color(R.color.mb_red_dark))
                .icon(R.drawable.tick);
        btnMorph.morph(circle);
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


            Log.i("image",picturePath);
            editor = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE).edit();
            editor.putString("image", picturePath);
            editor.commit();

            profile_img.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }

}
