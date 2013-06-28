package com.optimus.vocab;

import java.util.ArrayList;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener {
	ArrayList<Question> questions;
	TextView textviewQuestion, textviewAnswer1, textviewAnswer2,
			textviewAnswer3, textviewAnswer4;
	Button next_button, previous_button;
	int index_of_question = 0;
	AQuery aQuery;

	private void checkAnswer(String index_Clicked, TextView textView) {
		if (questions.get(index_of_question).getAnswer().equals(index_Clicked)) {
			textView.setBackgroundColor(getResources().getColor(R.color.CorrectColor));
		}else {
			textView.setBackgroundColor(getResources().getColor(R.color.FalseColor));
		}
			}

	private void configureUi() {
		this.textviewQuestion = (TextView) this.findViewById(R.id.question);
		this.textviewAnswer1 = (TextView) this.findViewById(R.id.answer1);
		this.textviewAnswer2 = (TextView) this.findViewById(R.id.answer2);
		this.textviewAnswer3 = (TextView) this.findViewById(R.id.answer3);
		this.textviewAnswer4 = (TextView) this.findViewById(R.id.answer4);
		this.textviewAnswer1.setOnClickListener(this);
		this.textviewAnswer2.setOnClickListener(this);
		this.textviewAnswer3.setOnClickListener(this);
		this.textviewAnswer4.setOnClickListener(this);
		this.next_button = (Button) this.findViewById(R.id.next_button);
		this.previous_button = (Button) this.findViewById(R.id.previous_button);
		this.next_button.setOnClickListener(this);
		this.previous_button.setOnClickListener(this);
		resetUI();
	}

	private ArrayList<Question> getQuestions(int startId, int endId) {
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();
		return mDbHelper.getQuestions(startId, endId);
	}

	private void showNextQuestion(){
		
		resetUI();
		
		if (this.index_of_question < this.questions.size() - 2) {
			this.index_of_question++;
			this.setUiOfQuestion(this.index_of_question);
		}

		
	}
	private void showPreviosQuestion()
	{
		resetUI();
		
		
		if (this.index_of_question > 0) {
			this.index_of_question--;
			this.setUiOfQuestion(this.index_of_question);
		}
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.next_button:
			showNextQuestion();
			break;
		case R.id.previous_button:
			showPreviosQuestion();
			break;
		case R.id.answer1:
			this.checkAnswer("1", (TextView)arg0);
			break;
		case R.id.answer2:
			this.checkAnswer("2", (TextView)arg0);
			break;
		case R.id.answer3:
			this.checkAnswer("3", (TextView)arg0);
			break;
		case R.id.answer4:
			this.checkAnswer("4", (TextView)arg0);
			break;
		default:
			break;

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.test_activity);
		
		aQuery =  new AQuery(this);
		this.configureUi();
		int startId = this.getIntent().getIntExtra("test_no", 0);
		int endId = startId + MainActivity.QUESTION_PER_TEST;
		this.questions = this.getQuestions(startId, endId);
		this.setUiOfQuestion(this.index_of_question);
		
	
	}

	/*
	 * @param question_number
	 */
	private void setUiOfQuestion(int question_number) {
		this.textviewQuestion.setText(this.questions.get(question_number)
				.getQuestion());
		this.textviewAnswer1.setText(this.questions.get(question_number)
				.getAnswerOne());
		this.textviewAnswer2.setText(this.questions.get(question_number)
				.getAnswerTwo());
		this.textviewAnswer3.setText(this.questions.get(question_number)
				.getAnswerThree());
		this.textviewAnswer4.setText(this.questions.get(question_number)
				.getAnswerFour());
		
		
		this.textviewQuestion.setTypeface(Utilities.getBoldTypeFace(this));
		this.textviewAnswer1.setTypeface(Utilities.getMediumTypeFace(this));
		this.textviewAnswer2.setTypeface(Utilities.getMediumTypeFace(this));
		this.textviewAnswer3.setTypeface(Utilities.getMediumTypeFace(this));
		this.textviewAnswer4.setTypeface(Utilities.getMediumTypeFace(this));
		
		if (index_of_question == this.questions.size() - 2) {
			// this is last question  - hide next button
			aQuery.id(R.id.next_button).invisible();
		}
		

		if (index_of_question == 0) {
			// this is last question  - hide next button
			aQuery.id(R.id.previous_button).invisible();
		}

		
	}

	
	private void resetUI(){
		
		resetTextColors();
		resetButtonVisibility();
		
	}
	
	
	private void resetButtonVisibility(){
		aQuery.id(R.id.next_button).visible();
		aQuery.id(R.id.previous_button).visible();
		
	}
	
	private void resetTextColors(){
		this.textviewAnswer1.setBackgroundColor(getResources().getColor(R.color.OptionColor));
		this.textviewAnswer2.setBackgroundColor(getResources().getColor(R.color.OptionColor));
		this.textviewAnswer3.setBackgroundColor(getResources().getColor(R.color.OptionColor));
		this.textviewAnswer4.setBackgroundColor(getResources().getColor(R.color.OptionColor));
		

		
		
	}
}
