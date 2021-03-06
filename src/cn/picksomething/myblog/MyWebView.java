package cn.picksomething.myblog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends Activity {

	private WebView myWebView = null;
	private Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.webview_layout);
		myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setWebViewClient(new MyWebViewClient());
		myWebView.setWebChromeClient(new MyWebChromeClient());
		intent = getIntent();
		String url = intent.getStringExtra("url");
		Log.d("caobin", url);
		myWebView.loadUrl(url);
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (Uri.parse(url).getHost().equals("www.picksomething.cn")) {
				// This is my web site, so do not override; let my WebView load
				// the page
				return false;
			}
			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
	}

	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			MyWebView.this.setProgress(newProgress * 100);
			Log.d("caobin", "ProgressChanged, new Progress = " + newProgress);
		}
		
		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			MyWebView.this.setTitle(title);
		}
	}

}
