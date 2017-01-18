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
		
		
		
		
		Button Btn0 = (Button)findViewById(R.id.button0);//��ȡ��ť��Դ    
        Btn0.setOnClickListener(new Button.OnClickListener(){//��������    
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
		// TODO �Զ����ɵķ������
		webView=(WebView) findViewById(R.id.webview1);
		pg1=(ProgressBar) findViewById(R.id.progressBar1);
		
		webView.setWebViewClient(new WebViewClient(){
			//��дshouldOverrideUrlLoadingʵ���ڲ���ʾ��ҳ
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO �Զ����ɵķ������
			    view.loadUrl(url);
				return true;
			}
		});
		WebSettings seting=webView.getSettings();
		seting.setJavaScriptEnabled(true);//����webview֧��javascript�ű�
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO �Զ����ɵķ������
				
				if(newProgress==100){
					pg1.setVisibility(View.GONE);//��������ҳ��������ʧ
				}
				else{
					pg1.setVisibility(View.VISIBLE);//��ʼ������ҳʱ��ʾ������
					pg1.setProgress(newProgress);//���ý���ֵ
				}
				
			}
		});
		
	}

	
    //���÷��ؼ���������ֹ�����ؼ�ֱ���˳�����)
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO �Զ����ɵķ������
		if(keyCode==KeyEvent.KEYCODE_BACK) {
			if(webView.canGoBack()) {//��webview���Ǵ��ڵ�һҳ��ʱ��������һ��ҳ��
				webView.goBack();
				return true;
			}
			else {//��webview���ڵ�һҳ��ʱ,ֱ���˳�����
				System.exit(0);
			}
			
		
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	



}
