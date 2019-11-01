package com.wispcoolwisp.genericrecyclerview.recycler.viewholders

import android.view.View
import androidx.annotation.LayoutRes
import com.wispcoolwisp.genericrecyclerview.Data
import com.wispcoolwisp.genericrecyclerview.OtherData
import com.wispcoolwisp.genericrecyclerview.R
import com.wispcoolwisp.genericrecyclerview.recycler.GenericAdapter
import kotlinx.android.synthetic.main.item_data.view.*

object ViewHolderFactory {

    fun create(view: View, @LayoutRes viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.item_data -> DataViewHolder(view)
            R.layout.item_other_data -> OtherDataViewHolder(view)
            else -> throw Exception("Wrong view type")
        }
    }

    class DataViewHolder(itemView: View) : BaseViewHolder<Data>(itemView),
        GenericAdapter.Binder<Data> {

        override fun bind(
            data: GenericAdapter.DataWrapper<Data>,
            listener: GenericAdapter.OnItemClickListener<Data>?
        ) {
            super.bind(data, listener)
            itemView.apply {
                nameTextView.text = data.item.name
                setOnClickListener { listener?.onClickItem(data.item) }
            }
        }
    }

    class OtherDataViewHolder(itemView: View) : BaseViewHolder<OtherData>(itemView),
        GenericAdapter.Binder<OtherData> {

        override fun bind(
            data: GenericAdapter.DataWrapper<OtherData>,
            listener: GenericAdapter.OnItemClickListener<OtherData>?
        ) {
            super.bind(data, listener)
            itemView.apply {
                nameTextView.text = data.item.name
                setOnClickListener { listener?.onClickItem(data.item) }
            }
        }
    }
}