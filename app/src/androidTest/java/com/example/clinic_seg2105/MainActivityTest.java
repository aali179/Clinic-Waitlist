package com.example.clinic_seg2105;

import android.widget.TextView;

import org.junit.Rule;
import android.support.test.rule.ActivityTestRule;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;
    private TextView text;

}
