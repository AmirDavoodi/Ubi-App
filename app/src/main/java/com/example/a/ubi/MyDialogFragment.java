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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    //et_order_date,et_expire_date,  ,mOrderDate,mExpireDate
    private TextView mOrderDate,mExpireDate;
    private EditText mIdDeviceEditText;
    private Button mAddDeviceButton;
    //tv_heart_rate,tv_respiratory,tv_BO2,tv_posture,tv_steps
    private CheckBox mHeart_rate,mRespiratory,mBO2,mPosture,mSteps;


    public interface UserDeviceListener {
        //(int id, String order_date, String expire_date, String phone, String respiratory, String bo2, String posture, String steps)
        void onFinishUserDialog(String id, String order_date, String expire_date, String phone, String respiratory, String bo2, String posture, String steps);
    }

    // Empty constructor required for DialogFragment
    public MyDialogFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.WideDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device1, container);
        mIdDeviceEditText = (EditText) view.findViewById(R.id.et_id_device);
        mOrderDate=(TextView) view.findViewById(R.id.tv_order_date);
        mExpireDate=(TextView) view.findViewById(R.id.tv_expire_date);


        //formattedDate
        String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String currentDateandTime = sdf.format(new Date());
        mOrderDate.setText(currentDateandTime);

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        mExpireDate.setText(nextYear.toString());

        mHeart_rate=(CheckBox) view.findViewById(R.id.tv_heart_rate);
        mRespiratory=(CheckBox) view.findViewById(R.id.tv_respiratory);
        mBO2=(CheckBox) view.findViewById(R.id.tv_BO2);
        mPosture=(CheckBox) view.findViewById(R.id.tv_posture);
        mSteps=(CheckBox) view.findViewById(R.id.tv_steps);

        mAddDeviceButton=(Button)  view.findViewById(R.id.btn_save);

        // set this instance as callback for editor action
        mIdDeviceEditText.setOnEditorActionListener(this);
        mAddDeviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDeviceListener activity = (UserDeviceListener) getActivity();

                //formattedDate
                String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                String currentDateandTime = sdf.format(new Date());

                Calendar cal = Calendar.getInstance();
                Date today = cal.getTime();
                cal.add(Calendar.YEAR, 1); // to get previous year add -1
                Date nextYear = cal.getTime();



                activity.onFinishUserDialog((mIdDeviceEditText.getText().toString()),currentDateandTime,nextYear.toString(),
                        valueCheckBox(mHeart_rate), valueCheckBox(mRespiratory), valueCheckBox(mBO2), valueCheckBox(mPosture),valueCheckBox(mSteps));
                getDialog().dismiss();
            }
        });
        mIdDeviceEditText.requestFocus();
//        getDialog().getWindow ().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       getDialog().setTitle("Please enter new device");

        return view;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Return input text to activity
        UserDeviceListener activity = (UserDeviceListener) getActivity();

        //formattedDate
        String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String currentDateandTime = sdf.format(new Date());

        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();

        activity.onFinishUserDialog((mIdDeviceEditText.getText().toString()),currentDateandTime,nextYear.toString(),
                valueCheckBox(mHeart_rate), valueCheckBox(mRespiratory), valueCheckBox(mBO2), valueCheckBox(mPosture),valueCheckBox(mSteps));
        this.dismiss();
        return true;
    }

    public String valueCheckBox(CheckBox checkBox){
        if(checkBox.isChecked()){
            return "1";
        }
        return "0";
    }
}
