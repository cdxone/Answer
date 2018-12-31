package com.cdx.answerlibrary.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cdx.answerlibrary.R;
import com.cdx.answerlibrary.bean.Answer;

import java.util.ArrayList;

/**
 * 问题，答案的Adapter
 */
public class AnswerAdapter extends BaseAdapter {
    private ArrayList<Answer> mAnswers = new ArrayList<>();

    public AnswerAdapter(ArrayList<Answer> answers) {
        mAnswers = answers;
    }

    @Override
    public int getCount() {
        return mAnswers.size();
    }

    @Override
    public Object getItem(int position) {
        return mAnswers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_view_lv_item, parent, false);
        TextView tvAsk = view.findViewById(R.id.tv_ask);
        tvAsk.setText(mAnswers.get(position).getTitle());
        return view;
    }
}
