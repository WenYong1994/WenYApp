package com.wheny.whenylibrary.adapter;

import androidx.collection.SparseArrayCompat;
import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public class ItemViewDelegateManager<T,VDB extends ViewDataBinding> {
    private SparseArrayCompat<ItemViewDelegate<T,VDB>> mItemDelegates = new SparseArrayCompat<ItemViewDelegate<T,VDB>>();

    public int getItemDelegateCount() {
        return mItemDelegates.size();
    }

    public ItemViewDelegateManager<T,VDB> addItemDelegate(ItemViewDelegate<T,VDB> itemViewDelegate) {
        if (itemViewDelegate != null) {
            mItemDelegates.put(getItemDelegateCount(), itemViewDelegate);
        }
        return this;
    }

    public ItemViewDelegateManager<T,VDB> addItemDelegate(int viewType, ItemViewDelegate<T,VDB> itemViewDelegate) {
        if (mItemDelegates.get(viewType) != null) {
            throw new IllegalArgumentException("this itemDelegate is added " +
                    "viewType = " + viewType +
                    "itemViewDelegate = " + mItemDelegates.get(viewType));
        }
        mItemDelegates.put(viewType, itemViewDelegate);
        return this;
    }

    public ItemViewDelegateManager<T,VDB> removeItemDelegate(ItemViewDelegate<T,VDB> itemViewDelegate) {
        if (itemViewDelegate == null) {
            throw new NullPointerException("itemViewDelegate is null");
        }
        int indexOfValue = mItemDelegates.indexOfValue(itemViewDelegate);
        if (indexOfValue >= 0) {
            mItemDelegates.removeAt(indexOfValue);
        }
        return this;
    }

    public ItemViewDelegateManager<T,VDB> removeItemDelegate(int viewType) {
        int indexOfKey = mItemDelegates.indexOfKey(viewType);
        if (indexOfKey >= 0) {
            mItemDelegates.remove(indexOfKey);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int itemDelegateCount = getItemDelegateCount();
        for (int i = 0; i < itemDelegateCount; i++) {
            if (mItemDelegates.valueAt(i).isForViewType(item, position)) {
                return mItemDelegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }


    public void convert(ViewHolder<VDB> holder, T item, int position) {
        int itemDelegateCount = getItemDelegateCount();
        for (int i = 0; i < itemDelegateCount; i++) {
            ItemViewDelegate<T,VDB> delegate = mItemDelegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convertPayloads(ViewHolder<VDB> holder, T item, int position, List<Object> payloads) {
        int itemDelegateCount = getItemDelegateCount();
        for (int i = 0; i < itemDelegateCount; i++) {
            ItemViewDelegate<T,VDB> delegate = mItemDelegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                delegate.convertPayloads(holder, item, position, payloads);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public ItemViewDelegate<T,VDB> getItemViewDelegate(int viewType) {
        return mItemDelegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType) {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate<T,VDB> itemViewDelegate) {
        int index = mItemDelegates.indexOfValue(itemViewDelegate);
        if (index != -1) {
            return mItemDelegates.keyAt(index);
        }
        return -1;
    }


}
