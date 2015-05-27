package bug.indicator.pager.poc.vranckaert.eu.dotspagerindicatorpagingbug;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Date: 27/05/15
 * Time: 11:56
 *
 * @author Dirk Vranckaert
 */
public class TestView extends AbstractViewHolder {
    private TextView mTest;

    public TestView(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent, R.layout.test_view);

        mTest = findViewById(R.id.test);
    }

    public void setPage(int page) {
        mTest.setText("Current page " + page);
    }
}
