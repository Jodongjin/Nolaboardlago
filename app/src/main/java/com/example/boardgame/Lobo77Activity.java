package com.example.boardgame;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lobo77Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobo77);
        setTitle("로보77");

        Button reviewBtn = (Button)findViewById(R.id.reviewBtn);
        Button methodBtn = (Button)findViewById(R.id.methodBtn);
        Button diaryBtn = (Button)findViewById(R.id.diaryBtn);

        TextView summary = (TextView)findViewById(R.id.summary);
        summary.setText("<로보77>은 차례대로 카드를 내며 매턴을 살아남는 것이 목적인 게임이다. 카드를 냈을 때 놓여있는 카드의 합이 77 이상이거나 11의 배수이면 해당 플레이어가 지는 게임이며 단순하지만 온전히 운에 의한 게임이기 때문에 긴장감을 놓칠 수 없는 게임이다.");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lobo77ReviewActivity.class);
                startActivity(intent);
            }
        });

        methodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Hcq3aFwpm5E"));
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
