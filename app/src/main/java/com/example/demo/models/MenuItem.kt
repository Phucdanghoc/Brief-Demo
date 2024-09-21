package com.example.demo.models

data class MenuItem(
    val title: String,
    val children: List<ChildItem> = emptyList(),
    var isExpanded: Boolean = false
)

data class ChildItem(
    val  title : String,
    val linkVideo : String
)