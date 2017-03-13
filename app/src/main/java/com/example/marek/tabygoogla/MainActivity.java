package com.example.marek.tabygoogla;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
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

import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_LANGUAGE = 1;
    public static final int TYPE_GENERAL = 2;
    public static final int TYPE_EDUCATION = 3;
    public static final int TYPE_REFERENCE = 4;
    public static final int TYPE_SEPARATOR = 5;
    public static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

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
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Toolbar tabToolbar = (Toolbar) findViewById(R.id.tabs);
        //setSupportActionBar(tabToolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);



        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

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

        Intent nextScreen;
        Intent callIntent;
        Intent mailIntent;

        switch (item.getItemId()) {
            case R.id.action_call:
                try {

                    callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+getString(R.string.tel_number)));
                    startActivity(callIntent);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("Calling a Phone Number", "Call failed", activityException);
                }


                return true;

            case R.id.action_mail:

                try {

                    mailIntent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                    mailIntent.setType("text/plain");
                    mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
                    mailIntent.setData(Uri.parse("mailto:"+getString(R.string.mail_adress))); // or just "mailto:" for blank
                    mailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                    startActivity(mailIntent);

                } catch (ActivityNotFoundException activityException) {
                    Log.e("Mailing an adress", "Mail failed", activityException);
                }

                return true;

            case R.id.action_linkedin:

                try {

                    Uri uriUrl = Uri.parse("https://www.linkedin.com/in/marek-pasierbek-a8992ba7#recommendations");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);

                } catch (ActivityNotFoundException activityException) {
                    Log.e("Mailing an adress", "Mail failed", activityException);
                }

                return true;

            case R.id.action_language:

                nextScreen = new Intent(getApplicationContext(), Language.class);

                startActivity(nextScreen);

                return true;

            case R.id.action_settings:

                nextScreen = new Intent(getApplicationContext(), Settings.class);

                startActivity(nextScreen);

                return true;

            case R.id.action_exit:
                this.finishAffinity();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            TextView textView;
            GenAdapter mAdapter;
            ExpAdapter eAdapter;
            ListView list;
            ExpandableListView eList = null;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:

                    mAdapter = new GenAdapter(rootView.getContext());

                    mAdapter.addNewItem(TYPE_GENERAL, "glowa i info ");
                    mAdapter.addNewItem(TYPE_SEPARATOR, getString(R.string.separator_education));
                    mAdapter.addNewItem(TYPE_EDUCATION, "Univ");
                    mAdapter.addNewItem(TYPE_SEPARATOR, getString(R.string.separator_languages));
                    mAdapter.addNewItem(TYPE_LANGUAGE, getString(R.string.lang_english),"4","5");
                    mAdapter.addNewItem(TYPE_LANGUAGE, getString(R.string.lang_polish),"5","5");
                    mAdapter.addNewItem(TYPE_LANGUAGE, getString(R.string.lang_swedish),"1","1");
                    mAdapter.addNewItem(TYPE_SEPARATOR, getString(R.string.separator_skills));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_1));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_2));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_3));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_4));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_5));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_6));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_7));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_8));
                    mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_9));
                    //mAdapter.addNewItem(TYPE_ITEM, getString(R.string.skill_10));
                    mAdapter.addNewItem(TYPE_SEPARATOR, getString(R.string.separator_references));
                    mAdapter.addNewItem(TYPE_REFERENCE, getString(R.string.ref_name_1),getString(R.string.ref_role_1),getString(R.string.ref_tel_1));
                    mAdapter.addNewItem(TYPE_REFERENCE, getString(R.string.ref_name_2),getString(R.string.ref_role_2),getString(R.string.ref_tel_2));
                    mAdapter.addNewItem(TYPE_REFERENCE, getString(R.string.ref_name_3),getString(R.string.ref_role_3),getString(R.string.ref_tel_3));

                    list = (ListView) rootView.findViewById(R.id.section_list);
                    list.setAdapter(mAdapter);



                    break;

                case 2:

                    /*
                    ExpBean_data = new ExpBean[] {

                            new ExpBean(R.mipmap.ic_comp_ss, getString(R.string.exp_role_4),getString(R.string.exp_comp_4),getString(R.string.exp_time_4)),
                            new ExpBean(R.mipmap.ic_comp_ss, getString(R.string.exp_role_3),getString(R.string.exp_comp_3),getString(R.string.exp_time_3)),
                            new ExpBean(R.mipmap.ic_comp_brms, getString(R.string.exp_role_2),getString(R.string.exp_comp_2),getString(R.string.exp_time_2)),
                            new ExpBean(R.mipmap.ic_comp_us, getString(R.string.exp_role_1),getString(R.string.exp_comp_1),getString(R.string.exp_time_1))
                    };



                    adapter = new ExpAdapter(rootView.getContext(), R.layout.list_element_experience_parent, ExpBean_data);
                    list = (ListView) rootView.findViewById(R.id.section_list);
                    list.setAdapter(adapter);
                    */

                    ArrayList<ExpParent> parents;
                    final ArrayList<ExpParent> expList = new ArrayList<ExpParent>();
                    for (int i = 4; i > 0; i--)
                    {
                        //Create parent class object
                        final ExpParent parent = new ExpParent();

                        // Set values in parent class object
                        if(i==4){
                            parent.setName("ss");
                            parent.setText1(getString(R.string.exp_role_4));
                            parent.setText2(getString(R.string.exp_comp_4));
                            parent.setText3(getString(R.string.exp_time_4));
                            parent.setChildren(new ArrayList<ExpChild>());


                            final ExpChild child = new ExpChild();
                            child.setText1(getString(R.string.exp_resp_4_1));
                            parent.getChildren().add(child);
                            final ExpChild child1 = new ExpChild();
                            child1.setText1(getString(R.string.exp_resp_4_2));
                            parent.getChildren().add(child1);
                            final ExpChild child2 = new ExpChild();
                            child2.setText1(getString(R.string.exp_resp_4_3));
                            parent.getChildren().add(child2);
                            final ExpChild child3 = new ExpChild();
                            child3.setText1(getString(R.string.exp_resp_4_4));
                            parent.getChildren().add(child3);

                        }
                        else if(i==3){
                            parent.setName("ss");
                            parent.setText1(getString(R.string.exp_role_3));
                            parent.setText2(getString(R.string.exp_comp_3));
                            parent.setText3(getString(R.string.exp_time_3));
                            parent.setChildren(new ArrayList<ExpChild>());

                            final ExpChild child = new ExpChild();
                            child.setText1(getString(R.string.exp_resp_3_1));
                            parent.getChildren().add(child);
                            final ExpChild child1 = new ExpChild();
                            child1.setText1(getString(R.string.exp_resp_3_2));
                            parent.getChildren().add(child1);
                            final ExpChild child2 = new ExpChild();
                            child2.setText1(getString(R.string.exp_resp_3_3));
                            parent.getChildren().add(child2);
                            final ExpChild child3 = new ExpChild();
                            child3.setText1(getString(R.string.exp_resp_3_4));
                            parent.getChildren().add(child3);
                            final ExpChild child4 = new ExpChild();
                            child4.setText1(getString(R.string.exp_resp_3_5));
                            parent.getChildren().add(child4);
                        }
                        else if(i==2){
                            parent.setName("brms");
                            parent.setText1(getString(R.string.exp_role_2));
                            parent.setText2(getString(R.string.exp_comp_2));
                            parent.setText3(getString(R.string.exp_time_2));
                            parent.setChildren(new ArrayList<ExpChild>());

                            final ExpChild child = new ExpChild();
                            child.setText1(getString(R.string.exp_resp_2_1));
                            parent.getChildren().add(child);
                            final ExpChild child1 = new ExpChild();
                            child1.setText1(getString(R.string.exp_resp_2_2));
                            parent.getChildren().add(child1);
                            final ExpChild child2 = new ExpChild();
                            child2.setText1(getString(R.string.exp_resp_2_3));
                            parent.getChildren().add(child2);
                        }
                        else if(i==1){
                            parent.setName("us");
                            parent.setText1(getString(R.string.exp_role_1));
                            parent.setText2(getString(R.string.exp_comp_1));
                            parent.setText3(getString(R.string.exp_time_1));
                            parent.setChildren(new ArrayList<ExpChild>());

                            final ExpChild child = new ExpChild();
                            child.setText1(getString(R.string.exp_resp_1_1));
                            parent.getChildren().add(child);
                            final ExpChild child1 = new ExpChild();
                            child1.setText1(getString(R.string.exp_resp_1_2));
                            parent.getChildren().add(child1);
                            //final ExpChild child2 = new ExpChild();
                            //child2.setText1(getString(R.string.exp_resp_1_3));
                            //parent.getChildren().add(child2);

                        }

                        //Adding ExpParent class object to ArrayList
                        expList.add(parent);
                    }

                    parents = expList;

                    //Create ExpandableListAdapter Object
                    eAdapter = new ExpAdapter(rootView.getContext(), parents);

                    // Check for ExpandableListAdapter object

                    if (eList == null)
                    {
                        ;

                        // Set Adapter to ExpandableList Adapter
                        eList = (ExpandableListView) rootView.findViewById(R.id.section_explist);
                        eList.setAdapter(eAdapter);
                    }
                    else
                    {
                        // Refresh ExpandableListView data
                        eAdapter.notifyDataSetChanged();
                    }



                    break;

                case 3:

                    textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(R.string.cover_letter);

                    break;

                default:




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
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.tab_general);
                case 1:
                    return getString(R.string.tab_experience);
                case 2:
                    return getString(R.string.tab_other);
            }
            return null;
        }
    }


}
