package com.wheny.whenylibrary.adapter;


import com.wheny.whenylibrary.R;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */

public class SimpleLoadMoreView extends LoadMoreView {

    @Override
    protected int getLoadMoreLayout() {
        return R.layout.jxgoo_rclview_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.jxgoo_lin_loading;
    }

    @Override
    protected int getLoadEndForOnceViewId() {
        return R.id.jxgoo_lin_loadend_foronce;
    }

    @Override
    protected int getLoadEndForAllViewId() {
        return R.id.jxgoo_lin_loadend_forall;
    }

    @Override
    protected int getLoadFailedViewId() {
        return R.id.jxgoo_lin_loadend_failed;
    }

    @Override
    protected int getEmptyViewId() {
        return R.id.jxgoo_lin_empty;
    }

}
