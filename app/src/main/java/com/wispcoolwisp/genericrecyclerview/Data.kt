package com.wispcoolwisp.genericrecyclerview

data class Data(
    override val id: Int = 0,
    val name: String = "",
    val content: String = ""
) : SomeData

data class OtherData(
    override val id: Int = 0,
    val name: String = "",
    val content: String = ""
) : SomeData

interface SomeData {
    val id: Int
}