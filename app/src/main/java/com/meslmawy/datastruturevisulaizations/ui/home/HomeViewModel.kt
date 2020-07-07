package com.meslmawy.datastruturevisulaizations.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meslmawy.datastruturevisulaizations.models.DSItem

class HomeViewModel : ViewModel() {


    private var _sortdsItems = MutableLiveData<List<DSItem>>()
    val sortdsItems: LiveData<List<DSItem>>
        get() = _sortdsItems


    private var myList: ArrayList<DSItem> = ArrayList()

    init {
        _sortdsItems.value = ArrayList()
        val bubbleItem =  DSItem(1,"BubbleSort","Bubble sort is a sorting algorithm that works by repeatedly stepping through lists that need to be sorted, comparing each pair of adjacent items and swapping them if they are in the wrong order. This passing procedure is repeated until no swaps are required, indicating that the list is sorted. Bubble sort gets its name because smaller elements bubble toward the top of the list.")
        myList.add(bubbleItem)
        _sortdsItems.value = myList
    }
    private val _navigateToSearch = MutableLiveData<Boolean>()
    val navigateToSearch: LiveData<Boolean>
        get() = _navigateToSearch

    fun onNavigatedToSearch() {
        _navigateToSearch.value = false
    }
}