package com.shelia.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private Button mCheatButton;
    private static final String TAG="QuizActivity";
    private static final String KEY_INDEX="index";
    private static final String KEY_ISCHEATER="isCheater";
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(data==null){
            return;
        }else{
            mQuestionBank[mCurrentIndex].setIsCheater(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false));
        }
    }


    private TrueFalse[] mQuestionBank=new TrueFalse[]{
            //多次调用构造方法，创建了TrueFalse对象数组,第一个参数为字符串资源ID不是string类型
            new TrueFalse(R.string.question_ocean,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true),
    };
    private int mCurrentIndex=0;
    //封装公共代码
    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    //添加检查回答的答案方法，也是封装公共方法
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId=0;
        if(mQuestionBank[mCurrentIndex].isCheater()){
            messageResId=R.string.judgement_toast;
        }else{
            if(userPressedTrue==answerIsTrue){
                messageResId=R.string.correct_toast;
            }else{
                messageResId=R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            mQuestionBank[mCurrentIndex].setIsCheater(savedInstanceState.getBoolean(KEY_ISCHEATER));
        }
        updateQuestion();
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        //监听器,匿名内部类
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkAnswer(false);
            }
        });
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mBackButton=(ImageButton)findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        mCheatButton=(Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式Intent
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i,0);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        saveInstanceState.putBoolean(KEY_ISCHEATER,mQuestionBank[mCurrentIndex].isCheater());
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

}
