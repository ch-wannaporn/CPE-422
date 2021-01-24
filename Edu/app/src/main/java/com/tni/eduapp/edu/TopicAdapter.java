package com.tni.eduapp.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.LinkedList;

class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.WordViewHolder> {

    private final LinkedList<TopicData> topicDataLinkedList;
    private LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView topic_id_view;
        public final TextView topic_name_view;
        final TopicAdapter mAdapter;
        private YouTubePlayerView youTubePlayerView;
        private YouTubePlayer youTubePlayer;
        private String currentVideoId;


        public WordViewHolder(View itemView, TopicAdapter adapter) {
            super(itemView);
            topic_id_view = itemView.findViewById(R.id.topic_id_text);
            topic_name_view = itemView.findViewById(R.id.topic_name_text);
            youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);
            mAdapter = adapter;

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.cueVideo(currentVideoId, 0);
                }
            });
        }

        void cueVideo(String videoId) {
            currentVideoId = videoId;

            if(youTubePlayer == null)
                return;

            youTubePlayer.cueVideo(videoId, 0);
        }
    }

    public TopicAdapter(Context context, LinkedList<TopicData> t) {
        mInflater = LayoutInflater.from(context);
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
        holder.cueVideo(mCurrent.getTopic_video());
    }

    @Override
    public int getItemCount() {
        return topicDataLinkedList.size();
    }
}