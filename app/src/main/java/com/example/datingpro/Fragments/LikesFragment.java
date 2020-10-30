package com.example.datingpro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.example.datingpro.Adapter.CommPagerAdapter;
import com.example.datingpro.Helper.BaseHelper;
import com.example.datingpro.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class LikesFragment extends Fragment {
    View view;
    GetMoreLikeFragment getMoreLikeFragment;
    MatchFragment matchFragment;
    private CommPagerAdapter pagerAdapter;
    ViewPager viewPager;
    XTabLayout tabLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(BaseHelper.Likes==false) {
            view = inflater.inflate(R.layout.likes_fragment, container, false);
            viewPager = (ViewPager) view.findViewById(R.id.viewpager);
            tabLayout = (XTabLayout) view.findViewById(R.id.tab_title);
            BaseHelper.isBlocked=false;
            getMoreLikeFragment = new GetMoreLikeFragment();
            matchFragment = new MatchFragment();
            setFragments();
            //BaseHelper.Likes=true;
        }
        return view;
    }

    public void setFragments() {
        fragments.add(getMoreLikeFragment);
        fragments.add(matchFragment);
        tabLayout.addTab(tabLayout.newTab().setText("LIKES"));
        tabLayout.addTab(tabLayout.newTab().setText("MATCHES"));
        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[]{"LIKES", "MATCHES"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).select();
    }

}
