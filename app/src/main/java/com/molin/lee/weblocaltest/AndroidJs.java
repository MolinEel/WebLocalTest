package com.molin.lee.weblocaltest;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by LEEP_COMPUTER on 2018/9/5.
 */

public class AndroidJs {

    private static final String TAG = AndroidJs.class.getSimpleName();
    private ISetText mISetText;

    public  AndroidJs(ISetText iSetText) {
        this.mISetText = iSetText;
    }

    @JavascriptInterface
    public void setContent(String message) {
        Log.e(TAG, message);
        mISetText.setContent(message);
    }
}
