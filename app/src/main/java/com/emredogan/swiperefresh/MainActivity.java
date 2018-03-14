package com.emredogan.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView;
    WebViewClient webViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_id);
        webView = (WebView) findViewById(R.id.webview_id);
        webViewClient = new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("Finished");
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);

                System.out.println("PageVisible");

                swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorFinishRefresh));


            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

                swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorLoadRefresh));

                System.out.println("onLoad");
            }
        };

        webView.loadUrl("http://google.com");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

        webView.setWebViewClient(webViewClient);
    }
}
