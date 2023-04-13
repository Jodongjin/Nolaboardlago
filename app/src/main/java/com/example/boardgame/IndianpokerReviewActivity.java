package com.example.boardgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IndianpokerReviewActivity extends AppCompatActivity {

    Integer loginState = 0; // 로그인 상태: 0이면 비로그인, 1이면 로그인

    // DataBase
    indianpokerDBHelper DBHelper = new indianpokerDBHelper(this);
    signupDBHelper DBHelper2 = new signupDBHelper(this);
    SQLiteDatabase sqlDB;

    // 로그인 액티비티에서 로그인 완료하면 loginState에 1을 받아와 대입 -> 로그인 완료, 리뷰작성 가능
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            loginState = data.getIntExtra("loginState", 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indianpokerreview);
        setTitle("게임리뷰");

        EditText inputId = (EditText)findViewById(R.id.inputId);
        EditText inputPassword = (EditText)findViewById(R.id.inputPassword);

        RatingBar averageRatingBar = (RatingBar)findViewById(R.id.averageRatingBar); // 평균 별점
        averageRatingBar.setEnabled(false);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar); // 리뷰로 입력하는 별점
        EditText comment = (EditText)findViewById(R.id.comment); // 리뷰 코멘트

        ListView listView = (ListView)findViewById(R.id.listView); // 리스트
        final ArrayList<String> reviewList = new ArrayList<String>(); // 리스트뷰에 동적으로 추가할 문자열 배열
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, reviewList);
        listView.setAdapter(adapter);

        // DataBase에서 리스트뷰에 보일 문자열을 불러와서 문자열 배열에 추가
        sqlDB = DBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM indianpokerTBL;", null);

        String commentRead;
        float ratingSum = 0; // 평균별점을 구하기 위한 전체 별점 합
        int num = 0; // 평균별점을 구하기 위한 리뷰 수

        while(cursor.moveToNext()) {
            commentRead = cursor.getString(0); // 데이터베이스의 첫 번째 열에서 코멘트를 불러와서
            reviewList.add(commentRead); // 문자열 배열에 추가
            // 동적으로 리스트뷰에 출력을 반복

            ratingSum += cursor.getFloat(1); // 데이터베이스의 두 번째 열에서 별점들을 불러와서 더함
            num ++;
        }
        cursor.close();
        sqlDB.close();

        adapter.notifyDataSetChanged();

        float average = ratingSum / num; // 별점의 평균을 구해서
        averageRatingBar.setRating(average); // 상단 평균별점을 나타내는 레이팅바에 set

        Button writeReviewBtn = (Button)findViewById(R.id.writeReviewBtn);
        writeReviewBtn.setOnClickListener(new View.OnClickListener() { // 리뷰작성 버튼 클릭 시
            @Override
            public void onClick(View v) {

                if(loginState == 0) { // 비로그인 상태라면
                    View dialogView = (View) View.inflate(IndianpokerReviewActivity.this, R.layout.logindialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(IndianpokerReviewActivity.this);
                    dlg.setView(dialogView);
                    dlg.setTitle("로그인");
                    dlg.setNeutralButton("회원가입", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), signupActivity.class);
                            startActivityForResult(intent,1);
                        }
                    });
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText inputId = (EditText) dialogView.findViewById(R.id.inputId);
                            EditText inputPassword = (EditText) dialogView.findViewById(R.id.inputPassword);
                            boolean idCheck = false;
                            boolean passwordCheck = false;
                            String DBId;
                            sqlDB = DBHelper2.getReadableDatabase();

                            Cursor cursor;
                            cursor = sqlDB.rawQuery("SELECT * FROM idTBL;", null);

                            while(cursor.moveToNext()) {
                                DBId = cursor.getString(0);
                                if(DBId.equals(inputId.getText().toString())) {
                                    idCheck = true;
                                    passwordCheck = cursor.getString(1).equals(inputPassword.getText().toString());
                                }
                            }
                            if(idCheck == false) {
                                Toast.makeText(getApplicationContext(), "해당되는 ID가 없습니다.", Toast.LENGTH_SHORT).show();
                            } else if(passwordCheck == false) {
                                Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                loginState = 1;
                            }
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                } else { // 로그인 상태라면 -> 데이터베이스에 업로드하고 리스트뷰에 추가
                    if(comment.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Comment가 비어있습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        reviewList.add(comment.getText().toString()); // 문자열 배열에 추가
                        adapter.notifyDataSetChanged();

                        sqlDB = DBHelper.getWritableDatabase();
                        sqlDB.execSQL("INSERT INTO indianpokerTBL VALUES ('" + comment.getText().toString() + "', '" + ratingBar.getRating() + "');");
                        sqlDB.close();
                        Toast.makeText(getApplicationContext(), "리뷰 입력됨. 별점은 페이지 재접속 후 적용됩니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // 게임리뷰 DB
    public class indianpokerDBHelper extends SQLiteOpenHelper {
        public indianpokerDBHelper(Context context) {
            super(context, "indianpokerDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE indianpokerTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS indianpokerTBL");
            onCreate(db);
        }
    }

    // 계정 DB
    public class signupDBHelper extends SQLiteOpenHelper {
        public signupDBHelper(Context context) {
            super(context, "idDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE idTBL (id VARCHAR(20) PRIMARY KEY, password VARCHAR(20));");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS idTBL");
            onCreate(db);
        }
    }
}
