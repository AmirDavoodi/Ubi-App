package com.example.a.ubi;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class UserAdapter  extends RecyclerView.Adapter<BaseViewHolder>{
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;



    private Callback mCallback;
    private List<UserModelDao> mCardResponseList;
    private Context mContext;

    public UserAdapter(List<UserModelDao> cardResponseList, Context context) {
        mCardResponseList = cardResponseList;
        mContext = context;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new UserAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row4, parent, false));
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

    public void addItems(List<UserModelDao> cardList) {
        mCardResponseList.addAll(cardList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onChargeEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {


        public TextView name,age, heart_rate,medicine,respiratory,BO2,steps,posture,tv_connection,tv_mdg,tv_status;
        public ImageView iv_steps,iv_posture,iv_BO2,iv_respiratory,iv_heart_rate,iv_heart_rate_v,iv_connection,iv_notification;


//        @BindView(R.id.content_text_view)
//        TextView contentTextView;


//        iv_respiratory=(ImageView) findViewById(R.id.iv_respiratory);
//        iv_respiratory.setColorFilter(R.color.white);
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            age=(TextView) itemView.findViewById(R.id.tv_age);
            heart_rate=(TextView) itemView.findViewById(R.id.tv_heart_rate);
            respiratory=(TextView) itemView.findViewById(R.id.tv_respiratory);
            BO2=(TextView) itemView.findViewById(R.id.tv_BO2);
            steps=(TextView) itemView.findViewById(R.id.tv_steps);
            medicine=(TextView) itemView.findViewById(R.id.tv_medicine);
            posture=(TextView) itemView.findViewById(R.id.tv_posture);

            tv_connection=(TextView) itemView.findViewById(R.id.tv_connection);
            tv_mdg=(TextView) itemView.findViewById(R.id.tv_mdg);
            tv_status=(TextView) itemView.findViewById(R.id.tv_status);


            iv_heart_rate=(ImageView) itemView.findViewById(R.id.iv_heart_rate);
            iv_heart_rate.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
            //iv_heart_rate.setColorFilter(R.color.gray);

            iv_respiratory=(ImageView) itemView.findViewById(R.id.iv_respiratory);
            iv_respiratory.setColorFilter(R.color.gray);

            iv_BO2=(ImageView) itemView.findViewById(R.id.iv_BO2);
            iv_BO2.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);
           // iv_BO2.setColorFilter(R.color.gray);

            iv_posture=(ImageView) itemView.findViewById(R.id.iv_posture);
            iv_posture.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);

            iv_steps=(ImageView) itemView.findViewById(R.id.iv_steps);
            iv_steps.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);


            iv_heart_rate_v=(ImageView) itemView.findViewById(R.id.iv_heart_rate_v);
            iv_heart_rate_v.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_IN);

            iv_notification=(ImageView)  itemView.findViewById(R.id.iv_notification);
            iv_connection=(ImageView) itemView.findViewById(R.id.iv_connection);
        }

        protected void clear()
        {
            name.setText("");
            age.setText("");
            heart_rate.setText("");
            respiratory.setText("");
            BO2.setText("");
            posture.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);


            final UserModelDao userModel = mCardResponseList.get(position);
            Log.i("Adapter-User--->",userModel.toString());

            if (userModel.getName() != null) {
                name.setText(userModel.getName());
            }

            //iv_notification
            BadgeView badge = new BadgeView(mContext, iv_notification);
            badge.setText(position+1+"");
            badge.getHorizontalBadgeMargin();
            badge.show();
            //person.getId()==0

            Log.i("ggg",(userModel.getAge())+"   9999");
            if (userModel.getAge() > 0) {
                age.setText(Integer.toString(userModel.getAge()));
            }

            if((position % 2)> 0){
                iv_connection.setImageResource(R.drawable.ic_disconnected);
                tv_mdg.setText("0");
                tv_connection.setText("Off");
                tv_status.setText("No Comment");
            }


            if (userModel.getHeart_rate() != null) {
                heart_rate.setText(userModel.getHeart_rate());
            }

            if(userModel.getPosture() !=null){
                posture.setText(userModel.getPosture());
                switch (userModel.getPosture()){
                    case "walking":
                        iv_posture.setImageResource(R.drawable.ic_pedestrian_walking);
                        break;

                    case "lying":
                        iv_posture.setImageResource(R.drawable.ic_illness_on_bed);
                        break;

                    case "sitting":
                        iv_posture.setImageResource(R.drawable.ic_sitting);
                        break;

                        default:
                            iv_posture.setImageResource(R.drawable.ic_standing_man);
                }
            }

            if(userModel.getRespiratory() != null){
                respiratory.setText(userModel.getRespiratory());
            }
            //BO2
            if(userModel.getBo2() != null){
                BO2.setText(userModel.getBo2());
            }

            //iv_steps
            if(userModel.getSteps() != null){
                steps.setText(userModel.getSteps());
            }

//            if (userModel.getMedicine() != null) {
//                medicine.setText(userModel.getMedicine());
//            }


            //iv_heart_rate
            if(userModel.getHeart_rate().equalsIgnoreCase("0")){
                iv_heart_rate.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_respiratory
            if(userModel.getRespiratory().equalsIgnoreCase("0")){
                iv_respiratory.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_BO2
            if(userModel.getBo2().equalsIgnoreCase("0")){
                iv_BO2.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }
            //iv_posture
            if(userModel.getPosture().equalsIgnoreCase("0")){
                iv_posture.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            //iv_steps
            if(userModel.getSteps().equalsIgnoreCase("0")){
                iv_steps.setColorFilter(mContext.getResources().getColor(R.color.gray));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mContext, "Clicked!!!", Toast.LENGTH_SHORT).show();
                    Intent mDetail_Ac=new Intent(mContext,DetailActivity1.class);
                    mContext.startActivity(mDetail_Ac);

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
