package com.cdx.answerlibrary.bean;

/**
 * 问题，结果的封装类
 */
public class Answer {

    private String mTitle;//问题的标题
    private String mAnswer;//回答

    public Answer(){

    }

    public Answer(String question, String answer) {
        mTitle = question;
        mAnswer = answer;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }
}
