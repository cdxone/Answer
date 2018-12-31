package com.cdx.answerlibrary.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdx.answerlibrary.R;
import com.cdx.answerlibrary.adpter.AnswerAdapter;
import com.cdx.answerlibrary.bean.Answer;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 问题 + 答案 View
 */
public class AnswerView extends RelativeLayout {

    private Context mContext;//上下文
    private ArrayList<Answer> mData = new ArrayList<>();//问题的数据
    private ListView mLv;
    private AnswerAdapter mAdapter;

    public AnswerView(Context context) {
        this(context, null);
    }

    public AnswerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnswerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.answer_view, this, true);
        mLv = view.findViewById(R.id.lv);
        mAdapter = new AnswerAdapter(mData);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Answer answer = mData.get(position);

                View adView = LayoutInflater.from(mContext).inflate(R.layout.ad_view_layout,null,false);
                TextView tvMessage = adView.findViewById(R.id.tv_message);
                tvMessage.setText(answer.getAnswer().replace(" ","").trim());
                new AlertDialog.Builder(mContext)
                        .setView(adView)
                        // 创建并显示对话框
                        .create()
                        .show();
            }
        });
    }

    /**
     * 设置ListView对应的数据
     *
     * @param fileName:存放问题和答案的Assert中的路劲
     */
    public void setData(String fileName) {
        ArrayList<Answer> answers = parseData(fileName);
        mData.clear();
        mData.addAll(answers);
        mAdapter.notifyDataSetChanged();
    }

    private ArrayList<Answer> parseData(String fileName) {
        ArrayList<Answer> answers = null;
        Answer answer = null;
        InputStream is = null;
        try {
            AssetManager assetManager = mContext.getAssets();
            XmlPullParser parser = Xml.newPullParser();
            is = assetManager.open("book_list.xml", AssetManager.ACCESS_STREAMING);//按顺序读取
            parser.setInput(is, "utf-8");
            //开始解析
            int type = parser.getEventType();//解析的类型,光标在文件的开头
            while (type != XmlPullParser.END_DOCUMENT) {//如果当前的类型不是文档的结束，就继续解析
                Log.e("type",type+"");
                if (type == XmlPullParser.START_DOCUMENT) {//如果解析到了START_DOCUMENT(文档开始标记:<?xml version="1.0" encoding="UTF-8"?>)
                    answers = new ArrayList<>();//创建Arraylist
                } else if (type == XmlPullParser.START_TAG) {//说明解析到了START_TAG(开始标签) <>
                    if ("问题".equals(parser.getName())) {//如果开始标签的内容是
                        answer = new Answer();//创建一个Answer对象 用来保存解析出来的内容
                    } else if ("标题".equals(parser.getName())) {//如果开始标签的内容是 标题
                        answer.setTitle(parser.nextText());//调用当前节点的nextText()方法 获取<书名>标签中的内容保存到answer对象中
                    } else if ("答案".equals(parser.getName())) {//如果开始标签的内容是 答案
                        answer.setAnswer(parser.nextText());//调用当前节点的nextText()方法 获取<作者>标签中的内容保存到answer对象中
                    }
                } else if (type == XmlPullParser.END_TAG) {//说明解析到了</>结束标签
                    if ("问题".equals(parser.getName())) {//如果结束标签的内容是书
                        answers.add(answer);//说明一本书已经解析完了 把当前这本书保存到books集合中
                        answer = null;//把book对象置空用来保存下一个对象
                    }
                }
                type = parser.next();//解析下一个节点，将光标移动到下个节点处
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return answers;
    }
}
