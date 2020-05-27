package com.example.commonlibrary.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import androidx.databinding.ViewDataBinding;

import com.example.commonlibrary.R;
import com.example.commonlibrary.view.swipemenu.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */

public abstract class SlideAdapter<E> extends MultiTypeItemAdapter<E> {
    private ContentLayoutDelegate contentLayoutDelegate;

    public abstract void convert(ViewHolder holder, SwipeMenuLayout swipeMenuLayout, E t, int position);

    protected void convertPayloads(ViewHolder holder, E e, int position, List<Object> payloads) {

    }

    public SlideAdapter(int contentLayoutId, int menuLayoutId, boolean isLeftSwipe) {
        this(new ArrayList<E>(), contentLayoutId, menuLayoutId, isLeftSwipe);
    }

    public SlideAdapter(List<E> mDatas, int contentLayoutId, int menuLayoutId, boolean isLeftSwipe) {
        super(mDatas);
        contentLayoutDelegate = new ContentLayoutDelegate(contentLayoutId, menuLayoutId, isLeftSwipe);
        addItemViewDelegate(contentLayoutDelegate);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == itemViewDelegateManager.getItemViewType(contentLayoutDelegate)) {
            ViewHolder holder = ViewHolder.create(parent, itemViewDelegateManager.getItemViewLayoutId(viewType), isAutoView());
            SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) holder.itemView;
            swipeMenuLayout.setLeftSwipe(contentLayoutDelegate.isLeftSwipe);
            FrameLayout container = (FrameLayout) holder.getView(R.id.jxgoo_afl_container_item);
            FrameLayout menusContainer = (FrameLayout) holder.getView(R.id.jxgoo_afl_menu_item);
            container.addView(LayoutInflater.from(holder.itemView.getContext()).inflate(contentLayoutDelegate.getContentLayoutId(), container, false));
            menusContainer.addView(LayoutInflater.from(holder.itemView.getContext()).inflate(contentLayoutDelegate.getMenuLayoutId(), menusContainer, false));
            return holder;
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    class ContentLayoutDelegate<VDB extends ViewDataBinding> implements ItemViewDelegate<E,VDB> {

        private int contentLayoutId;
        private int menuLayoutId;
        private boolean isLeftSwipe;

        public ContentLayoutDelegate(int contentLayoutId, int menuLayoutId, boolean isLeftSwipe) {
            this.contentLayoutId = contentLayoutId;
            this.menuLayoutId = menuLayoutId;
            this.isLeftSwipe = isLeftSwipe;
        }

        @Override
        public int getItemViewLayoutId() {
            return R.layout.jxgoo_rclview_slide;
        }

        public int getContentLayoutId() {
            return contentLayoutId;
        }

        public int getMenuLayoutId() {
            return menuLayoutId;
        }

        @Override
        public boolean isForViewType(E item, int position) {
            return isShow(position);
        }

        @Override
        public void injectViewModel(VDB viewDataBinding) {

        }

        @Override
        public void convert(ViewHolder<VDB> holder, E t, int position) {
            SlideAdapter.this.convert(holder, (SwipeMenuLayout) holder.itemView, mDatas.get(getContentPosition(position)), getContentPosition(position));
        }

        @Override
        public void convertPayloads(ViewHolder<VDB> holder, E e, int position, List<Object> payloads) {
            SlideAdapter.this.convertPayloads(holder, e, position, payloads);
        }

        protected boolean isShow(int position) {
            return position >= getHeaderViewCount() && getContentPosition(position) < mDatas.size();
        }

        protected int getContentPosition(int currPosition) {
            return currPosition - getHeaderViewCount();
        }
    }
}
