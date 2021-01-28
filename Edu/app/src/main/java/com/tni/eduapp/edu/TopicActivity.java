package com.tni.eduapp.edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.LinkedList;

public class TopicActivity extends AppCompatActivity {

    private final LinkedList<TopicData> topicDataLinkedList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private TopicAdapter mAdapter;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String TAG = "firecon";
    TextView fireBase;
    String FBref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("POSITION", 99);
        TextView coursePos = findViewById(R.id.courseP);
        coursePos.setText(Integer.toString(pos));
        FBref = "Edu/course/" + Integer.toString(pos) + "/topic";

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(FBref);
        //myRef.setValue("Hello");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                    TopicData t = npsnapshot.getValue(TopicData.class);
                    topicDataLinkedList.add(t);
                }

                // Get a handle to the RecyclerView.
                mRecyclerView = findViewById(R.id.topicRecyclerview);
                // Create an adapter and supply the data to be displayed.
                mAdapter = new TopicAdapter(TopicActivity.this, topicDataLinkedList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(TopicActivity.this));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void ToQuiz(View view) {
        Button b = (Button) view;
        Intent intent = new Intent(TopicActivity.this, QuizActivity.class);
        Log.d("Tag", b.getTag().toString());

        intent.putExtra("couseselect", FBref);
        intent.putExtra("topicselect", b.getTag().toString());
        startActivity(intent);
    }

    public void ToLearn(View view) {
        ImageView i = (ImageView) view;
        Intent intent = new Intent(TopicActivity.this, LearnActivity.class);

        intent.putExtra("videoID", i.getContentDescription());
        startActivity(intent);
    }

}