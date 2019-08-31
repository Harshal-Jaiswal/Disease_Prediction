package com.example.mysymptomanalyzer.Chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysymptomanalyzer.Config.Constants;
import com.example.mysymptomanalyzer.Model.ChatModel;
import com.example.mysymptomanalyzer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyChatHolder> {

    List<ChatModel> chatModelList;

    public ChatAdapter() {
    }

    public void setChatList(List<ChatModel> chatModelList) {
        this.chatModelList = chatModelList;
    }

    @NonNull
    @Override
    public MyChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.chat_holder, parent, false);
        return new MyChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Constants.TYPE_BOT:
                holder.textViewUser.setVisibility(View.GONE);
                holder.textViewBot.setText(chatModelList.get(position).getMsg());
                break;

            case Constants.TYPE_USER:
                holder.textViewUser.setText(chatModelList.get(position).getMsg());
                holder.textViewBot.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chatModelList!=null ? chatModelList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (chatModelList.get(position).equals(Constants.TYPE_BOT))
            return Constants.TYPE_BOT;
        else
            return Constants.TYPE_USER;
    }

    public class MyChatHolder extends RecyclerView.ViewHolder {
        private final TextView textViewBot;
        private final TextView textViewUser;

        public MyChatHolder(@NonNull View itemView) {
            super(itemView);
            textViewBot = itemView.findViewById(R.id.tv_bot);
            textViewUser = itemView.findViewById(R.id.tv_user);
        }
    }
}
