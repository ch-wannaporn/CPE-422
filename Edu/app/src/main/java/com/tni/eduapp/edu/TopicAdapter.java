package com.tni.eduapp.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.LinkedList;

class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.WordViewHolder> {

    private final LinkedList<TopicData> topicDataLinkedList;
    private LayoutInflater mInflater;
    private Context context;

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView topic_id_view;
        public final TextView topic_name_view;
        public final Button btn;
        public final ImageView img;
        final TopicAdapter mAdapter;

        public WordViewHolder(View itemView, TopicAdapter adapter) {
            super(itemView);
            topic_id_view = itemView.findViewById(R.id.topic_id_text);
            topic_name_view = itemView.findViewById(R.id.topic_name_text);
            btn = itemView.findViewById(R.id.topic_quiz);
            img = itemView.findViewById(R.id.imgView);
            mAdapter = adapter;
        }
    }

    public TopicAdapter(Context c, LinkedList<TopicData> t) {
        context = c;
        mInflater = LayoutInflater.from(c);
        topicDataLinkedList = t;
    }

    @NonNull
    @Override
    public TopicAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.topic_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.WordViewHolder holder, int position) {
        TopicData mCurrent = topicDataLinkedList.get(position);
        holder.topic_id_view.setText(mCurrent.getTopic_id());
        holder.topic_name_view.setText(mCurrent.getTopic_name());
        holder.btn.setText(Integer.toString(position));
        holder.img.setContentDescription(mCurrent.getTopic_video());
        Glide.with(context)
                .load("https://i3.ytimg.com/vi/"+ mCurrent.getTopic_video() +"/hqdefault.jpg")
                .apply(new RequestOptions().override( - 36, 200))
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return topicDataLinkedList.size();
    }
}