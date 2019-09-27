package com.wispcoolwisp.genericrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), OnItemClickListener<Data> {

    private lateinit var adapter: GenericAdapter<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = dataAdapter
        adapter.setDiffUtilCallback(diffUtil)

        //Дальнейшая реализация RecyclerView стандартная
    }

    override fun onClickItem(data: Data) {
        Log.d("TAG", data.toString())
    }

    private val dataAdapter = object : GenericAdapter<Data>() {
        override fun getLayoutId(position: Int, obj: Data): Int = R.layout.item_data
    }

    private val diffUtil = object : GenericItemDiff<Data>{
        override fun isSame(
            oldItems: List<Data>,
            newItems: List<Data>,
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            val oldData = oldItems[oldItemPosition]
            val newData = newItems[newItemPosition]
            return oldData.id == newData.id
        }

        override fun isSameContent(
            oldItems: List<Data>,
            newItems: List<Data>,
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            val oldData = oldItems[oldItemPosition]
            val newData = newItems[newItemPosition]
            return oldData.name == newData.name && oldData.content == newData.content
        }
    }
}
