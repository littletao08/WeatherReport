package com.example.qingwen_li.weatherreport.Activity.Bean;

/**
 * Created by Qingwen_li on 2016/1/30.
 */
public class CityWeather {


        private String city;//城市
        private int citycode;//编码
        private String date;//日期
        private String time;//更新时间
        private String postCode;//邮编
        private String weather;//天气情况
        private int temp;//温度
        private int l_tmp;//最低温度
        private int h_tmp;//最高温度
        private String WD;//风向
        private String WS;//风力

        public int getCitycode() {
                return citycode;
        }

        public void setCitycode(int citycode) {
                this.citycode = citycode;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public String getPostCode() {
                return postCode;
        }

        public void setPostCode(String postCode) {
                this.postCode = postCode;
        }

        public String getWeather() {
                return weather;
        }

        public void setWeather(String weather) {
                this.weather = weather;
        }

        public int getTemp() {
                return temp;
        }

        public void setTemp(int temp) {
                this.temp = temp;
        }

        public int getL_tmp() {
                return l_tmp;
        }

        public void setL_tmp(int l_tmp) {
                this.l_tmp = l_tmp;
        }

        public int getH_tmp() {
                return h_tmp;
        }

        public void setH_tmp(int h_tmp) {
                this.h_tmp = h_tmp;
        }

        public String getWD() {
                return WD;
        }

        public void setWD(String WD) {
                this.WD = WD;
        }

        public String getWS() {
                return WS;
        }

        public void setWS(String WS) {
                this.WS = WS;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

}
