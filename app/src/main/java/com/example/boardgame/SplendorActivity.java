package com.example.boardgame;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplendorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splendor);
        setTitle("스플렌더");

        Button reviewBtn = (Button)findViewById(R.id.reviewBtn);
        Button methodBtn = (Button)findViewById(R.id.methodBtn);
        Button diaryBtn = (Button)findViewById(R.id.diaryBtn);

        TextView summary = (TextView)findViewById(R.id.summary);
        summary.setText("<스플렌더>는 '마니아나 대중이나 보편적으로 좋아하는 게임'이라는 독점적인 지위를 창출해냈다. 자기 차례에 토큰을 가져오거나 토큰으로 카드를 사면 된다는 간단한 규칙과, 가볍지만 치열한 게임 분위기, 여유가 있다면 점심시간에도 한판 할 수 있는 30분 정도의 게임 시간이, 이 게임이 세상에 빠르게 퍼지게 만든 이유였다.");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SplendorReviewActivity.class);
                startActivity(intent);
            }
        });

        methodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=78Ga9vT3eIE"));
                startActivity(intent);
            }
        });

        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), diaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
