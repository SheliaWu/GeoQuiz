package com.shelia.geoquiz;

/**
 * Created by Administrator on 2016/4/14.
 */
public class TrueFalse {
    //保存地理知识问题字符串的资源ID
    private int mQuestion;
    //用来确定答案正确与否
    private boolean mTrueQuestion;
    //确定是否作弊
    private boolean mIsCheater;
    public TrueFalse(int question,boolean trueQuestion){
        mQuestion=question;
        mTrueQuestion=trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }

    public boolean isCheater() {
        return mIsCheater;
    }

    public void setIsCheater(boolean isCheater) {
        mIsCheater = isCheater;
    }
}
