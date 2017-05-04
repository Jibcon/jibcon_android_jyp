package com.asuscomm.yangyinetwork.jibcon_jyp;

import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "jaeyoung/"+getClass().getSimpleName();

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SeekBar mDiscreteProgress;
    private ImageButton mNextBtn, mSkipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHeaderView();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        initHeaderViewListener();
    }

    private void initHeaderView() {
        Log.d(TAG, "initHeaderView: ");
        mDiscreteProgress = (SeekBar) findViewById(R.id.discreteProgress);
        mNextBtn = (ImageButton) findViewById(R.id.nextBtn);
        mSkipBtn = (ImageButton) findViewById(R.id.skipBtn);
    }

    private void initHeaderViewListener() {
        Log.d(TAG, "initHeaderViewListener: ");
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected() called with: position = [" + position + "]");
                mDiscreteProgress.setProgress(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mDiscreteProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged() called with: seekBar = [" + seekBar + "], progress = [" + progress + "], fromUser = [" + fromUser + "]");
                if(fromUser) {
                    mViewPager.setCurrentItem(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"mNextBtn");
                mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"mSkipBtn");
                goNextActivity();
            }
        });
    }

    public void goNextActivity() {
        Log.d(TAG, "goNextActivity: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private final String TAG = "jaeyoung/"+getClass().getSimpleName();
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            int sectionNumberAsIndex = sectionNumber - 1;

            TextView tutorialHeadTv = (TextView) rootView.findViewById(R.id.tutorialHeadTv);
            TextView tutorialDetailTv = (TextView) rootView.findViewById(R.id.tutorialDetailTv);
            Button startBtn = (Button) rootView.findViewById(R.id.startBtn);

            tutorialHeadTv.setText(getResources().getStringArray(R.array.tutorial_head)[sectionNumberAsIndex]);
            tutorialDetailTv.setText(getResources().getStringArray(R.array.tutorial_detail)[sectionNumberAsIndex]);
            if (sectionNumber == getResources().getInteger(R.integer.tutorial_number)) {
                startBtn.setVisibility(View.VISIBLE);
                startBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: "+"startBtn");
                        // start new activity
                        ((MainActivity)getActivity()).goNextActivity();
                    }
                });
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            int tutorial_number = getResources().getInteger(R.integer.tutorial_number);
            Log.d(TAG, "getCount: "+tutorial_number);
            return tutorial_number;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "getPageTitle: ");
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
