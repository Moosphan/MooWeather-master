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
    public static int towhich = 0;

    //定位参数
    AMapLocationClientOption mapLocationClientOption;
    //定位信息
    public static AMapLocation mapLocation;
    //各种定位信息
    private IntentFilter intentFilter;
    //private NetworkChangeReceiver networkChangeReceiver;
    private static Context context;


//    {
//        PlatformConfig.setWeixin("wx6a91b4678e786345", "2d3ea5023cd9746ae5daec021a09f48b");
//        PlatformConfig.setQQZone("1106281483", "QgcpZixmyfAIM1L8");
//        PlatformConfig.setSinaWeibo(SinaConstants.APP_KEY, SinaConstants.SECRET, SinaConstants.REDIRECT_URL);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        startedApp = true;
        //初始化活动的集合
        //记录更新提示弹窗次数
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
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mapLocationClientOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mapLocationClientOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mapLocationClientOption.setInterval(20000);//可选，设置定位间隔。默认为2秒
        mapLocationClientOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mapLocationClientOption.setOnceLocation(true);//获取一次定位结果：该方法默认为false。
        //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        mapLocationClientOption.setOnceLocationLatest(true);
        //可选 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        //注意：https方式请求定位对定位速度和性能有一定的损耗，定位流量会增大，但安全性更高。
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        //给定位客户端对象设置定位参数
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

                    //当定位错误码类型为0时定位成功
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        aMapLocation.getAddress();
                        aMapLocation.getCountry();//国家信息
                        aMapLocation.getProvince();//省信息
                        String city = aMapLocation.getCity();//城市信息
                        String district = aMapLocation.getDistrict();//城区信息
                        String street = aMapLocation.getStreet();//街道信息
                        aMapLocation.getStreetNum();//街道门牌号信息
                        aMapLocation.getCityCode();//城市编码
                        aMapLocation.getAdCode();//地区编码
                        aMapLocation.getAoiName();//获取当前定位点的AOI信息
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);
                        MoosApplication.mapLocation = aMapLocation;
                        Log.e(TAG, "定位坐标1是==" + MoosApplication.mapLocation.getLatitude() + "," + aMapLocation.getLongitude());
//                        mapLocation = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                        double latitude = aMapLocation.getLatitude();
                        double longtgitude = aMapLocation.getLongitude();
                        if (mapLocation == null) {
                            mapLocation = aMapLocation;
                        }
                        //EventBus.getDefault().post(mapLocation);
                        Log.e(TAG, "定位坐标是==" + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                        Log.e(TAG, "Application保存的定位坐标是==" + mapLocation.getLatitude() + "," + mapLocation.getLongitude());
                        Log.e(TAG, "定位城市是==" + city + district + street);

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }

//                    aMapLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
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
