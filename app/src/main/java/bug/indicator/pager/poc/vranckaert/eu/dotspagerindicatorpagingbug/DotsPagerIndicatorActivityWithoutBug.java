package bug.indicator.pager.poc.vranckaert.eu.dotspagerindicatorpagingbug;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.wearable.view.FixedDotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.Toast;

public class DotsPagerIndicatorActivityWithoutBug extends Activity {
    private View mContainer;
    private GridViewPager mViewPager;
    private FixedDotsPageIndicator mViewPagerIndicator;
    private MyViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fixed);

        mViewPager = (GridViewPager) findViewById(R.id.view_pager);
        mViewPagerIndicator = (FixedDotsPageIndicator) findViewById(R.id.view_pager_indicator);

        mAdapter = new MyViewPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);
        mAdapter.setNumberOfPages(3);

        mViewPagerIndicator.setPager(mViewPager);

        mContainer = findViewById(R.id.container);

        // Scenario: When coming from a notification I want to go directly to page 1, showing cached data
        // I also start (on startup of the activity) a background call to load updated data from the phone, if that data
        // comes in, the number of pages may change (although in this example the number stays te same, that might
        // happen as well), so we set the number of pages on the viewpager on result.
        mContainer.post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(0, 1);
            }
        });
        doUpdateCall();
    }

    private void doUpdateCall() {
        updateCall.execute();
    }

    private void onUpdateCallReturns() {
        mAdapter.setNumberOfPages(3);
        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        // At this moment (if we did not scroll in the UI) the user is still on page 2 (index 1) of the viewpager.
        // However the view pager indicator (dots) show that we are at page 1!
    }

    private AsyncTask<Void, Void, Void> updateCall = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(3000L);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            DotsPagerIndicatorActivityWithoutBug.this.onUpdateCallReturns();
        }
    };
}
