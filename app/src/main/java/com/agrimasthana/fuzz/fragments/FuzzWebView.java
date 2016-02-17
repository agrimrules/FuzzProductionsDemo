package com.agrimasthana.fuzz.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.agrimasthana.fuzz.R;

/**
 * Created by agrimasthana on 2/14/16.
 */
public class FuzzWebView extends Activity {
    private WebView webView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        String url = super.getIntent().getExtras().getString("urlString");
        webView.loadUrl(url);
    }
}
