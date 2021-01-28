package com.tni.eduapp.edu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.WordViewHolder> {

    private final LinkedList<QuizData> quizDataLinkedList;
    private LayoutInflater mInflater;
    private final Context context;

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView question_id_view;
        private final RadioGroup rGroup;
        final QuizAdapter mAdapter;

        public WordViewHolder(View itemView, QuizAdapter adapter) {
            super(itemView);
            question_id_view = itemView.findViewById(R.id.question_text);
            rGroup = itemView.findViewById(R.id.choice_desc);
            mAdapter = adapter;
        }
    }

    public QuizAdapter(Context c, LinkedList<QuizData> q) {
        context = c;
        mInflater = LayoutInflater.from(c);
        quizDataLinkedList = q;
    }

    @NonNull
    @Override
    public QuizAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.quiz_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.WordViewHolder holder, int position) {
        QuizData mCurrent = quizDataLinkedList.get(position);
        holder.question_id_view.setText(mCurrent.getQuestion_desc());
        for (int i = 0; i < mCurrent.getQuestion_choice().size(); i++)
        {
            RadioButton rButton = new RadioButton(context);
            rButton.setText(mCurrent.getQuestion_choice().get(i));
            holder.rGroup.addView(rButton);
        }
    }

    @Override
    public int getItemCount() {
        return quizDataLinkedList.size();
    }
}