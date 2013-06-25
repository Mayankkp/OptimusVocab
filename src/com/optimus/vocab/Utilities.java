package com.optimus.vocab;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mayank on 24/06/13.
 */
public class Utilities {

    public static String KEY_NUMBER_OF_QUESTIONS_ALLOWED = "numberOfQuestionsAllowed";
    public static int DEFAULT_NUMBER_OF_QUESTIONS_ALLOWED = 20;
    public static int TOTAL_NUMBER_OF_QUESTIONS = 3380;


    public static  int getNumberOfQuestionAvailable(Context context){
        SharedPreferences prefs = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        return  prefs.getInt(KEY_NUMBER_OF_QUESTIONS_ALLOWED, DEFAULT_NUMBER_OF_QUESTIONS_ALLOWED);

    }

    public static int getTotalNumberOFQuestions(){
        return TOTAL_NUMBER_OF_QUESTIONS;
    }
}
