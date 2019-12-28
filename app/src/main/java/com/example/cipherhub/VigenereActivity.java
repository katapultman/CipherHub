package com.example.cipherhub;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import ui.KeyCipherFragment;
import ui.SectionFragment;
import ui.FragmentPageAdapter;
import ui.SetUpViewPager;
import ui.ui_custom.ui_table_key_ciphers.AdditionalVigenereFragment;

public class VigenereActivity extends Activity implements SetUpViewPager {

    ViewPager viewPager;
    FragmentPageAdapter fragmentPageAdapter;

    public void setUpViewPager(ViewPager viewPager) {
        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        for(Fragment fragment : fragmentCollection.get(2).keySet()) {
            adapter.addFragment(fragment, fragmentCollection.get(2).get(fragment));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vigenere);

        configureToolbar();

        fragmentPageAdapter = new FragmentPageAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.container);

        setUpViewPager(viewPager);

        viewPager.setCurrentItem(0);
    }
}
