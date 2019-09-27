package com.wispcoolwisp.genericrecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data.view.*

object ViewHolderFactory {

    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_data -> DataViewHolder(view)
            R.layout.item_other_data -> OtherDataViewHolder(view)
            else -> throw Exception("Wrong view type")
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<Data> {
        override fun bind(data: Data, listener: OnItemClickListener<Data>?) {
            itemView.apply {
                nameTextView.text = data.name
                setOnClickListener {
                    listener?.onClickItem(data)
                }
            }
        }
    }

    class OtherDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<OtherData> {
        override fun bind(data: OtherData, listener: OnItemClickListener<OtherData>?) {
            itemView.apply {
                nameTextView.text = data.name
                setOnClickListener { listener?.onClickItem(data) }
            }
        }
    }
}