package com.example.gacmy.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gac.widiget.progressbar.GacProgressBar;
import com.gac.widiget.progressbar.ProgressBarConfig;

public class MainActivity extends AppCompatActivity {
    GacProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
         progressBar = (GacProgressBar)findViewById(R.id.progress);
         ProgressBarConfig.Builder builder = new ProgressBarConfig.Builder();
         builder.setBackgroudColor(R.color.colorPrimaryDark)
                 .setRoundRect(true)
        .setForegroundColor(R.color.colorAccent)
        .setPercent(0.8f).setTextColor(R.color.white).setTextSize(16).setAnimation(true);
         progressBar.setProgressBarConfig(builder.build());

    }
}
