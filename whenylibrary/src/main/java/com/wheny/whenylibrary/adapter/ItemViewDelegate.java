package com.wheny.whenylibrary.adapter;

import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public interface ItemViewDelegate<T,VDB extends ViewDataBinding> {
    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void injectViewModel(VDB viewDataBinding);

    void convert(ViewHolder<VDB> holder, T t, int position);

    void convertPayloads(ViewHolder<VDB> holder, T t, int position, List<Object> payloads);

}
