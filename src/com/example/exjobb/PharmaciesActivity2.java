package com.example.exjobb;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PharmaciesActivity2 extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	/*Nytt*/
	boolean phWithoutDr;
	String choosenDrugID;
	int nbrOfDrug;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pharmacies2);
	    
		Bundle b = getIntent().getExtras();
		phWithoutDr = b.getBoolean("PhWithoutDr");
		if(phWithoutDr == false) {
			Log.e("PhWithoutDrug", "False");
			choosenDrugID = b.getString("drugID");
			nbrOfDrug = b.getInt("nbrOfDrug");
		} else {
			Log.e("PhWithoutDrug", "True");
		}
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pharmacies_activity2, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	/*Ny*/
	public void CopyDB(InputStream inputStream, 
		    OutputStream outputStream) throws IOException {
		        //---copy 1K bytes at a time---
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = inputStream.read(buffer)) > 0) {
		            outputStream.write(buffer, 0, length);
		        }
		        inputStream.close();
		        outputStream.close();
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
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			//case 2:
			//	return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		/*Nytt*/
		ArrayAdapter<String> adapterArr;
		String[] presidents;
		ListView lstView;
		boolean phWithoutDr;
		String choosenDrugID;
		int nbrOfDrug;
		
		
		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pharmacies2, container, false);
			return rootView;
		}
		
		public void onActivityCreated (Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			
			/*Bundle b = getActivity().getIntent().getExtras();
			phWithoutDr = b.getBoolean("PhWithoutDr");
			if(phWithoutDr == false) {
				Log.e("PhWithoutDrug in F", "False");
				choosenDrugID = b.getString("drugID");
				nbrOfDrug = b.getInt("nbrOfDrug");
			} else {
				Log.e("PhWithoutDrug in F", "True");
			}*/
			
			String section = Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER));
			//Toast.makeText(getActivity(), "You are in section " + section, Toast.LENGTH_SHORT).show();
			
			switch(Integer.valueOf(section)) {
				case 1: Toast.makeText(getActivity(), "You are in section " + section, Toast.LENGTH_SHORT).show();
						presidents = new String[] {"A", "B", "C"};
						break;
				case 2: Toast.makeText(getActivity(), "You are in section " + section, Toast.LENGTH_SHORT).show();
						presidents = new String[] {"D", "E", "F"};
						break;
			}
			
			adapterArr = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, presidents);
			lstView = (ListView) getView().findViewById(R.id.lstView);
			lstView.setAdapter(adapterArr);
			
			lstView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Toast.makeText(getActivity(), "You've selected " + presidents[arg2], Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getActivity(), MainActivity.class);
					startActivity(i);				}
			});
		}
	}
}