package com.example.qingwen_li.weatherreport.Activity.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qingwen_li.weatherreport.Activity.Utils.weatherUtil;
import com.example.qingwen_li.weatherreport.R;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    //用于获取所要查询的城市
    private EditText et_city;
    //用于发送请求的时候使用
    private String string_city;
    //用于访问地址
    private String realUrl;

    //用于输出结果
    private  TextView tv_show;
    //确认进行输出结果
    private Button btn_search;

    public  String result;
    static MyHandler myhandler;


    static class MyHandler extends Handler {

        WeakReference<MainActivity> mActivity;
        MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity theActivity = mActivity.get();
            if(msg.what==1233){
                theActivity.tv_show.setText(theActivity.result + "hello");
            }
        }
    }



    class MyThread extends Thread {
        public void run() {
            /*
            * 得到返回结果
            * */
            result=weatherUtil.getResult(string_city);

            myhandler.sendEmptyMessage(1233);


        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //这个可以在oncreat方法里
        myhandler=new MyHandler(this);
//这个写在调用的方法的地方


        btn_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取editview里面的内容
                string_city = et_city.getText().toString();
                MyThread myThread=new MyThread();
                myThread.start();

            }
        });

    }




    /*
    private void connectInternet() {
        Log.e("show", string_city);
        realUrl= weatherUtil.getSend(string_city);

        //网络请求

        RequestQueue mQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(realUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("real Responce", realUrl);
                        Log.e("TAG  Response", response.toString());
                        tv_show.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
*/

    private void  initView() {
        et_city=(EditText)findViewById(R.id.et_city);
        tv_show=(TextView)findViewById(R.id.tv_show);
        btn_search=(Button)findViewById(R.id.btn_search);

    }




}
