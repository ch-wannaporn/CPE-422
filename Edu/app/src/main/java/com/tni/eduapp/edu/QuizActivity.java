package com.tni.eduapp.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import io.perfmark.Link;

public class QuizActivity extends AppCompatActivity {

    private final LinkedList<QuizData> quizDataLinkedList = new LinkedList<>();

    TextView textV;

    String FBref;
    FirebaseDatabase database;
    DatabaseReference myRef;

    String TAG = "firecon";
    String ch;

    private RecyclerView mRecyclerView;
    private QuizAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        String cos = intent.getStringExtra("couseselect");
        String tos = intent.getStringExtra("topicselect");

        FBref = cos + "/" + tos + "/topic_test";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FBref);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                    textV = findViewById(R.id.tView);

                    quizDataLinkedList.clear();

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    QuizData q = npsnapshot.getValue(QuizData.class);
                    ArrayList<String> choiceDataArrayList = new ArrayList<>();
                    for (DataSnapshot ncsnapshot : npsnapshot.child("question_choice").getChildren()) {
                        choiceDataArrayList.add(ncsnapshot.getValue().toString());
                    }
                    q.setQuestion_choice(choiceDataArrayList);
                    quizDataLinkedList.add(q);
                }
                // Get a handle to the RecyclerView.
                mRecyclerView = findViewById(R.id.quizRecyclerview);
                // Create an adapter and supply the data to be displayed.
                mAdapter = new QuizAdapter(QuizActivity.this, quizDataLinkedList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(QuizActivity.this));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}