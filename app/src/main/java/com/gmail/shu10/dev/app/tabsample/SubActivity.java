package com.gmail.shu10.dev.app.tabsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.shu10.dev.app.tabsample.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    ActivitySubBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}
