package com.mystatscloud.onpoint;

import java.util.List;

/**
 * This represents the possible answers to a given test question, which only one answer is correct
 * questionID is the primary key
 */
public class examAnswers
{

	int questionID;
	List<String> answerList;
	int correctAnswer;

	/**
	 * Create a set of answers that corresponds to a certain question
	 * @param _questionID
	 * @param _answerList
	 * @param _correctAnswer
	 */
	examAnswers(int _questionID, List<String> _answerList, int _correctAnswer)
	{
		questionID = _questionID;
		answerList = _answerList;
		correctAnswer = _correctAnswer;
	}

}
