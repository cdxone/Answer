package com.cdx.answer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cdx.answerlibrary.view.AnswerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnswerView av = findViewById(R.id.av);
        av.setData("book_list.xml");
    }
}
