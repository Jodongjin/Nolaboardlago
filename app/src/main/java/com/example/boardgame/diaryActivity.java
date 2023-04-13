package com.example.boardgame;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class diaryActivity extends AppCompatActivity {

    diaryDBHelper diaryHelper;
    SQLiteDatabase sqlDB;

    DatePicker datePicker;
    EditText diaryEdit;
    Button uploadBtn;

    String diaryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary);
        setTitle("다이어리");

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        diaryEdit = (EditText)findViewById(R.id.diaryEdit);
        uploadBtn = (Button)findViewById(R.id.uploadBtn);

        diaryHelper = new diaryDBHelper(this);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        diaryDate = Integer.toString(cYear) + "_" + Integer.toString(cMonth+1) + "_" +
                Integer.toString(cDay); // 날짜를 조합하여 문자열 생성
        String str = readDiary(diaryDate); // 날짜를 대입하며 일기 불러오기
        diaryEdit.setText(str); // 불러온 일기 내용 set

        // 날짜 변경할 때마다 다이어리 불러오기
        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                diaryDate = Integer.toString(year) + "_" + Integer.toString(monthOfYear+1) + "_" +
                        Integer.toString(dayOfMonth);
                String str = readDiary(diaryDate);
                diaryEdit.setText(str);
            }
        });

        // 다이어리 업로드
       uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = diaryEdit.getText().toString();

                if(readDiary(diaryDate) == null) { // 저장된 일기가 없다면 INSERT
                    sqlDB = diaryHelper.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO diaryTBL VALUES ( '" + diaryDate + "', '" + content + "');");
                    sqlDB.close();
                    diaryEdit.setText(content);

                    if(diaryEdit.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), diaryDate + "일기 저장", Toast.LENGTH_SHORT).show();
                        uploadBtn.setText("새로저장");
                    } else {
                        Toast.makeText(getApplicationContext(), diaryDate + "일기 저장", Toast.LENGTH_SHORT).show();
                        uploadBtn.setText("수정하기");
                    }
                } else { // 저장된 일기가 있다면 UPDATE
                    sqlDB = diaryHelper.getWritableDatabase();
                    sqlDB.execSQL("UPDATE diaryTBL SET content ='" + content + "' WHERE diaryDate = '" + diaryDate + "';");
                    sqlDB.close();

                    diaryEdit.setText(content);

                    if(diaryEdit.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), diaryDate + "일기 수정", Toast.LENGTH_SHORT).show();
                        uploadBtn.setText("새로저장");
                    } else {
                        Toast.makeText(getApplicationContext(), diaryDate + "일기 수정", Toast.LENGTH_SHORT).show();
                        uploadBtn.setText("수정하기");
                    }
                }
            }
        });
    }

    // 다이어리를 불러오는 메소드
    String readDiary(String diaryDate) {
        sqlDB = diaryHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT content FROM diaryTBL WHERE diaryDate = '" + diaryDate + "';", null);

        String content = null;
        while(cursor.moveToNext()) {
            content = cursor.getString(0);
        }

        if(content != null) {
            diaryEdit.setText(content);

            if(diaryEdit.getText().toString().equals("")) {
                diaryEdit.setText("");
                diaryEdit.setHint("일기 없음");
                uploadBtn.setText("새로저장");
            } else {
                uploadBtn.setText("수정하기");
            }
        } else {
            diaryEdit.setText("");
            diaryEdit.setHint("일기 없음");
            uploadBtn.setText("새로저장");
        }

        cursor.close();
        sqlDB.close();

        return content;
    }

    // 다이어리 DB
    public class diaryDBHelper extends SQLiteOpenHelper {
                public diaryDBHelper(Context context) {
                    super(context, "diaryDB", null, 1);
                }
                @Override
                public void onCreate(SQLiteDatabase db) {
                    db.execSQL("CREATE TABLE diaryTBL (diaryDate CHAR(10) PRIMARY KEY, content VARCHAR(500));");
                }
                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    db.execSQL("DROP TABLE IF EXISTS diaryTBL");
                    onCreate(db);
        }
    }
}
