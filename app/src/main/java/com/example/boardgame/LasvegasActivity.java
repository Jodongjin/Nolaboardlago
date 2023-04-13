package com.example.boardgame;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LasvegasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lasvegas);
        setTitle("라스베가스");

        Button reviewBtn = (Button)findViewById(R.id.reviewBtn);
        Button methodBtn = (Button)findViewById(R.id.methodBtn);
        Button diaryBtn = (Button)findViewById(R.id.diaryBtn);

        TextView summary = (TextView)findViewById(R.id.summary);
        summary.setText("<라스베가스>는 여러 개의 주사위를 게임머니 위에 올려가며 배팅을하고 모든 플레이어의 주사위가 다 떨어졌을 때 게임머니 위에 가장 많이 놓인 주사위의 주인이 게임머니를 다 가져가는 게임이다. 최종 게임머니가 가장 많은 플레이어가 이긴다. 플레이어간에 눈치를 살피며 전략적인 투자가 요구되는 두뇌 게임이다.");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LasvesgasReviewActivity.class);
                startActivity(intent);
            }
        });

        methodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Bt2escpWj8Q"));
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
