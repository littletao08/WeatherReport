package com.example.qingwen_li.weatherreport.Activity.Utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qingwen_li.weatherreport.Activity.Activity.MainActivity;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Qingwen_li on 2016/1/29.
 */

public class weatherUtil {
//    private static String api="http://api.map.baidu.com/telematics/v3/weather?";
//    private static String output="json";
    static String httpArg = "cityname=%E6%9C%9D%E9%98%B3";
    static String ak="18e97d8e339a7cedcc5591f27232ac6b";
    static String httpUrl = "http://apis.baidu.com/apistore/weatherservice/citylist";
    public static String result;
/*
    public static String getSend(String str){
        try {
            //用规定字符进行输出
            str= URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //拼接url
        StringBuffer buf=new StringBuffer();
        buf.append("location=");
        buf.append(str);
        buf.append("&output=");
        buf.append(output);
        buf.append("&ak=");
        buf.append(ak);
        String param=buf.toString();
        String realName=api+param;
        return realName;
    }
*/
    /*用于得到最后结果*/
    public static String getResult(String city){
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" +httpArg;



            try {
                URL url = new URL(httpUrl);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("GET");
                // 填入apikey到HTTP header
                connection.setRequestProperty("apikey",  "18e97d8e339a7cedcc5591f27232ac6b");
                Log.e("数据连接", "读取数据1");
                connection.connect();
                Log.e("数据连接", "读取数据2");
                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                Log.e("数据连接", "读取数据3");
                String strRead = null;
                // System.out.println("***********1");
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                    Log.e("数据连接", "读取数据4");
                }
                reader.close();
                result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("执行内部", "连接失败");
        }
        Log.e("执行内部","读取数据4");
        return result;
    }
}
