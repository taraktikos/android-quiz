package com.example.QuizGame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras S. on 11.03.15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Quiz.db";

    private static final String TABLE_QUESTIONS = "questions";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER = "answer";
    private static final String COLUMN_OPTION_A = "option_a";
    private static final String COLUMN_OPTION_B = "option_b";
    private static final String COLUMN_OPTION_C = "option_c";
    private static final String COLUMN_OPTION_D = "option_d";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_QUESTIONS + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION
            + " TEXT, " + COLUMN_ANSWER + " TEXT, " + COLUMN_OPTION_A + " TEXT, "
            + COLUMN_OPTION_B + " TEXT, " + COLUMN_OPTION_C + " TEXT, " + COLUMN_OPTION_D + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_QUESTIONS;

    private SQLiteDatabase database;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        db.execSQL(SQL_CREATE_ENTRIES);
        addQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    private void addQuestions() {
        Question q1 = new Question("Best language", "PHP", "Java", "C", "C++", "Java");
        addQuestion(q1);
        Question q2 = new Question("Best country", "Ukraine", "USA", "GB", "Italy", "Ukraine");
        addQuestion(q2);
    }

    public void addQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question.getQuestion());
        values.put(COLUMN_ANSWER, question.getAnswer());
        values.put(COLUMN_OPTION_A, question.getOptionA());
        values.put(COLUMN_OPTION_B, question.getOptionB());
        values.put(COLUMN_OPTION_C, question.getOptionC());
        values.put(COLUMN_OPTION_D, question.getOptionD());

        database.insert(TABLE_QUESTIONS, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(0));
                question.setQuestion(cursor.getString(1));
                question.setAnswer(cursor.getString(2));
                question.setOptionA(cursor.getString(3));
                question.setOptionB(cursor.getString(4));
                question.setOptionC(cursor.getString(5));
                question.setOptionD(cursor.getString(6));
                questionList.add(question);
            } while (cursor.moveToNext());
        }

        return questionList;
    }

    public int rowCount() {
        String selectQuery = "SELECT * FROM " + TABLE_QUESTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.getCount();
    }
}
