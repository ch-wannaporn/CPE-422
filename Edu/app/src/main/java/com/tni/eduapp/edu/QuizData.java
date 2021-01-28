package com.tni.eduapp.edu;

import java.util.ArrayList;
import java.util.LinkedList;

class QuizData {
    private String question_id;
    private String question_desc;
    private String question_answer;
    private ArrayList<String> question_choice;

    public QuizData() {}

    public QuizData(String question_id, String question_desc, String question_answer)
    {
        this.question_id = question_id;
        this.question_desc = question_desc;
        this.question_answer = question_answer;
    }

    public String getQuestion_id() { return question_id; }

    public String getQuestion_desc()
    {
        return question_desc;
    }

    public String getQuestion_answer() { return getQuestion_answer(); }

    public ArrayList<String> getQuestion_choice() { return question_choice;}

    public void setQuestion_id(String question_id)
    {
        this.question_id = question_id;
    }

    public void setQuestion_desc(String question_desc)
    {
        this.question_desc = question_desc;
    }

    public void setQuestion_answer(String question_answer) { this.question_answer = question_answer; }

    public void setQuestion_choice(ArrayList<String> question_choice) { this.question_choice = question_choice; }
}