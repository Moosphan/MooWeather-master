package com.moos.weather.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.moos.weather.R;
import com.moos.weather.ui.activity.BlogActivity;
import com.moos.weather.ui.activity.SettingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by moos on 2017/12/24.
 */

public class PersonalCenterFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private int mParam1;

    @Bind(R.id.self_center_blog_bt)
    LinearLayout bt_scan_blog;
    @Bind(R.id.self_center_setting_bt)
    LinearLayout bt_go_setting;


    public PersonalCenterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MemoryPhotoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalCenterFragment newInstance(int param1) {
        PersonalCenterFragment fragment = new PersonalCenterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_center, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.self_center_blog_bt, R.id.self_center_setting_bt})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.self_center_blog_bt:
                /**
                 * to blog page
                 */
                Intent intent = new Intent(getActivity(), BlogActivity.class);
                startActivity(intent);
                break;

            case R.id.self_center_setting_bt:
                /**
                 * to setting page
                 */
                Intent intent_Setting = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent_Setting);
                break;
        }
    }
}