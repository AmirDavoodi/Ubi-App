package com.example.a.ubi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.a.ubi.RegisterActivity.MY_PREFS_USER;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;

    private Button btn_server_login;
    private TextView btn_register;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        mEmail=(EditText) findViewById(R.id.et_email);
        mPassword=(EditText) findViewById(R.id.et_password);
        btn_server_login=(Button) findViewById(R.id.btn_login);
        btn_register=(TextView) findViewById(R.id.tv_register_new);

        prefs = getSharedPreferences(MY_PREFS_USER, MODE_PRIVATE);

        btn_server_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String restoredEmailText = prefs.getString("email", null);
                String restoredPasswordText = prefs.getString("password", null);
                if(mEmail.getText().toString().trim().equalsIgnoreCase(restoredEmailText)){
                    if(mPassword.getText().toString().trim().equalsIgnoreCase(restoredPasswordText)){

                        Intent mMain_Ac=new Intent(LoginActivity.this,MainActivity2.class);
                        startActivity(mMain_Ac);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Please enter your password correctly",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please enter your email correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mRegister_Ac=new Intent(LoginActivity.this,RegisterActivity1.class);
                startActivity(mRegister_Ac);
                finish();
            }
        });

    }
}
