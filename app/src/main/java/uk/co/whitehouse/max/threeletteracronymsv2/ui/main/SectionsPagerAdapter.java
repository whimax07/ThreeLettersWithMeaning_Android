package uk.co.whitehouse.max.threeletteracronymsv2.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import uk.co.whitehouse.max.threeletteracronymsv2.R;
import uk.co.whitehouse.max.threeletteracronymsv2.ui.main.add_acronym.AddAcronymFragment;
import uk.co.whitehouse.max.threeletteracronymsv2.ui.main.list_acronyms.DisplayAcronymFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    public final ArrayList<Fragment> fragments = new ArrayList<>();

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        fragments.add(AddAcronymFragment.newInstance());
        fragments.add(DisplayAcronymFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 1 total pages.
        return 2;
    }
}