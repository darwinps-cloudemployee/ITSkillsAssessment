package com.assessment.it.itskillsassessment;

public class Question {

    private final String mOptionA;
    private final String mOptionB;
    private final String mQuestion;
    private final String mOptionC;
    private final String mOptionD;
    private String mCorrectAnswer;


    public Question(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer)
    {
        mQuestion = question;
        mOptionA = optionA;
        mOptionB = optionB;
        mOptionC = optionC;
        mOptionD = optionD;
        mCorrectAnswer = correctAnswer;

    }

    public String getQuestion()
    {
            return mQuestion;
    }

    public String getOptionA()
    {
        return mOptionA;
    }

    public String getOptionB()
    {
        return mOptionB;
    }

    public String getOptionC()
    {
        return mOptionC;
    }

    public String getOptionD()
    {
        return mOptionD;
    }

    public String getCorrectAnswer()
    {
        return mCorrectAnswer;
    }

}
