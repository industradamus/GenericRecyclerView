package com.wispcoolwisp.genericrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener<SomeData> {

    private lateinit var adapter: GenericAdapter<SomeData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = dataAdapter
        adapter.setDiffUtilCallback(diffUtil)

        dataRecycler.layoutManager = LinearLayoutManager(this)
        dataRecycler.adapter = adapter

        adapter.update(DataGenerator.getData())
    }

    override fun onClickItem(data: SomeData) {
        when (data) {
            is Data -> Toast.makeText(this, "Click Data ${data.id}", Toast.LENGTH_LONG).show()
            is OtherData -> Toast.makeText(
                this,
                "Click Other Data${data.id}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private val dataAdapter = object : GenericAdapter<SomeData>(this) {
        override fun getLayoutId(position: Int, obj: SomeData): Int =
            when (obj) {
                is Data -> R.layout.item_data
                is OtherData -> R.layout.item_other_data
                else -> R.layout.item_data
            }

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                R.layout.item_data -> ViewHolderFactory.DataViewHolder(view)
                R.layout.item_other_data -> ViewHolderFactory.OtherDataViewHolder(view)
                else -> ViewHolderFactory.OtherDataViewHolder(view)
            }
        }
    }
}

private val diffUtil = object : GenericItemDiff<SomeData> {
    override fun isSame(
        oldItems: List<SomeData>,
        newItems: List<SomeData>,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldData = oldItems[oldItemPosition]
        val newData = newItems[newItemPosition]
        return when {
            oldData is Data && newData is Data -> return oldData.id == newData.id
            oldData is OtherData && newData is OtherData -> return oldData.id == newData.id
            else -> false
        }
    }

    override fun isSameContent(
        oldItems: List<SomeData>,
        newItems: List<SomeData>,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldData = oldItems[oldItemPosition]
        val newData = newItems[newItemPosition]

        return when {
            oldData is Data && newData is Data -> return oldData.name == newData.name && oldData.content == newData.content
            oldData is OtherData && newData is OtherData -> return oldData.name == newData.name && oldData.content == newData.content
            else -> false
        }

    }
}
