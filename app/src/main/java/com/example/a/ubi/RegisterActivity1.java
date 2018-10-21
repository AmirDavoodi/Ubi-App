package com.example.a.ubi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity1 extends BaseActivity {

    private ImageView profile_img;
    private EditText profile_name;
    private EditText profile_phone;
    private EditText profile_email;
    private EditText profile_password;

    //private Button btn_save;
    private Button btn_save;

    private int mMorphCounter1 = 1;

    public static final int PICK_IMAGE = 1;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private TextView returnLogin;

    private static int RESULT_LOAD_IMAGE = 1;


    public static final String MY_PREFS_USER="MY_PREFS_USER";
    public static final String MY_PREFS_NAME="MY_PREFS_NAME";
    public static final String MY_PREFS_EMAIL="MY_PREFS_EMAIl";
    public static final String MY_PREFS_PHONE="MY_PREFS_PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);


        profile_phone=(EditText) findViewById(R.id.et_phone);
        profile_name=(EditText) findViewById(R.id.et_name);
        profile_email=(EditText) findViewById(R.id.et_email);
        //profile_img=(ImageView) findViewById(R.id.iv_user_image);
        profile_password=(EditText) findViewById(R.id.et_password);

        btn_save= (Button) findViewById(R.id.btn_sign_up);


        prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);


        ImageView ivVectorImagePassword = (ImageView) findViewById(R.id.iv_password);
        ivVectorImagePassword.setColorFilter(getResources().getColor(R.color.colorAccent3));

        ImageView ivVectorImageKey = (ImageView) findViewById(R.id.iv_password);
        ivVectorImageKey.setColorFilter(getResources().getColor(R.color.colorAccent3));


        ImageView ivVectorImageName = (ImageView) findViewById(R.id.iv_name);
        ivVectorImageName.setColorFilter(getResources().getColor(R.color.colorAccent3));

        setUp();


    }
    private void setUp() {

//        profile_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                int MyVersion = Build.VERSION.SDK_INT;
//                if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
//                    if (!checkIfAlreadyhavePermission()) {
//                        requestForSpecificPermission();
//                    }
//
//                }
//                else{
//                    Intent i = new Intent(
//                            Intent.ACTION_PICK,
//                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                    startActivityForResult(i, RESULT_LOAD_IMAGE);
//                }
//
//
//
//            }
//        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE).edit();
                editor.putString("name", profile_name.getText().toString());
                editor.putString("email", profile_email.getText().toString());
                editor.putString("phone", profile_phone.getText().toString());
                editor.commit(); // commit changes

//                prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);
//                Log.i("Register", prefs.getString("name", null));
            }
        });

//============================================================================================

        //final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.btnMorph1);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(profile_email.getText().toString().length()>0){

                    if(profile_password.getText().toString().length()>0){

                        if(profile_name.getText().toString().length()>0){

                            if(profile_phone.getText().toString().length()>0){
                                editor = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE).edit();
                                editor.putString("name", profile_name.getText().toString());
                                editor.putString("email", profile_email.getText().toString());
                                editor.putString("phone", profile_phone.getText().toString());
                                editor.putString("password", profile_password.getText().toString());
                                editor.commit(); // commit changes
                                Toast.makeText(RegisterActivity1.this,"You registered successfully.",Toast.LENGTH_SHORT).show();
                                Intent intent_login=new Intent(RegisterActivity1.this,LoginActivity.class);
                                startActivity(intent_login);

//                                new Handler().postDelayed(new Runnable() {
//                                    public void run() {
//                                        //
//
//                                    }
//                                }, 2000);
                            }
                            else{
                                Toast.makeText(RegisterActivity1.this,"Please fill your phone.",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(RegisterActivity1.this,"Please fill your name.",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterActivity1.this,"Please fill your password.",Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(RegisterActivity1.this,"Please fill your email.",Toast.LENGTH_SHORT).show();
                }

            }
        });





    }
}
