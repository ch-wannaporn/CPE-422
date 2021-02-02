package com.tni.eduapp.edu;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<CourseData> courseDataLinkedList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private CourseAdapter mAdapter;

    FirebaseDatabase database;
    DatabaseReference myRef;
    String TAG = "firecon";
    TextView fireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Edu/course/");
        //myRef.setValue("Hello");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                courseDataLinkedList.clear();

                for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        CourseData c = npsnapshot.getValue(CourseData.class);
                        courseDataLinkedList.add(c);
                }

                // Get a handle to the RecyclerView.
                mRecyclerView = findViewById(R.id.courseRecyclerview);
                // Create an adapter and supply the data to be displayed.
                mAdapter = new CourseAdapter(MainActivity.this, courseDataLinkedList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}