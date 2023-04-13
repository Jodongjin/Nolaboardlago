package com.example.boardgame;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bang);
        setTitle("뱅");

        Button reviewBtn = (Button)findViewById(R.id.reviewBtn);
        Button methodBtn = (Button)findViewById(R.id.methodBtn);
        Button diaryBtn = (Button)findViewById(R.id.diaryBtn);

        // 개요
        TextView summary = (TextView)findViewById(R.id.summary);
        summary.setText("<뱅!>은 팀을 나눠서 진행하고, 팀의 승리 조건을 만족하면 생존 여부에 상관없이 그 팀 전체가 승리하는 게임이다. 이 게임의 팀은 배신자와 무법자를 무찌르고 서부 마을의 평화를 지키려는 보안관과 부관 팀, 보안관을 제거하고 마을을 차지하려는 무법자 팀, 양쪽의 눈치를 보다가 최후의 승자가 되려는 배신자로 나뉜다.");

        // 리뷰 버튼
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BangReviewActivity.class);
                startActivity(intent);
            }
        });

        // 개입방법 버튼
        methodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=BfiEWxg5vB8"));
                startActivity(intent);
            }
        });

        // 다이어리 버튼
        diaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), diaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
