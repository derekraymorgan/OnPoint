package com.mystatscloud.onpoint;

import java.util.List;

public class examAnswers
{

	int questionID;
	List<String> answerList;
	int correctAnswer;

	examAnswers(int _questionID, List<String> _answerList, int _correctAnswer)
	{
		questionID = _questionID;
		answerList = _answerList;
		correctAnswer = _correctAnswer;
	}

}
