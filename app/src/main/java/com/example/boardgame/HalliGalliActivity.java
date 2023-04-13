package com.example.boardgame;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HalliGalliActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halligalli);
        setTitle("할리갈리");

        Button reviewBtn = (Button)findViewById(R.id.reviewBtn);
        Button methodBtn = (Button)findViewById(R.id.methodBtn);
        Button diaryBtn = (Button)findViewById(R.id.diaryBtn);

        TextView summary = (TextView)findViewById(R.id.summary);
        summary.setText("<할리갈리>는 '같은 과일이 다섯 개가 보이면 종을 쳐라'라는 한 마디로 압축되는 간단한 규칙, 속도 경쟁에서 승리하기 위해 극도의 집중력을 요구하는 환경, 재미를 극대화하는 소도구로써 '종'을 사용하는 것 등 간단하지만 흥미로운 요소들이 결합되어 많은 사람에게 강한 인상을 주었다.");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HalliGalliReviewActivity.class);
                startActivity(intent);
            }
        });

        methodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KIJvoeJ1C_Y"));
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
