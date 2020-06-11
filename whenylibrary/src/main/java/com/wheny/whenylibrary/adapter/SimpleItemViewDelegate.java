package com.wheny.whenylibrary.adapter;

import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * author：Vinsmoke
 * e-mail：vinsmokeleigh@gmail.com
 */
public abstract class SimpleItemViewDelegate<T,VDB extends ViewDataBinding> implements ItemViewDelegate<T,VDB> {

    @Override
    public void convertPayloads(ViewHolder<VDB> holder, T t, int position, List<Object> payloads) {

    }

}
