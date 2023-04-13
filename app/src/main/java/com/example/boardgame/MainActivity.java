package com.example.boardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqlDB;

    // 게임별 별점을 나타내기 위한 DB
    rummikubDBHelper rummikubHelper = new rummikubDBHelper(this);
    bangDBHelper bangHelper = new bangDBHelper(this);
    clueDBHelper clueHelper = new clueDBHelper(this);
    davincicodeDBHelper davincicodeHelper = new davincicodeDBHelper(this);
    halligalliDBHelper halligalliHelper = new halligalliDBHelper(this);
    indianpokerDBHelper indianpokerHelper = new indianpokerDBHelper(this);
    lasvegasDBHelper lasvegasHelper = new lasvegasDBHelper(this);
    lobo77DBHelper lobo77Helper = new lobo77DBHelper(this);
    splendorDBHelper splendorHelper = new splendorDBHelper(this);
    ubongoDBHelper ubongoHelper = new ubongoDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("놀아 보드라고");

        // 게임별 별점을 나타내는 레이팅
        RatingBar rummikubRating = (RatingBar)findViewById(R.id.rummikubRating);
        RatingBar bangRating = (RatingBar)findViewById(R.id.bangRating);
        RatingBar clueRating = (RatingBar)findViewById(R.id.clueRating);
        RatingBar davincicodeRating = (RatingBar)findViewById(R.id.davincicodeRating);
        RatingBar halligalliRating = (RatingBar)findViewById(R.id.halligalliRating);
        RatingBar indianpokerRating = (RatingBar)findViewById(R.id.indianpokerRating);
        RatingBar lasvegasRating = (RatingBar)findViewById(R.id.lasvegasRating);
        RatingBar lobo77Rating = (RatingBar)findViewById(R.id.lobo77Rating);
        RatingBar splendorRating = (RatingBar)findViewById(R.id.splendorRating);
        RatingBar ubongoRating = (RatingBar)findViewById(R.id.ubongoRating);


        // 게임별 레이팅바 setting
        float ratingSum = 0; // 평균별점을 구하기 위한 전체 별점 합
        int num = 0; // 평균별점을 구하기 위한 리뷰 수
        float average = 0;
        Cursor cursor;

        sqlDB = rummikubHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM rummikubTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1); // 데이터베이스의 두 번째 열에서 별점들을 불러와서 더함
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num; // 별점의 평균
        rummikubRating.setRating(average);
        ratingSum = 0; // 별점의 합과 count 초기화
        num = 0;

        sqlDB = bangHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM bangTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        bangRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = clueHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM clueTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        clueRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = davincicodeHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM davincicodeTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        davincicodeRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = halligalliHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM halligalliTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        halligalliRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = indianpokerHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM indianpokerTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        indianpokerRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = lasvegasHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM lasvegasTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        lasvegasRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = lobo77Helper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM lobo77TBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        lobo77Rating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = splendorHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM splendorTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        splendorRating.setRating(average);
        ratingSum = 0;
        num = 0;

        sqlDB = ubongoHelper.getReadableDatabase();
        cursor = sqlDB.rawQuery("SELECT * FROM ubongoTBL;", null);
        while(cursor.moveToNext()) {
            ratingSum += cursor.getFloat(1);
            num ++;
        }
        cursor.close();
        sqlDB.close();
        average = ratingSum / num;
        ubongoRating.setRating(average);


        // 게임별 버튼의 OnClickListner
        Button rummikubBtn = (Button) findViewById(R.id.rummikubBtn);
        rummikubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rummikubActivity.class);
                startActivity(intent);
            }
        });

        Button davincicodeBtn = (Button)findViewById(R.id.davincicodeBtn);
        davincicodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), davincicodeActivity.class);
                startActivity(intent);
            }
        });

        Button bangBtn = (Button)findViewById(R.id.bangBtn);
        bangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BangActivity.class);
                startActivity(intent);
            }
        });

        Button clueBtn = (Button)findViewById(R.id.clueBtn);
        clueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClueActivity.class);
                startActivity(intent);
            }
        });

        Button halligalliBtn = (Button)findViewById(R.id.halligalliBtn);
        halligalliBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HalliGalliActivity.class);
                startActivity(intent);
            }
        });

        Button indianpokerBtn = (Button)findViewById(R.id.indianpokerBtn);
        indianpokerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IndianpokerActivity.class);
                startActivity(intent);
            }
        });

        Button lasvegasBtn = (Button)findViewById(R.id.lasvegasBtn);
        lasvegasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LasvegasActivity.class);
                startActivity(intent);
            }
        });

        Button lobo77Btn = (Button)findViewById(R.id.lobo77Btn);
        lobo77Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lobo77Activity.class);
                startActivity(intent);
            }
        });

        Button splendorBtn = (Button)findViewById(R.id.splendorBtn);
        splendorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SplendorActivity.class);
                startActivity(intent);
            }
        });

        Button ubongoBtn = (Button)findViewById(R.id.ubongoBtn);
        ubongoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UbongoActivity.class);
                startActivity(intent);
            }
        });
    }

    // 게임별 DB
    public class rummikubDBHelper extends SQLiteOpenHelper {
        public rummikubDBHelper(Context context) {
            super(context, "rummikubDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE rummikubTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS rummikubTBL");
            onCreate(db);
        }
    }

    public class bangDBHelper extends SQLiteOpenHelper {
        public bangDBHelper(Context context) {
            super(context, "bangDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE bangTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS bangTBL");
            onCreate(db);
        }
    }

    public class clueDBHelper extends SQLiteOpenHelper {
        public clueDBHelper(Context context) {
            super(context, "clueDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE clueTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS clueTBL");
            onCreate(db);
        }
    }

    public class davincicodeDBHelper extends SQLiteOpenHelper {
        public davincicodeDBHelper(Context context) {
            super(context, "davincicodeDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE davincicodeTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS davincicodeTBL");
            onCreate(db);
        }
    }

    public class halligalliDBHelper extends SQLiteOpenHelper {
        public halligalliDBHelper(Context context) {
            super(context, "halligalliDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE halligalliTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS halligalliTBL");
            onCreate(db);
        }
    }

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

    public class lasvegasDBHelper extends SQLiteOpenHelper {
        public lasvegasDBHelper(Context context) {
            super(context, "lasvegasDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE lasvegasTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS lasvegasTBL");
            onCreate(db);
        }
    }

    public class lobo77DBHelper extends SQLiteOpenHelper {
        public lobo77DBHelper(Context context) {
            super(context, "lobo77DB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE lobo77TBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS lobo77TBL");
            onCreate(db);
        }
    }

    public class splendorDBHelper extends SQLiteOpenHelper {
        public splendorDBHelper(Context context) {
            super(context, "splendorDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE splendorTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS splendorTBL");
            onCreate(db);
        }
    }

    public class ubongoDBHelper extends SQLiteOpenHelper {
        public ubongoDBHelper(Context context) {
            super(context, "ubongoDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE ubongoTBL (comment VARCHAR(100), rating FROAT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS ubongoTBL");
            onCreate(db);
        }
    }
}