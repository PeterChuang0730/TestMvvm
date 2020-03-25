package com.example.mvvm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm.databinding.ActivityWebviewBinding;

public class WebActivity extends AppCompatActivity {
    ActivityWebviewBinding activityWebviewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        activityWebviewBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_webview);

        Intent intent = getIntent();
        String userHtml = intent.getStringExtra("html_url");

        openJavaScript();

        activityWebviewBinding.webView.loadUrl(userHtml);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openJavaScript() {
        WebSettings settings = activityWebviewBinding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (activityWebviewBinding.webView.canGoBack()) {
                activityWebviewBinding.webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
