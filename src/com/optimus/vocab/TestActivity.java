package com.optimus.vocab;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener {
	ArrayList<Question> questions;
	TextView textviewQuestion,textviewAnswer1,textviewAnswer2,textviewAnswer3,textviewAnswer4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		configureUi();
		int startId = getIntent().getIntExtra("test_no", 0);
		int endId = startId + MainActivity.QUESTION_PER_TEST;
		questions = getQuestions(startId, endId);
		setUiOfQuestion(0);
	}

	private ArrayList<Question> getQuestions(int startId, int endId) {
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();
		return mDbHelper.getQuestions(startId, endId);
	}

	/*
	 * @param question_number
	 */
	private void setUiOfQuestion(int question_number) {
		textviewQuestion.setText(questions.get(0).getQuestion());
		textviewAnswer1.setText(questions.get(0).getAnswerOne());
		textviewAnswer2.setText(questions.get(0).getAnswerTwo());
		textviewAnswer3.setText(questions.get(0).getAnswerThree());
		textviewAnswer4.setText(questions.get(0).getAnswerFour());
	}

	private void configureUi() {
		textviewQuestion = (TextView) findViewById(R.id.question);
		textviewAnswer1 = (TextView) findViewById(R.id.answer1);
		textviewAnswer2 = (TextView) findViewById(R.id.answer2);
		textviewAnswer3 = (TextView) findViewById(R.id.answer3);
		textviewAnswer4 = (TextView) findViewById(R.id.answer4);
		textviewAnswer1.setOnClickListener(this);
		textviewAnswer2.setOnClickListener(this);
		textviewAnswer3.setOnClickListener(this);
		textviewAnswer4.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
