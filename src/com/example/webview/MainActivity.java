package com.example.webview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.transition.Visibility;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    
	private WebView webView;
	private ProgressBar pg1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		
		
		
		
		Button Btn0 = (Button)findViewById(R.id.button0);//获取按钮资源    
        Btn0.setOnClickListener(new Button.OnClickListener(){//创建监听    
            public void onClick(View v) {    
            	EditText textView = (EditText)findViewById(R.id.textview0);
        		String val = textView.getText().toString();
        		if(val.equals("")){
        			val = "http://www.baidu.com";
        		}
        		
                webView.loadUrl(val);
                
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
            }    
  
        });    
        
        String homepage = "http://www.baidu.com";
        webView.loadUrl(homepage);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
		
	}

	private void init() {
		// TODO 自动生成的方法存根
		webView=(WebView) findViewById(R.id.webview1);
		pg1=(ProgressBar) findViewById(R.id.progressBar1);
		
		webView.setWebViewClient(new WebViewClient(){
			//覆写shouldOverrideUrlLoading实现内部显示网页
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO 自动生成的方法存根
			    view.loadUrl(url);
				return true;
			}
		});
		WebSettings seting=webView.getSettings();
		seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO 自动生成的方法存根
				
				if(newProgress==100){
					pg1.setVisibility(View.GONE);//加载完网页进度条消失
				}
				else{
					pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
					pg1.setProgress(newProgress);//设置进度值
				}
				
			}
		});
		
	}

	
    //设置返回键动作（防止按返回键直接退出程序)
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自动生成的方法存根
		if(keyCode==KeyEvent.KEYCODE_BACK) {
			if(webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
				webView.goBack();
				return true;
			}
			else {//当webview处于第一页面时,直接退出程序
				System.exit(0);
			}
			
		
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	



}
