package com.example.commonlibrary.adapter;

import android.animation.Animator;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.commonlibrary.R;
import com.example.commonlibrary.animation.AlphaInAnimation;
import com.example.commonlibrary.animation.BaseAnimation;
import com.example.commonlibrary.enums.LoadMoreState;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.NO_ID;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public class MultiTypeItemAdapter<E> extends RecyclerView.Adapter<ViewHolder> {
    public static final int VIEW_TYPE_HEADER = 0x10000;
    public static final int VIEW_TYPE_FOOTER = 0x10001;
    public static final int VIEW_TYPE_LOAD = 0x10002;
    protected List<E> mDatas;
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;
    protected ItemViewDelegateManager itemViewDelegateManager;
    private HeaderLayoutDelegate headerLayoutDelegate;
    private FooterLayoutDelegate footerLayoutDelegate;
    private LoadMoreLayoutDelegate loadMoreLayoutDelegate;
    //bottom loadmoreview
    private LoadMoreView loadMoreView = new SimpleLoadMoreView();
    //listener loadMore request
    private OnLoadMoreListener onLoadMoreListener;
    //listener when loadFailed click request
    private OnRetryListener onRetryListener;
    //listener when set autoLoadMore is false click request
    private OnLoadNextListener onLoadNextListener;
    private SparseArray<View> mHeaderViews = new SparseArray<>();
    private SparseArray<View> mFooterViews = new SparseArray<>();
    private boolean isAutoLoadMore = true;
    private boolean mOpenAnimationEnable = false;
    private boolean mFirstOnlyEnable = true;
    private int mLastPosition = -1;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private BaseAnimation mCustomAnimation;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();
    private boolean headerAsflow = true;
    private boolean footerAsFlow = true;
    private SpanSizeLookup mSpanSizeLookup;
    private boolean isAutoView;
    private OnScrollListener onScrollListener;
    private long itemAnimTime = 500;
    private RecyclerView recyclerView;
    //加载更多是否显示在item最后，否者第一
    private boolean isLastLoadMore = true;


    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    public MultiTypeItemAdapter(List<E> mDatas) {
        this.mDatas = mDatas;
        itemViewDelegateManager = new ItemViewDelegateManager();
        headerLayoutDelegate = new HeaderLayoutDelegate();
        footerLayoutDelegate = new FooterLayoutDelegate();
        loadMoreLayoutDelegate = new LoadMoreLayoutDelegate();
        addItemViewDelegate(VIEW_TYPE_HEADER, headerLayoutDelegate);
        addItemViewDelegate(VIEW_TYPE_FOOTER, footerLayoutDelegate);
        addItemViewDelegate(VIEW_TYPE_LOAD, loadMoreLayoutDelegate);
    }

    public MultiTypeItemAdapter() {
        this(new ArrayList<E>());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder<ViewDataBinding> viewDataBindingViewHolder = ViewHolder.create(parent, itemViewDelegateManager.getItemViewLayoutId(viewType), isAutoView);
        itemViewDelegateManager.getItemViewDelegate(viewType).injectViewModel(viewDataBindingViewHolder.getViewDataBinding());
        return viewDataBindingViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads == null || payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            itemViewDelegateManager.convertPayloads(holder, mDatas.size() > position - getHeaderViewCount() && position - getHeaderViewCount() > -1 ? mDatas.get(position - getHeaderViewCount()) : null, position, payloads);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder, position));
        if (onItemLongClickListener != null)
            holder.itemView.setOnLongClickListener(v -> {
                onItemLongClickListener.onItemLongClickListener(holder.itemView, holder, position);
                return true;
            });
        itemViewDelegateManager.convert(holder, mDatas.size() > position - getHeaderViewCount() && position - getHeaderViewCount() > -1 ? mDatas.get(position - getHeaderViewCount()) : null, position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? getDefaultDelegateItemCount() : mDatas.size() + getDefaultDelegateItemCount();
    }

    public int getItemDataCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    protected int getDefaultDelegateItemCount() {
        return getHeaderViewCount() + getFooterViewCount() + getLoadMoreViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (itemViewDelegateManager.getItemDelegateCount() > 0) {
            if (getHeaderViewCount() != 0) {
                if (position == 0) {
                    return itemViewDelegateManager.getItemViewType(null, position);
                } else {
                    return itemViewDelegateManager.getItemViewType(mDatas.size() > position - getHeaderViewCount() && position - getHeaderViewCount() > -1 ?
                            mDatas.get(position - getHeaderViewCount()) : null, position);
                }
            }
            return itemViewDelegateManager.getItemViewType(mDatas.size() > position ? mDatas.get(position) : null, position);
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager || manager instanceof SafeGridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (mSpanSizeLookup == null) {
                        return isFixedViewType(type, position) ? gridManager.getSpanCount() : 1;
                    } else {
                        return isFixedViewType(type, position) ? gridManager.getSpanCount() : mSpanSizeLookup.getSpanSize(gridManager, position);
                    }
                }

            });
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (isAutoLoadMore && onLoadMoreListener != null) {
                    LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int totalItemCount = recyclerView.getAdapter().getItemCount();
                    int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
                    int visibleItemCount = recyclerView.getChildCount();
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && mPreLoadNumber >= totalItemCount - lastVisibleItemPosition
                            && visibleItemCount > 0) {
                        if (loadMoreView.getLoadMoreState() == LoadMoreState.STATE_END_FORONCE) {
                            loadMoreView.setLoadMoreState(LoadMoreState.STATE_LOADING);
                            notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
                            onLoadMoreListener.onLoadMore();
                        }
                    }
                }
                if (onScrollListener != null)
                    onScrollListener.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (onScrollListener != null) onScrollListener.onScrolled(recyclerView, dx, dy);
            }
        });
        this.recyclerView = recyclerView;
    }

    public void setLastLoadMore(boolean lastLoadMore) {
        isLastLoadMore = lastLoadMore;
    }

    public RecyclerView getAttachedRecyclerView() {
        return recyclerView;
    }

    public void detachRecyclerView() {
        if (recyclerView != null) recyclerView.setAdapter(null);
    }

    public void setLoadMoreView(LoadMoreView loadMoreView) {
        this.loadMoreView = loadMoreView;
    }

    public void setLoadMoreViewState(LoadMoreState state) {
        loadMoreView.setLoadMoreState(state);
    }

    public <T extends MultiTypeItemAdapter<E>> T addHeaderView(View view) {
        addHeaderView(view, mHeaderViews.size());
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T addHeaderView(View view, int index) {
        View mView = mHeaderViews.get(index);
        if (mView == null) {
            mHeaderViews.put(index, view);
        } else {
            mHeaderViews.setValueAt(index, view);
        }
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T removeHeader(View view) {
        int indexOfValue = mHeaderViews.indexOfValue(view);
        if (indexOfValue != -1) {
            mHeaderViews.remove(indexOfValue);
        }
        return (T) this;
    }

    public final <T extends View> T getFooterView(int footerPosition) {
        if (mFooterViews.size() == 0) {
            return null;
        }
        return (T) mFooterViews.get(footerPosition);
    }

    public final <T extends View> T findFooterViewById(int footerPosition, @IdRes int id) {
        if (id == NO_ID) {
            return null;
        }
        if (mFooterViews.size() == 0) {
            return null;
        }
        return mFooterViews.get(footerPosition).findViewById(id);
    }

    public <T extends MultiTypeItemAdapter<E>> T addFooterView(View view) {
        addFooterView(view, mFooterViews.size());
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T addFooterView(View view, int index) {
        View mView = mFooterViews.get(index);
        if (mView == null) {
            mFooterViews.put(index, view);
        } else {
            mFooterViews.setValueAt(index, view);
        }
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T removeFooter(View view) {
        int indexOfValue = mFooterViews.indexOfValue(view);
        if (indexOfValue != -1) {
            mFooterViews.remove(indexOfValue);
        }
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T removeAllFooter() {
        mFooterViews.clear();
        return (T) this;
    }

    public int getHeaderViewCount() {
        int count = mHeaderViews.size() > 0 ? 1 : 0;
        return count;
    }

    protected int getFooterViewCount() {
        int count = mFooterViews.size() > 0 ? 1 : 0;
        return count;
    }

    protected int getLoadMoreViewCount() {
        return getLoadMoreViewState() == LoadMoreState.STATE_HIDE ? 0 : 1;
    }

    public LoadMoreState getLoadMoreViewState() {
        return loadMoreView.getLoadMoreState();
    }

    public <T extends MultiTypeItemAdapter<E>> T setAutoLoadMore(boolean autoLoadMore) {
        isAutoLoadMore = autoLoadMore;
        return (T) this;
    }

    private boolean isFixedViewType(int type, int position) {
        if (type == itemViewDelegateManager.getItemViewType(headerLayoutDelegate) &&
                isHeaderAsflow()) {
            return true;
        } else if (type == itemViewDelegateManager.getItemViewType(footerLayoutDelegate) &&
                isFooterAsFlow()) {
            return true;
        } else if (type == itemViewDelegateManager.getItemViewType(loadMoreLayoutDelegate) &&
                loadMoreLayoutDelegate.isShow(position)) {
            return true;
        } else {
            return false;
        }
    }

    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder
                    .itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    public void setNotDoAnimationCount(int count) {
        mLastPosition = count;
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        if (type == itemViewDelegateManager.getItemViewType(headerLayoutDelegate) ||
                type == itemViewDelegateManager.getItemViewType(footerLayoutDelegate) ||
                type == itemViewDelegateManager.getItemViewType(loadMoreLayoutDelegate)) {
            setFullSpan(holder);
        } else {
            addAnimation(holder);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onViewRecycled(holder);
    }

    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (!mFirstOnlyEnable || holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation;
                if (mCustomAnimation != null) {
                    animation = mCustomAnimation;
                } else {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim);
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    protected void startAnim(Animator anim) {
        anim.setInterpolator(mInterpolator);
        anim.setDuration(mDuration).start();
    }

    public void setAnimationDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setCustomAnimation(BaseAnimation animation) {
        mCustomAnimation = animation;
    }

    public void openAnimation() {
        mOpenAnimationEnable = true;
    }

    public void closeAnimation() {
        mOpenAnimationEnable = false;
    }

    private int mPreLoadNumber = 1;

    @Deprecated
    public void setAutoLoadMoreSize(int preLoadNumber) {
        setPreLoadNumber(preLoadNumber);
    }

    public void setPreLoadNumber(int preLoadNumber) {
        if (preLoadNumber > 1) {
            mPreLoadNumber = preLoadNumber;
        }
    }

    public void setHeaderAsflow(boolean headerAsflow) {
        this.headerAsflow = headerAsflow;
    }

    public void setFooterAsFlow(boolean footerAsFlow) {
        this.footerAsFlow = footerAsFlow;
    }

    public boolean isHeaderAsflow() {
        return headerAsflow;
    }

    public boolean isFooterAsFlow() {
        return footerAsFlow;
    }

    public void setmSpanSizeLookup(SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }

    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int position);
    }

    public void isFirstOnly(boolean firstOnly) {
        this.mFirstOnlyEnable = firstOnly;
    }

    public List<E> getDataList() {
        return mDatas;
    }

    public <VDB extends ViewDataBinding> MultiTypeItemAdapter<E> addItemViewDelegate(ItemViewDelegate<E,VDB> itemViewDelegate) {
        itemViewDelegateManager.addItemDelegate(itemViewDelegate);
        return this;
    }

    public <VDB extends ViewDataBinding> MultiTypeItemAdapter<E> addItemViewDelegate(int viewType, ItemViewDelegate<E,VDB> itemViewDelegate) {
        itemViewDelegateManager.addItemDelegate(viewType, itemViewDelegate);
        return  this;
    }

    public void showLoading() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_LOADING);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public void showLoadEndForOnce() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_END_FORONCE);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public void showLoadEndForAll() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_END_FORALL);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public void showLoadFailed() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_FAILED);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public void showEmpty() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_EMPTY);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public void hideLoadView() {
        loadMoreView.setLoadMoreState(LoadMoreState.STATE_HIDE);
        notifyItemChanged(loadMoreLayoutDelegate.getShowPosition());
    }

    public <T extends MultiTypeItemAdapter<E>> T setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T setOnLoadMoreListener(MultiTypeItemAdapter.OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T setOnRetryListener(MultiTypeItemAdapter.OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T setOnLoadNextListener(MultiTypeItemAdapter.OnLoadNextListener onLoadNextListener) {
        this.onLoadNextListener = onLoadNextListener;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T add(E data) {
        mDatas.add(data);
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T addAll(List<E> datas) {
        mDatas.addAll(datas);
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T setDataList(List<E> datas) {
        mDatas = datas;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T clearn() {
        mDatas.clear();
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter<E>> T remove(E data) {
        int indexOf = mDatas.indexOf(data);
        if (indexOf == -1) return (T) this;
        remove(indexOf);
        return (T) this;
    }

    public interface RemoveCallBack {
        void removeEnd();
    }

    public synchronized void remove(int index) {
        this.remove(index, null);
    }

    public synchronized void remove(final int index, final RemoveCallBack removeCallBack) {
        mHandler.post(() -> {
            notifyItemRemoved(index);
        });
        mHandler.postDelayed(() -> {
            notifyItemRangeChanged(index, getItemCount());
            if (removeCallBack != null) removeCallBack.removeEnd();
        }, itemAnimTime);
    }

    public interface MovedCallack {
        void movedEnd();
    }

    public synchronized void moved(int fromPosition, int toPosition) {
        this.moved(fromPosition, toPosition, null);
    }

    public synchronized void moved(final int fromPosition, final int toPosition, final MovedCallack movedCallack) {
        mHandler.post(() -> {
            E temp = mDatas.get(fromPosition);
            mDatas.remove(fromPosition);
            mDatas.add(toPosition, temp);
            notifyItemMoved(fromPosition, toPosition);
        });
        mHandler.postDelayed(() -> {
            notifyItemChanged(toPosition);
            if (movedCallack != null) movedCallack.movedEnd();
        }, itemAnimTime);
    }

    public <T extends MultiTypeItemAdapter> T setAutoView(boolean autoView) {
        isAutoView = autoView;
        return (T) this;
    }

    public <T extends MultiTypeItemAdapter> T setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
        return (T) this;
    }

    class HeaderLayoutDelegate<VDB extends ViewDataBinding> implements ItemViewDelegate<E,VDB> {
        private ViewHolder viewHolder;

        public HeaderLayoutDelegate() {
        }

        public void setViewHolder(ViewHolder<VDB> viewHolder) {
            this.viewHolder = viewHolder;
        }

        public void addHeadView(View view) {
//            addHeadView(view, 0);
            viewHolder.addView(R.id.rclview_headerlayout, view);
        }

        public void addHeadView(View view, int index) {
            viewHolder.addView(R.id.rclview_headerlayout, view, index);
        }

        public void removeView(int index) {
            viewHolder.removeViewAt(R.id.rclview_headerlayout, index);
        }

        @Override
        public int getItemViewLayoutId() {
            return R.layout.jxgoo_rclview_headerlayout;
        }

        @Override
        public boolean isForViewType(E item, int position) {
            return isShow(position);
        }

        @Override
        public void injectViewModel(VDB viewDataBinding) {

        }

        @Override
        public void convert(ViewHolder<VDB> holder, E view, int position) {
            if (mHeaderViews.size() > 0) {
                holder.itemView.setVisibility(View.VISIBLE);
                setViewHolder(holder);
                for (int i = 0; i < mHeaderViews.size(); i++) {
                    if (mHeaderViews.get(i).getParent() != null) {
                        holder.removeView(R.id.rclview_headerlayout, mHeaderViews.get(i));
                    }
                    View headerView = mHeaderViews.get(i);
                    ViewGroup.LayoutParams ftLp = headerView.getLayoutParams();
                    if (ftLp != null) {
                        LinearLayout parent = (LinearLayout) holder.getView(R.id.rclview_headerlayout);
                        ViewGroup.LayoutParams parentLp = parent.getLayoutParams();
                        parentLp.width = ftLp.width;
                        parentLp.height = ftLp.height;
                    }
                    addHeadView(headerView);
                }
            } else {
                holder.itemView.setVisibility(View.GONE);
            }
        }

        @Override
        public void convertPayloads(ViewHolder<VDB> holder, E e, int position, List<Object> payloads) {

        }

        protected boolean isShow(int position) {
            return position == 0 && getHeaderViewCount() > 0;
        }

        protected int getShowPosition() {
            return 0;
        }
    }

    class FooterLayoutDelegate<VDB extends ViewDataBinding> implements ItemViewDelegate<E,VDB> {
        private ViewHolder viewHolder;

        public void setViewHolder(ViewHolder<VDB> viewHolder) {
            this.viewHolder = viewHolder;
        }

        public void addFooterView(View view) {
            addFooterView(view, ((ViewGroup) viewHolder.itemView).getChildCount());
        }

        public void addFooterView(View view, int index) {
            viewHolder.addView(R.id.rclview_footerLayout, view, index);
        }

        @Override
        public int getItemViewLayoutId() {
            return R.layout.jxgoo_rclview_footerlayout;
        }

        @Override
        public boolean isForViewType(E item, int position) {
            return isShow(position);
        }

        @Override
        public void injectViewModel(VDB viewDataBinding) {

        }

        @Override
        public void convert(ViewHolder<VDB> holder, E view, int position) {
            if (mFooterViews.size() > 0) {
                holder.itemView.setVisibility(View.VISIBLE);
                setViewHolder(holder);
                for (int i = 0; i < mFooterViews.size(); i++) {
                    if (mFooterViews.get(i).getParent() != null) {
                        holder.removeView(R.id.rclview_footerLayout, mFooterViews.get(i));
                    }
                    View footerView = mFooterViews.get(i);
                    ViewGroup.LayoutParams ftLp = footerView.getLayoutParams();
                    if (ftLp != null) {
                        LinearLayout parent = (LinearLayout) holder.getView(R.id.rclview_footerLayout);
                        ViewGroup.LayoutParams parentLp = parent.getLayoutParams();
                        parentLp.width = ftLp.width;
                        parentLp.height = ftLp.height;
                    }
                    addFooterView(footerView);
                }
            } else {
                holder.itemView.setVisibility(View.GONE);
            }
        }

        @Override
        public void convertPayloads(ViewHolder<VDB> holder, E e, int position, List<Object> payloads) {

        }

        protected boolean isShow(int position) {
            return position == getItemCount() - 1 - getLoadMoreViewCount() && getFooterViewCount() > 0;
        }

        protected int getShowPosition() {
            return getItemCount() - 1 - getLoadMoreViewCount();
        }
    }

    class LoadMoreLayoutDelegate<VDB extends ViewDataBinding> implements ItemViewDelegate<E,VDB> {

        @Override
        public int getItemViewLayoutId() {
            return loadMoreView.getLoadMoreLayout();
        }

        @Override
        public boolean isForViewType(E item, int position) {
            return isShow(position);
        }

        @Override
        public void injectViewModel(VDB viewDataBinding) {

        }

        @Override
        public void convert(ViewHolder<VDB> holder, E view, int position) {
            loadMoreView.convert(holder);
            if (loadMoreView.getLoadMoreState() == LoadMoreState.STATE_EMPTY) {
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            if (loadMoreView.getLoadMoreState() == LoadMoreState.STATE_FAILED
                    && onRetryListener != null) {
                holder.itemView.setOnClickListener(v -> onRetryListener.onRetry());
            } else if (loadMoreView.getLoadMoreState() == LoadMoreState.STATE_END_FORONCE
                    && onLoadNextListener != null) {
                holder.itemView.setOnClickListener(v -> onLoadNextListener.onLoadNext());
            } else {
                holder.itemView.setOnClickListener(null);
            }
        }

        @Override
        public void convertPayloads(ViewHolder<VDB> holder, E e, int position, List<Object> payloads) {

        }

        public boolean isShow(int position) {
            return position == getShowPosition() && loadMoreView.getLoadMoreState() != LoadMoreState.STATE_HIDE;
        }

        public int getShowPosition() {
            return getItemCount() - 1;
        }
    }

    public boolean isAutoView() {
        return isAutoView;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(View view, RecyclerView.ViewHolder holder, int position);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnRetryListener {
        void onRetry();
    }

    public interface OnLoadNextListener {
        void onLoadNext();
    }

    public abstract static class OnScrollListener {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    }
}
