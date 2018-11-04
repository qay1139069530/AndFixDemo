package com.qbase.andfix;

import android.app.Application;
import android.os.Environment;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;

public class MyApp extends Application {
    public PatchManager mPatchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        mPatchManager = new PatchManager(this);
        try {
            //appversion变更会将所有补丁被删除,如果appversion没变，则会加载已经保存的所有补丁
            String appversion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mPatchManager.init(appversion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPatchManager.loadPatch();
    }
}
