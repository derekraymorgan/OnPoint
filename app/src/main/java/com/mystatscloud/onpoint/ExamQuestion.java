package com.mystatscloud.onpoint;

public class ExamQuestion
{
	int questionID;
	String type;
	int level;
	String question;
	int resource;


	ExamQuestion(int _questionID, String _type, int _level, String _question, int _resource)
	{
		questionID = _questionID;
		type = _type;
		level = _level;
		question = _question;
		resource = _resource;
	}

}
