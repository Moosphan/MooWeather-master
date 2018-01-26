package com.moos.weather.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.moos.weather.R;
import com.moos.weather.bean.Model.CitySearchSuggestion;
import com.moos.weather.data.SearchDataHelper;
import com.moos.weather.ui.adapter.SearchResultListAdapter;
import com.moos.weather.ui.fragment.HomeFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.moos.weather.application.MoosApplication.TAG;

public class SearchActivity extends AppCompatActivity {
    /**
     * by moos on 2018/01/21
     * func:城市搜索界面
     * @param savedInstanceState
     */
    @Bind(R.id.floating_search_view)
    FloatingSearchView mSearchView;
    @Bind(R.id.search_results_list)
    RecyclerView recyclerView;

    private SearchResultListAdapter mSearchResultsAdapter;
    private String TAG  = "SearchPage";
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private String mLastQuery = "";
    private boolean mIsDarkSearchTheme = false;
    private List<Tip> tips;//获取的高德提示数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        initSearchView();
        initResultsList();
    }

    private void initSearchView(){

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    SearchDataHelper.findSuggestions(SearchActivity.this, newQuery, 5,
                            FIND_SUGGESTION_SIMULATED_DELAY, new SearchDataHelper.OnFindSuggestionsListener() {

                                @Override
                                public void onResults(List<CitySearchSuggestion> results) {

                                    //this will swap the data and
                                    //render the collapse/expand animations as necessary
                                    mSearchView.swapSuggestions(results);

                                    //let the users know that the background
                                    //process has completed
                                    mSearchView.hideProgress();
                                }
                            });

                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
                    @Override
                    public void onSuggestionClicked(final SearchSuggestion searchSuggestion) {

                        CitySearchSuggestion citySearchSuggestion = (CitySearchSuggestion) searchSuggestion;
                        String suggestCity = citySearchSuggestion.getBody();
                        Log.e(TAG, "onSuggestionClicked: "+suggestCity);
                        LatLng latLng;
                        switch (suggestCity){
                            case "上海":
                                latLng = new LatLng(31.203512,121.602938);
                                break;
                            case "北京":
                                latLng = new LatLng(39.91667,116.41667);
                                break;
                            case "南京":
                                latLng = new LatLng(32.05000,118.78333);
                                break;
                            case "西安":
                                latLng = new LatLng(34.26667,108.95000);
                                break;
                            case "成都":
                                latLng = new LatLng(30.66667,104.06667);
                                break;
                            case "厦门":
                                latLng = new LatLng(24.46667,118.10000);
                                break;
                            default:
                                throw new IllegalArgumentException("valid city for suggesting~");

                        }

                        Intent intent = new Intent();
                        intent.putExtra("search_location_lat",latLng.latitude);
                        intent.putExtra("search_location_lon",latLng.longitude);
                        intent.putExtra("search_location_address",suggestCity);
                        setResult(HomeFragment.SEARCH_RESULT_CODE,intent);
                        finish();
                        Log.d(TAG, "onSuggestionClicked()");

                        mLastQuery = searchSuggestion.getBody();
                    }

                    @Override
                    public void onSearchAction(String query) {
                        mLastQuery = query;

                        SearchDataHelper.findColors( SearchActivity.this,query,
                                new SearchDataHelper.OnFindColorsListener() {

                                    @Override
                                    public void onResults(List<Tip> results) {
                                        tips = results;
                                        mSearchResultsAdapter.swapData(results);
                                    }

                                });
                        Log.d(TAG, "onSearchAction()");
                    }
                });
            }
        });


        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                //show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(SearchDataHelper.getHistory(SearchActivity.this, 3));

                Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                //set the title of the bar so that when focus is returned a new query begins
                mSearchView.setSearchBarTitle(mLastQuery);

                //you can also set setSearchText(...) to make keep the query there when not focused and when focus returns
                //mSearchView.setSearchText(searchSuggestion.getBody());

                Log.d(TAG, "onFocusCleared()");
            }
        });


        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.settings) {

                    mIsDarkSearchTheme = true;

                    //demonstrate setting colors for items
                    mSearchView.setBackgroundColor(Color.parseColor("#787878"));
                    mSearchView.setViewTextColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setHintTextColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setActionMenuOverflowColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setMenuItemIconColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setClearBtnColor(Color.parseColor("#e9e9e9"));
                    mSearchView.setDividerColor(Color.parseColor("#BEBEBE"));
                    mSearchView.setLeftActionIconColor(Color.parseColor("#e9e9e9"));
                } else {

                    //just print action
                    Toast.makeText(getApplicationContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

                Log.d(TAG, "onHomeClicked()");
            }
        });

        /*
         * Here you have access to the left icon and the text of a given suggestion
         * item after as it is bound to the suggestion list. You can utilize this
         * callback to change some properties of the left icon and the text. For example, you
         * can load the left icon images using your favorite image loading library, or change text color.
         *
         *
         * Important:
         * Keep in mind that the suggestion list is a RecyclerView, so views are reused for different
         * items in the list.
         */
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                CitySearchSuggestion colorSuggestion = (CitySearchSuggestion) item;

                String textColor = mIsDarkSearchTheme ? "#ffffff" : "#000000";
                String textLight = mIsDarkSearchTheme ? "#bfbfbf" : "#787878";

                if (colorSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = colorSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font color=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(Html.fromHtml(text));
            }

        });

        //listen for when suggestion list expands/shrinks in order to move down/up the
        //search results list
        mSearchView.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {
                recyclerView.setTranslationY(newHeight);
            }
        });

        /*
         * When the user types some text into the search field, a clear button (and 'x' to the
         * right) of the search text is shown.
         *
         * This listener provides a callback for when this button is clicked.
         */
        mSearchView.setOnClearSearchActionListener(new FloatingSearchView.OnClearSearchActionListener() {
            @Override
            public void onClearSearchClicked() {

                Log.d(TAG, "onClearSearchClicked()");
            }
        });
    }

    private void initResultsList() {
        mSearchResultsAdapter = new SearchResultListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mSearchResultsAdapter);
        mSearchResultsAdapter.setItemsOnClickListener(new SearchResultListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view,int position) {
                Log.e(TAG,"搜索返回的位置信息数量>>>>"+tips.size());
                Intent intent = new Intent();
                intent.putExtra("search_location_lat",tips.get(position).getPoint().getLatitude());
                intent.putExtra("search_location_lon",tips.get(position).getPoint().getLongitude());
                intent.putExtra("search_location_address",tips.get(position).getName());
                setResult(HomeFragment.SEARCH_RESULT_CODE,intent);
                finish();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
