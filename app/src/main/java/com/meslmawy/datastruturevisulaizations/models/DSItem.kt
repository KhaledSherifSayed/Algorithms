package com.meslmawy.datastruturevisulaizations.models

data class DSItem(
    val id: Int?=null,
    val name: String? = null,
    val description : String?=null
)

data class BubbleItem(
    val id : Int?=null,
    val data: Int?=null,
    var iterate : Boolean?=null,
    var fullySorted : Boolean?=null
)