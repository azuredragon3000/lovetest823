package com.myapp.lovetest_azuredragon3000.quiz.object;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class QuizDB extends SQLiteOpenHelper  {
    private static final int MAX_OPTION = 6;
    private SQLiteDatabase db;
    Context context;
    public QuizDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        /* will be inherited */

        create_column(db);
        fill_category();
        ArrayList<String[]> list_question = create_question();

        /* LIST QUESTION SHOULD BE
        *
        * QUESTION~A~B~C~D~ANS~DIFF
        *
        * */
        /* fixed */
        fillQuestionsTable(list_question);
    }
    public void create_column(SQLiteDatabase db) {

        final String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + " ( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT "+
                ")";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + "(" +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, "+

                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, "+
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, "+
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, "+
                QuizContract.QuestionTable.COLUMN_OPTION4 + " TEXT, "+
                QuizContract.QuestionTable.COLUMN_OPTION5 + " TEXT, "+
                QuizContract.QuestionTable.COLUMN_OPTION6 + " TEXT, "+

                QuizContract.QuestionTable.COLUMN_ANSWER_NR + " INTEGER, "+
                QuizContract.QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES "+
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" +
                "ON DELETE CASCADE"+
                ")";
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
    }

    public ArrayList<String[]> create_question() {
        ArrayList<String[]> list_question = new ArrayList<>();
        AssetManager am = context.getAssets();
        InputStream input;
        try {
            String[] files = am.list("");
            for (String file : files) {
                if (file.contains("category")) {
                    input = am.open(file);
                    String ans = processFile(file,"ans");
                    String diff = processFile(file,"diff");
                    String category = processFile(file,"category");
                    String[] textline = read_text(input);
                    String[] question = processString(textline,ans,diff,category);
                    list_question.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list_question;
    }

    protected String processFile(String file, String check){
        if(check.equals("diff")){
           return file.split("_")[3];
        }else if(check.equals("category")){
            return file.split("_")[4];
        }else if(check.equals("ans")){
            return file.split("_")[2];
        }else{
            return "";
        }
    }


    public abstract void fill_category();
    public abstract String[] processString(String[] textline, String ans, String diff, String category);

    private String[] read_text(InputStream input) throws IOException {
        int size = input.available();
        byte[] buffer = new byte[size];
        input.read(buffer);
        input.close();
        String text = new String(buffer);
        return text.split("\\r?\\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ QuizContract.QuestionTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ QuizContract.CategoriesTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(ArrayList<String[]> list_question) {
        for(int i=0;i<list_question.size();i++){
            for (String arr_question : list_question.get(i)) {
                int index = arr_question.split("~").length;
                if(index>=4){
                    ArrayList<String> list_option = new ArrayList<>();
                    int num_option = index - 4;
                    String quest = arr_question.split("~")[0];
                    int answ = Integer.parseInt(arr_question.split("~")[index-3]);
                    String diff = arr_question.split("~")[index-2];
                    int category = (i+1);
                    for(int j=0;j<num_option;j++){
                        list_option.add(arr_question.split("~")[j+1]);
                    }
                    Question q = new Question(quest,list_option,answ,diff,category);
                    addQuestion(q);
                }else{
                    return;
                }
            }
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME,null,cv);
    }

    public void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        for(int i=0;i<question.list_option.size();i++){
            cv.put("option"+(i+1), question.list_option.get(i));
        }
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionTable.COLUMN_DIFFICULTY,question.getDifficulty());
        cv.put(QuizContract.QuestionTable.COLUMN_CATEGORY_ID,question.getCategoryID());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }


    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT*FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            }while(c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty){
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " =? " +
                " AND " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " =? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID),difficulty};
        Cursor c = db.query(
                QuizContract.QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable._ID)));
                question.setQuestion((c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION))));
                /* GET OPTION */
                ArrayList<String> option = new ArrayList<String>();
                for(int i=0;i<MAX_OPTION;i++){
                    if(!(c.getString(c.getColumnIndex("option"+(i+1))) == null)){
                        option.add(c.getString(c.getColumnIndex("option"+(i+1))));
                    }
                }
                question.setList_option(option);
                /* ---------- */
                question.setAnswerNr((c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR))));
                question.setDifficulty((c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY))));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}

class QuizContract
{
    private QuizContract(){}

    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_NAME = "name";
    }
    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_question";
        public static final String COLUMN_QUESTION = "question";

        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_CATEGORY_ID = "category_id";

        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_OPTION5 = "option5";
        public static final String COLUMN_OPTION6 = "option6";
    }
}
