package com.example.ljli.weather.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.ljli.weather.db.City;
import com.example.ljli.weather.db.County;
import com.example.ljli.weather.db.Province;
import com.example.ljli.weather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
//    解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject provinceObject=jsonArray.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //    解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject cityObject=jsonArray.getJSONObject(i);
                     City city=new City();
                     city.setProvinceId(provinceId);
                     city.setCityCode(cityObject.getInt("id"));
                     city.setCityNmae(cityObject.getString("name"));
                     city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


//    解析和处理服务器返回的县级数据
public static boolean handleCountyResponse(String response,int cityId){
    if(!TextUtils.isEmpty(response)){
        try{
            JSONArray jsonArray=new JSONArray(response);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject countyObject=jsonArray.getJSONObject(i);
                County county=new County();
                county.setCountName(countyObject.getString("name"));
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    return false;
}


//   将返回的JSON数据解析成weather类
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            //这个返回的数据有问题
           Weather weather= new Gson().fromJson(weatherContent,Weather.class);
           if(weather==null){
               Log.d("返回的天气类","为空");
           }
            return weather;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
