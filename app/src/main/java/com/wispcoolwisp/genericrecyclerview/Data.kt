package com.wispcoolwisp.genericrecyclerview

data class Data(
    val id: Int = 0,
    val name: String = "",
    val content: String = ""
) : SomeData

data class OtherData(
    val id: Int = 0,
    val name: String = "",
    val content: String = ""
) : SomeData

interface SomeData