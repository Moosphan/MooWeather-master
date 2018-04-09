package com.moos.weather.application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by moos on 2017/12/25.
 */
public class MoosApplication extends Application {
    public static Map<String, String> toOpen = new LinkedHashMap<String, String>();
    public static final String TAG = "MooWeather";
    public static String mToken;
    public static int count;

    /**
     * 获取设备的语言环境
     */
    public static String languageCode;

    //记录app已经启动的
    public static Boolean startedApp;


    //声明AMapLocationClient对象
    static AMapLocationClient aMapLocationClient;
    //定位参数
    AMapLocationClientOption mapLocationClientOption;
    //定位信息
    public static AMapLocation mapLocation;
    private IntentFilter intentFilter;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        startedApp = true;
        count = 0;
        languageCode = Locale.getDefault().getDisplayLanguage();
        if (languageCode.equals("中文")) {
            languageCode = "CN";
        } else {
            languageCode = "EN";
        }

        initGDLocation();


    }


    private static Map<String, Object> datas = new LinkedHashMap<String, Object>();

    /**
     * 向全局的数据容器中存放数据,用来多个activity或者fragment进行数据交换
     *
     * @param key   填充的数据key
     * @param value 填充的数据
     * @return 如果此key 已经与其他的值 产生了映射关系,那么本次操作会将旧的数据覆盖,并且会返回旧的数据,否则返回null
     */
    public static Object put(String key, Object value) {

        return datas.put(key, value);
    }

    /**
     * 从全局的数据容器中获取数据
     *
     * @param key      获取数据的密钥
     * @param isDelete 本次获取数据,是否在数据容器中清除数据
     * @return
     */
    public static Object get(String key, boolean isDelete) {
        if (isDelete) {
            return datas.remove(key);
        }
        return datas.get(key);

    }

    public static Object remove(String key) {
        return datas.remove(key);
    }


    /**
     * 将json转换成hashmap
     *
     * @param object
     * @return hashmap
     */
    public static HashMap<String, String> toHashMap(String object) {
        HashMap<String, String> strMap = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(object);

            Iterator it = jsonObject.keys();
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = jsonObject.getString(key);
                strMap.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strMap;
    }





    /**
     * 初始化AMapLocationClient对象和AMapLocationClientOption对象，
     * 并设置定位参数
     */
    private void initGDLocation() {

        aMapLocationClient = new AMapLocationClient(getApplicationContext());
        mapLocationClientOption = new AMapLocationClientOption();
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setGpsFirst(false);
        mapLocationClientOption.setHttpTimeOut(30000);
        mapLocationClientOption.setInterval(20000);
        mapLocationClientOption.setNeedAddress(true);
        mapLocationClientOption.setOnceLocation(true);
        mapLocationClientOption.setOnceLocationLatest(true);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        aMapLocationClient.setLocationOption(mapLocationClientOption);
        startLocate();
        setListener();
    }

    public static void startLocate(){
        aMapLocationClient.startLocation();
    }
    public void setListener() {


        /**
         * 定位回调监听器
         * 用于接收异步返回的定位结果，回调参数是AMapLocation
         */
        aMapLocationClient.setLocationListener(new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {

                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        aMapLocation.getLocationType();
                        aMapLocation.getLatitude();
                        aMapLocation.getLongitude();
                        aMapLocation.getAccuracy();
                        aMapLocation.getAddress();
                        aMapLocation.getCountry();
                        aMapLocation.getProvince();
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        String street = aMapLocation.getStreet();
                        aMapLocation.getStreetNum();
                        aMapLocation.getCityCode();
                        aMapLocation.getAdCode();
                        aMapLocation.getAoiName();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);
                        MoosApplication.mapLocation = aMapLocation;
                        Log.e(TAG, "定位坐标1是==" + MoosApplication.mapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                        double latitude = aMapLocation.getLatitude();
                        double longtgitude = aMapLocation.getLongitude();
                        if (mapLocation == null) {
                            mapLocation = aMapLocation;
                        }
                        Log.e(TAG, "定位坐标是==" + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                        Log.e(TAG, "Application保存的定位坐标是==" + mapLocation.getLatitude() + "," + mapLocation.getLongitude());
                        Log.e(TAG, "定位城市是==" + city + district + street);

                    } else {
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
        }
        aMapLocationClient = null;
    }
}
