package com.molin.lee.weblocaltest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.molin.lee.R;


/**
 * Created by LEEP_COMPUTER on 2018/9/5.
 */

public class WebAndLocalCorrespondActivity extends Activity implements ISetText {

    private static final String TAG = WebAndLocalCorrespondActivity.class.getSimpleName();

    private WebView mWebView;
    private TextView mTvFromWeb;
    private EditText mEt;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wlcorrespond);
        initView();
    }

    private void initView() {
        mTvFromWeb = findViewById(R.id.tvfromweb);
        mEt = findViewById(R.id.ed_toweb);
        mWebView = findViewById(R.id.web);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/android.html");
        mWebView.addJavascriptInterface(new AndroidJs(this), "androidjs");
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e(TAG, message);
                mTvFromWeb.setText(message);
                result.cancel();
                return true;
            }
        });
    }

    public void towebtext(View v) {
        Log.e(TAG, "发送数据到网页");
        Toast.makeText(this, "发送数据到网页", Toast.LENGTH_SHORT).show();
        String s = mEt.getText().toString();
//        mWebView.loadUrl("javascript:remote('"+s+"')");
        mWebView.evaluateJavascript("javascript:remote('" + s + "')", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.e(TAG,"callback--"+value);
            }
        });
    }

    @Override
    public void setContent(final String msg) {
        Log.e(TAG, "接收到网页中的数据" + msg);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTvFromWeb.setText(msg);
            }
        });
    }
}
