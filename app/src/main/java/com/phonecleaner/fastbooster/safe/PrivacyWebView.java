package com.phonecleaner.fastbooster.safe;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.phonecleaner.fastbooster.safe.R;

public class PrivacyWebView extends AppCompatActivity {
    private ProgressBar webProg;

    
    @Override // androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_web_view);
        WebView webView = (WebView) findViewById(R.id.webView);
        this.webProg = (ProgressBar) findViewById(R.id.webProg);
        webView.setWebViewClient(new CustomWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(true);
        webView.loadUrl(getResources().getString(R.string.privacy_url));
    }

    private class CustomWebViewClient extends WebViewClient {
        private CustomWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            PrivacyWebView.this.webProg.setVisibility(View.INVISIBLE);
        }
    }
}
