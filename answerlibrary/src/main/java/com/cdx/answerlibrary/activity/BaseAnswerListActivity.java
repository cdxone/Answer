package com.cdx.answerlibrary.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cdx.answerlibrary.R;
import com.cdx.answerlibrary.view.AnswerView;

public abstract class BaseAnswerListActivity extends AppCompatActivity {

    protected Context mContext;
    protected AnswerView mAv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_answer_list);

        initParamsAndValues();

        initView();

        initData();
    }

    private void initParamsAndValues() {
        mContext = this;
    }

    private void initView() {
        mAv = findViewById(R.id.av);
    }

    protected abstract void initData();
}
