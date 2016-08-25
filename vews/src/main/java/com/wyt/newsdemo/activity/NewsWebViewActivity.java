package com.wyt.newsdemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.wyt.newsdemo.R;

public class NewsWebViewActivity extends AppCompatActivity {

    WebView wvNews;
    String newsLink,newsSource;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_web_view);

        initVariable();
        intiView();
        loadData();
    }

    public void initVariable(){
        newsLink=getIntent().getStringExtra("newsLink");
        newsSource=getIntent().getStringExtra("newsSource");
    }

    public void intiView(){
        if (!TextUtils.isEmpty(newsSource)){
            setTitle(newsSource);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar=(ProgressBar)findViewById(R.id.pbBar);
        wvNews=(WebView)findViewById(R.id.wvNews);
        wvNews.getSettings().setSupportZoom(true);
        wvNews.getSettings().setBuiltInZoomControls(true);
        wvNews.getSettings().setJavaScriptEnabled(true);
        wvNews.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wvNews.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvNews.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress==100){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void loadData(){
        if (!TextUtils.isEmpty(newsLink)){
            wvNews.loadUrl(newsLink);
        }
    }

    @Override
    public void onBackPressed() {

        if (wvNews.canGoBack()){
            wvNews.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
