package com.tni.eduapp.edu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.WordViewHolder> {

    private final LinkedList<CourseData> courseDataLinkedList;
    private LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView course_id_view;
        public final TextView course_name_view;
        final CourseAdapter mAdapter;

        public WordViewHolder(View itemView, CourseAdapter adapter) {
            super(itemView);
            course_id_view = itemView.findViewById(R.id.course_id_text);
            course_name_view = itemView.findViewById(R.id.course_name_text);
            mAdapter = adapter;
        }

    }

    public CourseAdapter(Context context, LinkedList<CourseData> c) {
        mInflater = LayoutInflater.from(context);
        courseDataLinkedList = c;
    }

    @NonNull
    @Override
    public CourseAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.course_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.WordViewHolder holder, int position) {
        CourseData mCurrent = courseDataLinkedList.get(position);
        holder.course_id_view.setText(mCurrent.getCourse_id());
        holder.course_name_view.setText(mCurrent.getCourse_name());

    }

    @Override
    public int getItemCount() {
        return courseDataLinkedList.size();
    }
}