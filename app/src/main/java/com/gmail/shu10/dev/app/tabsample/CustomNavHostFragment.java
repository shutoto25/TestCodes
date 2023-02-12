package com.gmail.shu10.dev.app.tabsample;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;

public class CustomNavHostFragment extends NavHostFragment {
    @NonNull
    @Override
    protected Navigator<? extends FragmentNavigator.Destination> createFragmentNavigator() {
        Log.d("CustomNavHostFragment", "createFragmentNavigator() called");
        return new CustomNavigator(requireContext(), getChildFragmentManager(), getId());
    }
}


