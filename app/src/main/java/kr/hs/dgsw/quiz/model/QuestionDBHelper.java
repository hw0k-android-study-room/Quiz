package kr.hs.dgsw.quiz.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuestionDBHelper extends SQLiteOpenHelper {

    private static final String TABLE = "questions";
    private List<Question> questions;

    public QuestionDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        questions = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Questions(" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  question TEXT," +
                "  score INTEGER," +
                "  answer INTEGER," +
                "  type INTEGER," +
                "  choice1 TEXT," +
                "  choice2 TEXT," +
                "  choice3 TEXT," +
                "  choice4 TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE Questions";
        db.execSQL(sql);
        onCreate(db);
    }

    private Question getQuestionFromCursor(Cursor cursor) {
        Question question = new Question();
        question.setId(cursor.getInt(cursor.getColumnIndex("id")));
        question.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
        question.setScore(cursor.getInt(cursor.getColumnIndex("score")));
        question.setAnswer(cursor.getInt(cursor.getColumnIndex("answer")));
        String[] choices = new String[4];
        for (int i = 1; i <= 4; i++) {
            choices[i - 1] = cursor.getString(cursor.getColumnIndex("choice" + i));
        }
        question.setChoices(choices);
        question.setType(cursor.getInt(cursor.getColumnIndex("type")));

        return question;
    }

    public long insert(Question question) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question.getQuestion());
        values.put("score", question.getScore());
        values.put("answer", question.getAnswer());
        values.put("type", question.getType());

        for (int i = 0; i < question.getChoices().length; i++) {
            int idx = i + 1;
            values.put("choice" + idx, question.getChoices()[i]);
        }

        return db.insert(TABLE, null, values);
    }

    public List<Question> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[] { "*" }, null, null, null, null, null);

        questions.clear();

        while (cursor.moveToNext()) {
            Question question = getQuestionFromCursor(cursor);
            questions.add(question);
        }

        return questions;
    }

    public Question get(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[] { "*" }, "id = ?", new String[] { String.valueOf(id) }, null, null, null);

        if (cursor.moveToNext()) {
            return getQuestionFromCursor(cursor);
        }
        else {
            return null;
        }
    }

    public long update(Question question) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("question", question.getQuestion());
        values.put("score", question.getScore());
        values.put("answer", question.getAnswer());
        values.put("type", question.getType());

        for (int i = 0; i < question.getChoices().length; i++) {
            int idx = i + 1;
            values.put("choice" + idx, question.getChoices()[i]);
        }

        return db.update(TABLE, values, "id = ?", new String[] { String.valueOf(question.getId()) });
    }

    public long delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE, "id = ?", new String[] { String.valueOf(id) });
    }
}
