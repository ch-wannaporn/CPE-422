package com.tni.eduapp.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView csel;
    TextView tsel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        String cos = intent.getStringExtra("couseselect");
        String tos = intent.getStringExtra("topicselect");

        csel = findViewById(R.id.coursesel);
        csel.setText(cos);

        tsel = findViewById(R.id.topicsel);
        tsel.setText(tos);
    }
}