package com.example.a.ubi;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MyPatientDialogFragment extends DialogFragment implements TextView.OnEditorActionListener{


    //et_order_date,et_expire_date
    private EditText mName,mMedicine,mhealth_info;
    private Button mAddPatientButton;
    //tv_heart_rate,tv_respiratory,tv_BO2,tv_posture,tv_steps
    private CheckBox mHeart_rate,mRespiratory,mBO2,mPosture,mSteps;

    // Empty constructor required for DialogFragment
    public MyPatientDialogFragment() {}

    public interface UserListener {
        //(int id, String name, int age, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps)
        void onFinishUserDialog(int id, String name,int age, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient, container);
        mName = (EditText) view.findViewById(R.id.et_id_name);
        mMedicine=(EditText) view.findViewById(R.id.et_medicine);
        mhealth_info=(EditText) view.findViewById(R.id.et_health_info);

        mHeart_rate=(CheckBox) view.findViewById(R.id.tv_heart_rate);
        mRespiratory=(CheckBox) view.findViewById(R.id.tv_respiratory);
        mBO2=(CheckBox) view.findViewById(R.id.tv_BO2);
        mPosture=(CheckBox) view.findViewById(R.id.tv_posture);
        mSteps=(CheckBox) view.findViewById(R.id.tv_steps);

        mAddPatientButton=(Button)  view.findViewById(R.id.btn_save);

        // set this instance as callback for editor action
       // mName.setOnEditorActionListener(this);
        mAddPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPatientDialogFragment.UserListener activity = (MyPatientDialogFragment.UserListener) getActivity();
                //(int id, String name, String medicine, String health_info, String phone, String heart_rate, String respiratory, String bo2, String posture, String steps)

                activity.onFinishUserDialog(4,(mName.getText().toString()),5,mMedicine.getText().toString(),mhealth_info.getText().toString(),"",
                        valueCheckBox(mHeart_rate), valueCheckBox(mRespiratory), valueCheckBox(mBO2), valueCheckBox(mPosture),valueCheckBox(mSteps));
                getDialog().dismiss();
            }
        });
        mName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        getDialog().setTitle("Please enter new Patient");

        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        // Return input text to activity
        MyPatientDialogFragment.UserListener activity = (MyPatientDialogFragment.UserListener) getActivity();
        activity.onFinishUserDialog(4,(mName.getText().toString()),5,mMedicine.getText().toString(),mhealth_info.getText().toString(),
                valueCheckBox(mHeart_rate), "",valueCheckBox(mRespiratory), valueCheckBox(mBO2), valueCheckBox(mPosture),valueCheckBox(mSteps));
        this.dismiss();
        return true;
    }

    public interface OnEditorActionListener {
        boolean onEditorAction(TextView var1, int var2, KeyEvent var3);
    }

    public String valueCheckBox(CheckBox checkBox){
        if(checkBox.isChecked()){
            return "1";
        }
        return "0";
    }
}
