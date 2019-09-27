package com.wispcoolwisp.genericrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var itemList = mutableListOf<T>()
    private var itemClickListener: OnItemClickListener<T>? = null
    private var diffUtil: GenericItemDiff<T>? = null

    constructor()

    constructor(listener: OnItemClickListener<T>) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
            , viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        @Suppress("UNCHECKED_CAST")
        (holder as Binder<T>).bind(itemList[position], itemClickListener)
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = getLayoutId(position, itemList[position])

    fun setDiffUtilCallback(diffUtilImpl: GenericItemDiff<T>) {
        diffUtil = diffUtilImpl
    }

    fun update(items: List<T>) {
        if (diffUtil != null) {
            val result = DiffUtil.calculateDiff(GenericDiffUtil(itemList, items, diffUtil!!))

            itemList.clear()
            itemList.addAll(items)
            result.dispatchUpdatesTo(this)
        } else {
            itemList = items.toMutableList()
            notifyDataSetChanged()
        }
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    internal interface Binder<T> {
        fun bind(data: T, listener: OnItemClickListener<T>?)
    }
}