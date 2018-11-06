package com.qbase.andfix;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;

public class MyApp extends Application {


    public static volatile MyApp Instance;


    public synchronized static MyApp getInstance() {
        if (Instance == null) {
            synchronized (MyApp.class) {
                if (Instance == null) {
                    Instance = new MyApp();
                }
            }
        }
        return Instance;
    }

    public PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        mPatchManager = new PatchManager(this);
        try {
            //appversion变更会将所有补丁被删除,如果appversion没变，则会加载已经保存的所有补丁
            String appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mPatchManager.init(appversion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPatchManager.loadPatch();

//
//        try {
//            // .apatch file path ，这里一定要注意每台手机sd卡路径不同
//            String patchFileString = Environment.getExternalStorageDirectory().getAbsolutePath() + "/andfix.apatch";
//            //3）添加patch
//            mPatchManager.addPatch(patchFileString);
//
//            Log.e("-MyApp-", "andfix.apatch加载成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
