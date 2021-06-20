package uk.co.whitehouse.max.threeletteracronymsv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import uk.co.whitehouse.max.threeletteracronymsv2.data.AcronymModel;
import uk.co.whitehouse.max.threeletteracronymsv2.data.SaveAcronyms;
import uk.co.whitehouse.max.threeletteracronymsv2.databinding.ActivityMainBinding;
import uk.co.whitehouse.max.threeletteracronymsv2.ui.main.SectionsPagerAdapter;
import uk.co.whitehouse.max.threeletteracronymsv2.ui.main.list_acronyms.DisplayAcronymFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final AcronymModel acronymModel = AcronymModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        acronymModel.setAcronymsList(SaveAcronyms.load(getApplicationContext()));

        DisplayAcronymFragment fragment = (DisplayAcronymFragment) sectionsPagerAdapter.fragments.get(1);
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(@NonNull @NotNull TabLayout.Tab tab) {
                super.onTabSelected(tab);
                if (acronymModel.isUpdated() && fragment.getRecyclerViewAdapter() != null) {
                    fragment.getRecyclerViewAdapter().notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SaveAcronyms.save(getApplicationContext(), acronymModel.getAcronymsList());
    }
}