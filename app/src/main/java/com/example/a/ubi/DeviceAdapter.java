package com.example.a.ubi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DeviceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private UserAdapter.Callback mCallback;
    private List<DeviceModelDao> mCardResponseList;
    private Context mContext;

    public DeviceAdapter(List<DeviceModelDao> cardResponseList, Context context) {
        mCardResponseList = cardResponseList;
        mContext = context;
    }

    public void setCallback(UserAdapter.Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_device1, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (mCardResponseList != null && mCardResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mCardResponseList != null && mCardResponseList.size() > 0) {
            return mCardResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<DeviceModelDao> cardList) {
        mCardResponseList.addAll(cardList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onChargeEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {


        public TextView id_device,order_date, expire_date,tv_count;
        public ImageView iv_steps,iv_posture,iv_BO2,iv_respiratory,iv_heart_rate;


//        @BindView(R.id.content_text_view)
//        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            id_device = (TextView) itemView.findViewById(R.id.tv_id_device);
            order_date=(TextView) itemView.findViewById(R.id.tv_order_date);
            expire_date=(TextView) itemView.findViewById(R.id.tv_expire_date);
            tv_count=(TextView) itemView.findViewById(R.id.tv_count);

            iv_heart_rate=(ImageView) itemView.findViewById(R.id.iv_heart_rate);
            iv_respiratory=(ImageView) itemView.findViewById(R.id.iv_respiratory);
            iv_BO2=(ImageView) itemView.findViewById(R.id.iv_BO2);
            iv_posture=(ImageView) itemView.findViewById(R.id.iv_posture);
            iv_steps=(ImageView) itemView.findViewById(R.id.iv_steps);

        }

        protected void clear() {
            id_device.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);


            final DeviceModelDao deviceModel = mCardResponseList.get(position);
            Log.i("Adapter---->",deviceModel.toString());

            if (deviceModel.getId_device() != null) {
                id_device.setText(deviceModel.getId_device());
            }

            Log.i("current",new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
            if (deviceModel.getOrder_date() != null && deviceModel.getOrder_date().trim().length()>0) {
                order_date.setText(deviceModel.getOrder_date());
                Log.i("current 0","e");
            }
            else{
                //formattedDate
                String mDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                String currentDateandTime = sdf.format(new Date());
                order_date.setText(currentDateandTime);
                //order_date.setTextColor(mContext.getResources().getColor(R.color.black));
            }

            int randomFoot=new Random().nextInt((10- 1) + 1) + 1;
            tv_count.setText(randomFoot+"");

            if (deviceModel.getExpire_date() != null && deviceModel.getExpire_date().trim().length()>0) {
                expire_date.setText(deviceModel.getExpire_date());
            }
            else{
                Calendar cal = Calendar.getInstance();
                Date today = cal.getTime();
                cal.add(Calendar.YEAR, 1); // to get previous year add -1
                Date nextYear = cal.getTime();
                expire_date.setText(nextYear.toString());
            }

            //iv_heart_rate
            if(deviceModel.getHeart_rate().equalsIgnoreCase("0")){
                iv_heart_rate.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_respiratory
            if(deviceModel.getRespiratory().equalsIgnoreCase("0")){
                iv_respiratory.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_BO2
            if(deviceModel.getBo2().equalsIgnoreCase("0")){
                iv_BO2.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }
            //iv_posture
            if(deviceModel.getPosture().equalsIgnoreCase("0")){
                iv_posture.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_steps
            if(deviceModel.getSteps().equalsIgnoreCase("0")){
                iv_steps.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Toast.makeText(mContext, "Clicked device!!!", Toast.LENGTH_SHORT).show();


                }
            });
        }
    }

    public class EmptyViewHolder extends BaseViewHolder {

//        @BindView(R.id.btn_retry)
//        Button retryButton;

//        @BindView(R.id.content_text_view)
//        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }

//        @OnClick(R.id.btn_retry)
//        void onRetryClick() {
//            if (mCallback != null)
//                mCallback.onBlogEmptyViewRetryClick();
//        }
    }
}
