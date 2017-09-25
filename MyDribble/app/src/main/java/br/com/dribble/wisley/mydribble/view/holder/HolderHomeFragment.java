package br.com.dribble.wisley.mydribble.view.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import br.com.dribble.wisley.mydribble.R;

/**
 * Created by Wisley on 24/09/17.
 */

public class HolderHomeFragment extends AbstractHolderFragment {

    private RecyclerView rvBody;
    public  HolderHomeFragment(View siteSelectionView) {
        super(siteSelectionView);
    }

    @Override
    protected void initializeFields() {
        this.rvBody = (RecyclerView) getField(R.id.rvBody);
    }

    @Override
    protected void initializeActions() {
        rvBody.setHasFixedSize(true);
        rvBody.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvBody.setNestedScrollingEnabled(false);
    }

    public RecyclerView getRvBody() {
        return rvBody;
    }

    public void setRvBody(RecyclerView rvBody) {
        this.rvBody = rvBody;
    }
}
