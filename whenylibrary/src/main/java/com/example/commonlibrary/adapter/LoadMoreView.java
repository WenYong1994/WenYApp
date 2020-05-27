package com.example.commonlibrary.adapter;


import com.example.commonlibrary.enums.LoadMoreState;
import com.example.commonlibrary.utils.ViewAdapterUtils;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */

public abstract class LoadMoreView {
    private LoadMoreState loadMoreState = LoadMoreState.STATE_HIDE;

    public LoadMoreState getLoadMoreState() {
        return loadMoreState;
    }

    public void setLoadMoreState(LoadMoreState loadMoreState) {
        this.loadMoreState = loadMoreState;
    }

    public void convert(ViewHolder viewHolder) {
        setLoadingLayoutVisible(viewHolder, loadMoreState == LoadMoreState.STATE_LOADING);
        setLoadEndForOnceLayoutVisible(viewHolder, loadMoreState == LoadMoreState.STATE_END_FORONCE);
        setLoadEndForAllLayoutVisible(viewHolder, loadMoreState == LoadMoreState.STATE_END_FORALL);
        setLoadFailedLayoutVisible(viewHolder, loadMoreState == LoadMoreState.STATE_FAILED);
        setEmptyLayoutVisible(viewHolder, loadMoreState == LoadMoreState.STATE_EMPTY);
    }

    private void setLoadingLayoutVisible(ViewHolder viewHolder, boolean isVisible) {
        if (getLoadingViewId() != 0)
            viewHolder.setVisible(getLoadingViewId(), ViewAdapterUtils.visibleGone(isVisible));
    }

    private void setLoadEndForOnceLayoutVisible(ViewHolder viewHolder, boolean isVisible) {
        if (getLoadEndForOnceViewId() != 0)
            viewHolder.setVisible(getLoadEndForOnceViewId(), ViewAdapterUtils.visibleGone(isVisible));
    }

    private void setLoadEndForAllLayoutVisible(ViewHolder viewHolder, boolean isVisible) {
        if (getLoadEndForAllViewId() != 0)
            viewHolder.setVisible(getLoadEndForAllViewId(), ViewAdapterUtils.visibleGone(isVisible));
    }

    private void setLoadFailedLayoutVisible(ViewHolder viewHolder, boolean isVisible) {
        if (getLoadFailedViewId() != 0)
            viewHolder.setVisible(getLoadFailedViewId(), ViewAdapterUtils.visibleGone(isVisible));
    }

    private void setEmptyLayoutVisible(ViewHolder viewHolder, boolean isVisible) {
        if (getEmptyViewId() != 0)
            viewHolder.setVisible(getEmptyViewId(), ViewAdapterUtils.visibleGone(isVisible));
    }

    protected abstract int getLoadMoreLayout();

    protected abstract int getLoadingViewId();

    protected abstract int getLoadEndForOnceViewId();

    protected abstract int getLoadEndForAllViewId();

    protected abstract int getLoadFailedViewId();

    protected abstract int getEmptyViewId();

}
