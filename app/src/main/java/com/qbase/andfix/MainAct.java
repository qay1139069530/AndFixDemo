package com.qbase.andfix;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;


public class MainAct extends AppCompatActivity {

    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        mTvResult = findViewById(R.id.main_result);
        findViewById(R.id.main_calucate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test test = new Test();
                mTvResult.setText(" Result : " + test.getCalculateResult());
            }
        });
        findViewById(R.id.main_andfix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAndFix();
            }
        });
    }

    /**
     * 修复
     */
    private void onAndFix() {
        try {
            MyApp myApp = (MyApp) getApplication();
            File file = new File(Environment.getExternalStorageDirectory(),"andfix.apatch");
            if (file.exists()) {
                Log.e("--", "发现 andfix.apatch");
                myApp.mPatchManager.addPatch(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
