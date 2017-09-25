package br.com.dribble.wisley.mydribble.view.holder;

import android.content.Context;
import android.view.View;

/**
 *
 */
public abstract class AbstractHolderFragment {

    protected View mView;

    protected AbstractHolderFragment(final View siteSelectionView) {
        super();
        this.mView = siteSelectionView;
        initializeFields();
        initializeActions();
    }

    /**
     * initialize fields and finds
     */
    protected abstract void initializeFields();

    /**
     * initialize fields and finds
     */
    protected abstract void initializeActions();

    /**
     * performing the search components from the interface implemented by any
     * activity
     *
     * @param resId
     * @return {@link View}
     */
    protected View getField(final int resId) {
        return mView.findViewById(resId);
    }

    public Context getActivity() {
        return mView.getContext();
    }
}
