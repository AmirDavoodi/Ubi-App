package com.example.a.ubi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EventAdapter  extends RecyclerView.Adapter<BaseViewHolder>{
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private UserAdapter.Callback mCallback;
    private List<EventModel> mCardResponseList;
    private Context mContext;

    public EventAdapter(List<EventModel> cardResponseList, Context context) {
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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false));
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

    public void addItems(List<EventModel> cardList) {
        mCardResponseList.addAll(cardList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onChargeEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {


        public TextView time,day,date, Msg;


//        @BindView(R.id.content_text_view)
//        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            time=(TextView) itemView.findViewById(R.id.tv_time);
            day=(TextView) itemView.findViewById(R.id.tv_day);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            Msg = (TextView) itemView.findViewById(R.id.tv_msg);
        }

        protected void clear() {
            time.setText("");
            day.setText("");
            date.setText("");
            Msg.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);


            final EventModel eventModel = mCardResponseList.get(position);
            Log.i("Adapter---->",eventModel.toString());


            if (eventModel.getTime() != null) {
                time.setText(eventModel.getTime());
            }
            if (eventModel.getDay() != null) {
                day.setText(eventModel.getDay());
            }

            if (eventModel.getDate() != null) {
                date.setText(eventModel.getDate());
            }

            if (eventModel.getMsg() != null) {
                Msg.setText(eventModel.getMsg());
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(mContext, "Clicked!!!", Toast.LENGTH_SHORT).show();
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
