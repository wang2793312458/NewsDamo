package com.feicui.news.util;

/**
 * Created by AAAAA on 2016/6/13.
 */
public interface OnRefreshListener {
    /**
     * 下拉刷新
     */
    void onDownPullRefresh();

    /**
     * 上拉加载更多
     */
    void onLoadingMore();
}
