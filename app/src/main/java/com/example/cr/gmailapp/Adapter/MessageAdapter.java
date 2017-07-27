package com.example.cr.gmailapp.Adapter;


import android.content.Context;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cr.gmailapp.R;
import com.example.cr.gmailapp.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder>
{
    private Context context;
    private List<Message> messages;
    private MessageAdapterListener listener;
    private SparseBooleanArray sparseBooleanArray;

    private SparseBooleanArray animatedItemsIndex;
    private boolean reverseAllAnimations = false;

    private static int currenSelectorIndex = -1;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Message message = messages.get(position);

        holder.from.setText(message.getFrom());
        holder.subject.setText(message.getSubject());
        holder.message.setText(message.getMessage());
        holder.timeStamp.setText(message.getTimestamp());

        holder.iconText.setText(message.getFrom().substring(0,1));

        holder.itemView.setActivated(sparseBooleanArray.get(position, false));

        applyReadStatus(holder, message);

        applyImportant(holder, message);

        applyIconAnimation(holder, position);

        applyProfilePicture(holder, message);

        applyClickEvents(holder, position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {
        public TextView from ,subject, message, iconText, timeStamp;
        public ImageView iconImp, imgProfile;
        public LinearLayout messageContainer;
        public RelativeLayout iconContainer, iconBack, iconFront;

        public MyViewHolder(View view)
        {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            subject = (TextView) view.findViewById(R.id.txt_primary);
            message = (TextView) view.findViewById(R.id.txt_secondary);
            iconText = (TextView) view.findViewById(R.id.icon_text);
            timeStamp = (TextView) view.findViewById(R.id.timestamp);
            iconBack = (RelativeLayout) view.findViewById(R.id.icon_back);
            iconFront = (RelativeLayout) view.findViewById(R.id.icon_front);
            iconImp = (ImageView) view.findViewById(R.id.icon_star);
            imgProfile = (ImageView) view.findViewById(R.id.icon_profile);
            messageContainer = (LinearLayout) view.findViewById(R.id.message_container);
            iconContainer = (RelativeLayout) view.findViewById(R.id.icon_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v)
        {
            listener.onRowLongClicked(getAdapterPosition());
            v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }

    public MessageAdapter(Context context, List<Message> messages, MessageAdapterListener listener)
    {
        this.context = context;
        this.messages = messages;
        this.listener = listener;
    }

    public interface MessageAdapterListener
    {
        void onIconClicked(int position);

        void onIconImportantClicked(int psotion);

        void onMessageRowClicked(int position);

        void onRowLongClicked(int position);
    }
}
