package com.moos.weather.data;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.moos.weather.bean.Model.CitySearchSuggestion;
import com.moos.weather.ui.activity.SearchActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Handler;

import static android.content.ContentValues.TAG;

/**
 * created by https://github.com/arimorty/floatingsearchview
 * updated by moos on 2018/1/24.
 * func:搜索结果的处理类
 */
public class SearchDataHelper {

    private static final String COLORS_FILE_NAME = "colors.json";

    private static List<Tip> searchResult = new ArrayList<>();

    private static List<CitySearchSuggestion> sColorSuggestions =
            new ArrayList<>(Arrays.asList(
                    new CitySearchSuggestion("北京"),
                    new CitySearchSuggestion("上海"),
                    new CitySearchSuggestion("南京"),
                    new CitySearchSuggestion("西安"),
                    new CitySearchSuggestion("成都"),
                    new CitySearchSuggestion("厦门")
                    ));

    public interface OnFindColorsListener {
        void onResults(List<Tip> results);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<CitySearchSuggestion> results);
    }

    public static List<CitySearchSuggestion> getHistory(Context context, int count) {

        List<CitySearchSuggestion> suggestionList = new ArrayList<>();
        CitySearchSuggestion colorSuggestion;
        for (int i = 0; i < sColorSuggestions.size(); i++) {
            colorSuggestion = sColorSuggestions.get(i);
            colorSuggestion.setIsHistory(true);
            suggestionList.add(colorSuggestion);
            if (suggestionList.size() == count) {
                break;
            }
        }
        return suggestionList;
    }

    public static void resetSuggestionsHistory() {
        for (CitySearchSuggestion colorSuggestion : sColorSuggestions) {
            colorSuggestion.setIsHistory(false);
        }
    }

    public static void findSuggestions(Context context, String query, final int limit, final long simulatedDelay,
                                       final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try {
                    Thread.sleep(simulatedDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SearchDataHelper.resetSuggestionsHistory();
                List<CitySearchSuggestion> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {

                    for (CitySearchSuggestion suggestion : sColorSuggestions) {
                        if (suggestion.getBody().toUpperCase()
                                .startsWith(constraint.toString().toUpperCase())) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<CitySearchSuggestion>() {
                    @Override
                    public int compare(CitySearchSuggestion lhs, CitySearchSuggestion rhs) {
                        return lhs.getIsHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<CitySearchSuggestion>) results.values);
                }
            }
        }.filter(query);

    }


    public static void findColors(Context context, String query, final OnFindColorsListener listener) {
        //initColorWrapperList(context);


        initSearchResultList(context,query,listener);


    }

    /**
     * by moos on 2018/01/24
     * func:初始化数据
     * @param context
     */
    public static void initSearchResultList(Context context,String inputString,OnFindColorsListener listener) {

        if (searchResult.isEmpty()) {
            getGaoDeSearchTips(context,inputString,listener);
        }else {
            searchResult.clear();
            getGaoDeSearchTips(context,inputString,listener);
        }
    }

    /**
     * by moos on 2018/01/24
     * func:通过高德输入提示接口获取提示数据（经纬度用于搜索城市天气）
     * @param inputTips
     */
    private static void getGaoDeSearchTips(Context context, final String inputTips, final OnFindColorsListener listener){
        InputtipsQuery inputtipsQuery = new InputtipsQuery(inputTips,"");
        inputtipsQuery.setCityLimit(false);
        Inputtips inputtips = new Inputtips(context,inputtipsQuery);
        inputtips.setInputtipsListener(new Inputtips.InputtipsListener() {
            @Override
            public void onGetInputtips(List<Tip> list, int i) {
                searchResult = list;
                Log.e(TAG,"搜索到的结果数目="+searchResult.size());
                new Filter() {

                    @Override
                    protected FilterResults performFiltering(CharSequence constraint) {


                        List<Tip> suggestionList = new ArrayList<>();

                        if (!(constraint == null || constraint.length() == 0)) {

                            for (Tip tip : searchResult) {
                                if (tip.getName().toUpperCase()
                                        .startsWith(constraint.toString().toUpperCase())) {

                                    suggestionList.add(tip);
                                }
                            }

                        }

                        FilterResults results = new FilterResults();
                        results.values = suggestionList;
                        results.count = suggestionList.size();

                        return results;
                    }

                    @Override
                    protected void publishResults(CharSequence constraint, FilterResults results) {

                        if (listener != null) {
                            listener.onResults((List<Tip>) results.values);
                        }
                    }
                }.filter(inputTips);

            }
        });
        inputtips.requestInputtipsAsyn();
    }

    /**
     * by moos on 2018/01/24
     * func:返回搜索结果
     * @return
     */
    public static List<Tip> getSearchResult(){
        return searchResult;
    }


}
