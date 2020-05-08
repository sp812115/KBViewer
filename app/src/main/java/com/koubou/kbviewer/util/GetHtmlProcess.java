package com.koubou.kbviewer.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import androidx.annotation.NonNull;

public class GetHtmlProcess {


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                mWebview.loadUrl("javascript:"+script+";");
            }
            if(msg.what==1){
                html=msg.getData().getString("html");
                document  = Jsoup.parse(html);
                isok=true;
                mWebview.loadUrl("about:blank");
            }
            if(msg.what==2){
                onResultOkDocument(document);
                onResultOkHtml(html);
            }
        }
    };

    boolean isok=false;
    WebView mWebview=null;
    String script="window.HTMLOUT.processHTML(document.getElementsByTagName('html')[0].innerHTML)";
    String url="";
    String html="";
    Document document=null;

    public GetHtmlProcess(WebView mWebview, String url) {
        this.mWebview = mWebview;
        this.url=url;
    }

    public void  startProcess(){
        isok=false;
        WebSettings settings = mWebview.getSettings();
        //开启javascript
        settings.setJavaScriptEnabled(true);
        //设置useragent 360浏览器
        settings.setUserAgentString("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)");

        mWebview.addJavascriptInterface(new JavaScriptInterface(), "HTMLOUT");
        mWebview.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageFinished(WebView view, String url) {
                if(isok){
                    Message message=new Message();
                    message.what=2;
                    mHandler.sendMessage(message);
                    return;
                }


                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);


            }
        });

        // 开始加载网址
        mWebview.loadUrl(url);
    }

    public void onResultOkDocument(Document doc){

    }

    public void onResultOkHtml(String html){

    }

    private  class JavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html)
        {
            Message message=new Message();
            message.what=1;
            Bundle bundle=new Bundle();
            bundle.putString("html",html);
            message.setData(bundle);
            mHandler.sendMessage(message);
        }
    }
}

