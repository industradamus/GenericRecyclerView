package com.wispcoolwisp.genericrecyclerview

object DataGenerator {

    fun getData(): List<SomeData> {
        val dataList = mutableListOf<SomeData>()
        for (i in 0..20) {
            if (i % 2 == 0) {
                dataList.add(Data(i, "Name $i", "Content $i"))
            } else {
                dataList.add(OtherData(i, "Name $i", "Content $i"))
            }
        }
        return dataList
    }
}