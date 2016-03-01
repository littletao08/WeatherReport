package com.example.qingwen_li.weatherreport.Activity.Utils;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.qingwen_li.weatherreport.Activity.Activity.MainActivity;
import com.example.qingwen_li.weatherreport.Activity.Bean.CityWeather;
import com.example.qingwen_li.weatherreport.Activity.Nets.NetUrl;

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


    public static String result;
    private static String httpUrl;

    /*
    * 用于存储城市天气的各种数据
    *
    String city;//城市
    int citycode;//编码
    String date;//日期
    String time;//更新时间
    String postCode;//邮编
    String weather;//天气情况
    int temp;//温度
    int l_tmp;//最低温度
    int h_tmp;//最高温度
    String WD;//风向
    String WS;//风力
*/

    public static  CityWeather cityToJson(String city){
        return dealJson(getResult(city));
    }


    /*用于得到最后结果*/
    public static String getResult(String city){
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = NetUrl.httpUrl + "?" +NetUrl.httpArg;
        Log.e("url",httpUrl);
            try {
                Log.e("open url","进入try  catch块");
                URL url = new URL(NetUrl.httpUrl);
                Log.e("connect1","connection connect");
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                Log.e("connect2","connection connect");
                connection.setRequestMethod("GET");
                // 填入apikey到HTTP header
                Log.e("connect3","connection connect");
                connection.setRequestProperty("apikey", NetUrl.ak);
                Log.e("connect4", "connection connect");

 //代码报错的地方
                connection.connect();
                Log.e("connect5","connection connect");
                InputStream is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String strRead = null;
                Log.e("strRead", "初始化strReader");
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                    Log.e("sbf,append", "sbf读取数据");
                }
                reader.close();
                result = sbf.toString();
                Log.e("close reader",result);
        } catch (Exception e) {
            e.printStackTrace();

                Log.e("catch","网络连接异常");
        }
        Log.e("执行内部","读取数据5"+result);
        return decodeUnicode(result);
    }

    /*
    * 网络请求得到的json中含有unicode编码，用下面的代码进行转换
    * */
    private static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    /*
    * json数据格式的处理
    * */
    public static CityWeather dealJson(String jsonString) {
        CityWeather cw=null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);     //返回的数据形式是一个Object类型，所以可以直接转换成一个Object
            String errMsg = jsonObject.getString("errMsg");
            int errNum = jsonObject.getInt("errNum");

            if("success".equals(errMsg)){
                cw=new CityWeather();

                JSONObject retDataObject = jsonObject.getJSONObject("retData");       //获取对象中的对象
                String city = retDataObject.getString("city");  //得到city
                cw.setCity(city);
                String date = retDataObject.getString("date");  //得到city
                cw.setDate(date);
                String time = retDataObject.getString("time");  //得到city
                cw.setTime(time);
                String  postCode = retDataObject.getString("postCode");  //得到city
                cw.setPostCode(postCode);
                String weather = retDataObject.getString("weather");  //得到city
                cw.setWeather(weather);
                String WD = retDataObject.getString("WD");  //得到city
                cw.setWD(WD);
                String WS = retDataObject.getString("WS");  //得到city
                cw.setWS(WS);
                int citycode = retDataObject.getInt("citycode");
                cw.setCitycode(citycode);
                int temp = retDataObject.getInt("temp");
                cw.setTemp(temp);
                int l_tmp = retDataObject.getInt("l_tmp");
                cw.setL_tmp(l_tmp);
                int h_tmp = retDataObject.getInt("h_tmp");
                cw.setH_tmp(h_tmp);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        * 这里应该添加数据库的东西
        *
        * */
      return cw;
    }

}
