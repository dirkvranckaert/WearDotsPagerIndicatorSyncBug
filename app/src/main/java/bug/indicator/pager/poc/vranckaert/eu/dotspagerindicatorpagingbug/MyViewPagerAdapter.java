package bug.indicator.pager.poc.vranckaert.eu.dotspagerindicatorpagingbug;

import android.content.Context;
import android.support.wearable.view.GridPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Date: 20/10/14
 * Time: 16:32
 *
 * @author Dirk Vranckaert
 */
public class MyViewPagerAdapter extends GridPagerAdapter {
    private final LayoutInflater mLayoutInflater;
    private int mNumberOfPages = 1;

    public MyViewPagerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public final int getRowCount() {
        return 1;
    }

    @Override
    public final int getColumnCount(int row) {
        return mNumberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        mNumberOfPages = numberOfPages;
        notifyDataSetChanged();
    }

    @Override
    protected final Object instantiateItem(ViewGroup container, int row, int col) {
        AbstractViewHolder view = instantiateItemViewHolder(mLayoutInflater, container, col);
        container.addView(view.getView());
        return view;
    }

    private AbstractViewHolder instantiateItemViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int position) {
        TestView view = new TestView(layoutInflater, parent);
        view.setPage(position);
        return view;
    }

    @Override
    protected final void destroyItem(ViewGroup container, int row, int col, Object object) {
        AbstractViewHolder viewHolder = (AbstractViewHolder) object;
        container.removeView(viewHolder.getView());
    }

    @Override
    public final boolean isViewFromObject(View view, Object object) {
        AbstractViewHolder viewHolder = (AbstractViewHolder) object;
        return view.equals(viewHolder.getView());
    }
}
