package com.gmail.ariapar.tedxoxford;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Handler handler;

    protected Fragment fragment = null;
    //private boolean started = false;
    private ArrayList<Fragment> frList = new ArrayList<Fragment>();

    private static int AD_TIME_OUT = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        handler = new Handler();

        handler.postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, AdScreen.class);
                startActivity(i);
            }
        }, AD_TIME_OUT);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //    FragmentManager fragmentManager = getSupportFragmentManager();
        //    fragmentManager.beginTransaction()
        //            .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
        //            .commit();
        onSectionAttached(position);
    }

    private void initFrList(){
        //Initialize and add fragments to list
        frList.add(new NewsFragment());
        frList.add(new SpeakersFragment());

        Bundle bundle = new Bundle();
        String url = "http://tedxoxford.co.uk/api/get_page/?id=955&include=title,title_plain,content,id";
        bundle.putString("url", url);
        Fragment pfragment = new PageFragment();
        pfragment.setArguments(bundle);
        frList.add(pfragment);

        frList.add(new TweetsFragment());

        Bundle bundle2 = new Bundle();
        String aboutUs = "http://tedxoxford.co.uk/api/get_page/?id=1132&include=title,title_plain,content,id";
        bundle2.putString("url", aboutUs);
        Fragment pfragment2 = new PageFragment();
        pfragment2.setArguments(bundle2);
        frList.add(pfragment2);

        frList.add(new ContactUsFragment());

        Bundle bundle3 = new Bundle();
        String neptune = "http://tedxoxford.co.uk/api/get_page/?id=933&include=title,title_plain,content,id";
        bundle3.putString("url", neptune);
        Fragment pfragment3 = new PageFragment();
        pfragment3.setArguments(bundle3);
        frList.add(pfragment3);
    }

    public void onSectionAttached(int number) {
        Bundle bundle = new Bundle();
        if (frList.size()== 0 ) initFrList();
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
            case 3:
                mTitle = getString(R.string.title_section4);
                break;
            case 4:
                mTitle = getString(R.string.title_section5);
                break;
            case 5:
                mTitle = getString(R.string.title_section6);
                break;
            case 6:
                mTitle = getString(R.string.title_section7);
                break;
        }

        fragment = frList.get(number);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh){
            fragment.onOptionsItemSelected(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            handler.removeCallbacksAndMessages(null);
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
