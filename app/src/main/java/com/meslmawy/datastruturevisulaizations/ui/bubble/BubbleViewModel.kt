package com.meslmawy.datastruturevisulaizations.ui.bubble

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meslmawy.datastruturevisulaizations.models.BubbleItem


class BubbleViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _unSortedData = MutableLiveData<List<BubbleItem>>()
    val unSortedData: LiveData<List<BubbleItem>>
        get() = _unSortedData

    private var mText: MutableLiveData<String>? = null
    private var reset_enabled: MutableLiveData<Boolean>? = null
    private var run_enabled: MutableLiveData<Boolean>? = null

    private var myList: ArrayList<BubbleItem> = ArrayList()
    companion object {
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1500L
        // This is the total time of the alarm
        const val ALARM_TIME = 1 * 60000L

    }

    private lateinit var alarmTimer: CountDownTimer

    init {
        _unSortedData.value = ArrayList()
        var bubbleItem = BubbleItem(1, 100, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(2, 7, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(3, 52, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(4, 6, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(5, 3, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(6, 2, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(7, 1, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(8, 9, false, false)
        myList.add(bubbleItem)
        myList.shuffle()
        iterateInitial(myList)
        mText = MutableLiveData()
        reset_enabled = MutableLiveData()
        run_enabled = MutableLiveData()

        mText!!.value = "Bubble sort is one algorithms used to sort a sequence of numbers"
        reset_enabled!!.value = false
        run_enabled!!.value = true
    }

    private fun iterateInitial(list: ArrayList<BubbleItem>) {
        list[0].iterate = true
        list[1].iterate = true
        _unSortedData.value = list
    }

    fun resetData() {
        reset_enabled?.value = false
        run_enabled?.value = true
        mText!!.value = "Bubble sort is one algorithms used to sort a sequence of numbers"
        alarmTimer.cancel()
        makeAllUnIterated()
        myList.shuffle()
        val newList = ArrayList(myList)
        newList[0].iterate = true
        newList[1].iterate = true
        _unSortedData.value = newList
    }

    fun runData() {
        reset_enabled?.value = true
        run_enabled?.value = false
        val newList2 = ArrayList(myList)
        val n: Int = myList.size
        var i = 0
        var j = 1
        alarmTimer = object : CountDownTimer(ALARM_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                if(j < n - i){
                    if (j > 1) {
                        newList2[j].iterate = true
                        newList2[j - 1].iterate = true
                        newList2[j - 2].iterate = false
                        val refreshlist = ArrayList(newList2)
                        _unSortedData.value = refreshlist
                    }
                    if (newList2[j - 1].data!! > newList2[j].data!!) {
                        //swap elements
                        mText!!.value = " ${newList2[j].data!!} is smaller than  ${newList2[j - 1].data!!} , so the numbers get swapped"
                        val x = newList2[j - 1]
                        newList2[j - 1] = newList2[j]
                        newList2[j] = x
                        myList = newList2
                        val refreshlist = ArrayList(newList2)
                        _unSortedData.value = refreshlist
                    } else
                        mText!!.value = " ${newList2[j].data!!} is greater ${newList2[j - 1].data!!} , so the numbers don't get swapped"
                    j++
                }
                else {
                    if (j == 1) {
                        newList2[j].fullySorted = true
                        newList2[j].iterate = false
                        newList2[j-1].fullySorted = true
                        newList2[j-1].iterate = false
                        val refreshlist = ArrayList(newList2)
                        _unSortedData.value = refreshlist
                        alarmTimer.cancel()
                        mText!!.value = "Sorting is complete."
                        run_enabled?.value = true
                    }
                    else {
                        mText!!.value = " ${newList2[j - 1].data!!} on the right edge is considered fully sorted "
                        newList2[j - 1].fullySorted = true
                        newList2[j - 1].iterate = false
                        newList2[j - 2].iterate = false
                        j = 1
                        i++
                        newList2[j].iterate = true
                        newList2[j - 1].iterate = true
                        val refreshlist = ArrayList(newList2)
                        _unSortedData.value = refreshlist
                    }
                }
            }
            override fun onFinish() {

            }
        }
        alarmTimer.start()
    }

    fun makeAllUnIterated() {
        myList.forEach {
            it.iterate = false
            it.fullySorted = false
        }
    }


    fun getText(): LiveData<String?>? {
        return mText
    }

    fun getResetEnabled(): LiveData<Boolean?>? {
        return reset_enabled
    }
    fun getRunEnabled(): LiveData<Boolean?>? {
        return run_enabled
    }

}