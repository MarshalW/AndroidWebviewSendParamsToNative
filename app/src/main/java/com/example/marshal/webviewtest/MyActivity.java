package com.example.marshal.webviewtest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;


public class MyActivity extends Activity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        this.webview = (WebView)findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        webview.setWebViewClient(new MyCustomWebViewClient());
        webview.loadUrl("http://10.0.2.2:3000/test.html");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("webviewTest",">>>>>>>>>>>>>"+url);
            Uri uri= Uri.parse(url);
            if (uri.getScheme().equals("local")){
                List<String> values=uri.getQueryParameters("url");
                for (String v:values){
                    Log.d("webvewTest",">>>>>>>>>>>>>"+v);
                }
                return true;
            }

            view.loadUrl(url);
            return true;
        }
    }
}
