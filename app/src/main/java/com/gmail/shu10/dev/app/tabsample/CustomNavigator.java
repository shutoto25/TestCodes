package com.gmail.shu10.dev.app.tabsample;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorState;
import androidx.navigation.fragment.FragmentNavigator;

import java.util.List;

@Navigator.Name("custom_fragment")
public class CustomNavigator extends FragmentNavigator {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private int mContainerId;

    public CustomNavigator(@NonNull Context context,
                           @NonNull FragmentManager fragmentManager,
                           int containerId) {
        super(context, fragmentManager, containerId);
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
    }

    @Override
    public void navigate(@NonNull List<NavBackStackEntry> entries,
                         @Nullable NavOptions navOptions,
                         @Nullable Navigator.Extras navigatorExtras) {
        super.navigate(entries, navOptions, navigatorExtras);
        if (mFragmentManager.isStateSaved()) {
            Log.i(
                "TAG", "Ignoring navigate() call:" +
                            " FragmentManager has already saved its state");
            return;
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        for (NavBackStackEntry entry : entries) {
            NavigatorState state = getState();
//            boolean initialNavigation = state.getBackStack().getValue().isEmpty();
//            boolean restoreState = (
//                    navOptions != null && !initialNavigation &&
//                            navOptions.shouldRestoreState() &&
//                            savedIds.remove(entry.id)
//            )
//            if (restoreState) {
            // Restore back stack does all the work to restore the entry
            String tag = entry.getId();
            mFragmentManager.restoreBackStack(tag);
            getState().push(entry);
//                return;
//            }
            if (!initialNavigation) {
                ft.addToBackStack(tag)
            }

            if (navigatorExtras is Extras){
                for ((key, value) in navigatorExtras.sharedElements){
                    ft.addSharedElement(key, value)
                }
            }
            ft.commit()
            // The commit succeeded, update our view of the world
            state.push(entry)


            Fragment fragment = mFragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                Log.d("CustomNavigator", "navigate() called instantiateFragment");
                fragment = instantiateFragment(mContext, mFragmentManager, className, args);
                transaction.add(mContainerId, fragment, tag);
            }

            fragment.setArguments(args);
            transaction.show(fragment);
            transaction.setPrimaryNavigationFragment(fragment);
            transaction.commit();

        }
    }
}
