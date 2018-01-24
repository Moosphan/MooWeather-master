package com.moos.weather.bean.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by moos on 2018/1/24.
 * func:搜索城市时的搜索提示建议数据
 */

public class CitySearchSuggestion implements SearchSuggestion {

    private String cityName;
    private boolean mIsHistory = false;

    public CitySearchSuggestion(String suggestion) {
        this.cityName = suggestion.toLowerCase();
    }

    public CitySearchSuggestion(Parcel source) {
        this.cityName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return cityName;
    }

    public static final Parcelable.Creator<CitySearchSuggestion> CREATOR = new Parcelable.Creator<CitySearchSuggestion>() {
        @Override
        public CitySearchSuggestion createFromParcel(Parcel in) {
            return new CitySearchSuggestion(in);
        }

        @Override
        public CitySearchSuggestion[] newArray(int size) {
            return new CitySearchSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
