package com.example.qingwen_li.weatherreport.Activity.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qingwen_li.weatherreport.Activity.Adapters.Adapter_CityWeather;
import com.example.qingwen_li.weatherreport.Activity.Bean.CityWeather;
import com.example.qingwen_li.weatherreport.Activity.Utils.weatherUtil;
import com.example.qingwen_li.weatherreport.R;
import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //用于获取所要查询的城市
    private EditText et_city;
    //用于发送请求的时候使用
    private String string_city;
    //用于输出结果
    private  TextView tv_show;
    //确认进行输出结果
    private Button btn_search;
    public  String result;
    static MyHandler myhandler;

    SwipeRefreshLayout swipe;
    RecyclerView myRecycleview;

    private List<CityWeather> mDatas;
    private Adapter_CityWeather mAdapter;


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
            string_city = et_city.getText().toString();
            result=weatherUtil.getResult(string_city);
            myhandler.sendEmptyMessage(1233);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        //这个可以在oncreat方法里
        myhandler=new MyHandler(this);
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
* 初始化数据
* */
    private void initData() {
        CityWeather cw= weatherUtil.cityToJson(string_city);
        mDatas.add(cw);
    }

    private void  initView() {
        et_city=(EditText)findViewById(R.id.et_city);
        tv_show=(TextView)findViewById(R.id.tv_show);
        btn_search=(Button)findViewById(R.id.btn_search);
        myRecycleview= (RecyclerView) findViewById(R.id.recycler_view);
        myRecycleview.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流

        swipe= (SwipeRefreshLayout) findViewById(R.id.swipe);

        mAdapter=new Adapter_CityWeather(this,mDatas);

        /*
        * 保持刷新
        * */
        swipe.setColorSchemeResources(R.color.colorPrimary);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                Log.e("Swipe", "Refreshing Number");
            }
        });
        myRecycleview.setAdapter(mAdapter);
    }

}
