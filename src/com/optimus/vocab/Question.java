package com.optimus.vocab;

/**
 * Created by Mayank on 24/06/13.
 */
public class Question {


    public Question( String question, String questionExp,  String answerOne, String answerTwo, String answerThree, String answerFour,String answer) {
       // this.id = id;
        this.question = question;
        this.questionExp = questionExp;
        this.answer = answer;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionExp() {
        return questionExp;
    }

    public void setQuestionExp(String questionExp) {
        this.questionExp = questionExp;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    private int id;
    private String question;
    private String questionExp;
    private String answer;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;




}
