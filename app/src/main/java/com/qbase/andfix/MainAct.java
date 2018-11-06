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
                setText();
            }
        });
        findViewById(R.id.main_andfix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAndFix();
            }
        });
    }

    private void setText() {
        Test test = new Test();
//        String text = "有bug了--已经修复了";
//        mTvResult.setText(text);
        mTvResult.setText(" Result : " + test.getCalculateResult());
    }

    /**
     * 修复
     */
    private void onAndFix() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "andfix.apatch");
            if (file.exists()) {
                Log.e("--", "发现 andfix.apatch");
                MyApp.getInstance().mPatchManager.addPatch(file.getAbsolutePath());
                Log.e("--", "andfix.apatch加载成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
