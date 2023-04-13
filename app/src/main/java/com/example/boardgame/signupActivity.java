package com.example.boardgame;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signupActivity extends AppCompatActivity {

    signupDBHelper DBHelper = new signupDBHelper(this);
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setTitle("회원가입");

        EditText id = (EditText)findViewById(R.id.id);
        EditText password = (EditText)findViewById(R.id.password);
        EditText passwordCheck = (EditText)findViewById(R.id.passwordCheck);
        EditText name = (EditText)findViewById(R.id.name);

        Button completionBtn = (Button)findViewById(R.id.completionBtn);
        completionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean already = false; // id 중복 확인
                String checkId;

                // 필수입력항목(아이디, 비밀번호, 비밀번호 확인, 이름)이 비었을 때
                if(id.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "필수입력 항목이 비어있습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "필수입력 항목이 비어있습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(passwordCheck.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "필수입력 항목이 비어있습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "필수입력 항목이 비어있습니다.", Toast.LENGTH_SHORT).show();
                } else if(!password.getText().toString().equals(passwordCheck.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "password가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    sqlDB = DBHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqlDB.rawQuery("SELECT * FROM idTBL;", null);

                    while(cursor.moveToNext() && already == false) {
                        checkId = cursor.getString(0);
                        if(checkId.equals(id.getText().toString())) {
                            already = true;
                        }
                    }
                    cursor.close();
                    sqlDB.close();

                    if(already == true) {
                        Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                    } else { // 모든 조건 충족 시, ID Password DB에 업로드하고 액티비티 finish
                        sqlDB = DBHelper.getWritableDatabase();
                        sqlDB.execSQL("INSERT INTO idTBL VALUES ('" + id.getText().toString() + "', '" + password.getText().toString() + "');");
                        sqlDB.close();

                        Intent intent = new Intent(getApplicationContext(), rummikubReviewActivity.class);
                        intent.putExtra("loginState", 1);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        Button cancelBtn = (Button)findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
