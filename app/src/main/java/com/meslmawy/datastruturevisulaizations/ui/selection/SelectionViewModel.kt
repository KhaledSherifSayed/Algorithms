package com.meslmawy.datastruturevisulaizations.ui.selection

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meslmawy.datastruturevisulaizations.models.BubbleItem

class SelectionViewModel : ViewModel() {

    private lateinit var alarmTimer: CountDownTimer
    private var _unSortedData = MutableLiveData<List<BubbleItem>>()
    val unSortedData: LiveData<List<BubbleItem>>
        get() = _unSortedData

    private var mText: MutableLiveData<String>? = null
    private var reset_enabled: MutableLiveData<Boolean>? = null
    private var run_enabled: MutableLiveData<Boolean>? = null
    private var step_enabled: MutableLiveData<Boolean>? = null

    private var myList: ArrayList<BubbleItem> = ArrayList()
    private var newList2: ArrayList<BubbleItem> = ArrayList()
    var listSize: Int? = null

    private var iterate1 = 0
    private var min_idx = 0
    private var iterate2 = 1

    companion object {
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1500L
        // This is the total time of the alarm
        var ALARM_Run_TIME = 1 * 60000L
        var ALARM_Step_TIME = 1 * 1500L
    }


    init {
        mText = MutableLiveData()
        reset_enabled = MutableLiveData()
        run_enabled = MutableLiveData()
        step_enabled = MutableLiveData()
        _unSortedData.value = ArrayList()
        var bubbleItem = BubbleItem(1, 1, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(2, 7, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(3, 52, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(4, 6, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(5, 7, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(6, 2, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(7, 100, false, false)
        myList.add(bubbleItem)
        bubbleItem = BubbleItem(8, 9, false, false)
        myList.add(bubbleItem)
        //myList.shuffle()
        iterateInitial(myList)
        newList2 = ArrayList(myList)
        listSize = newList2.size
        mText!!.value = "Selection sort is one algorithms used to sort a sequence of numbers"
        reset_enabled!!.value = false
        run_enabled!!.value = true
    }

    private fun iterateInitial(list: ArrayList<BubbleItem>) {
        _unSortedData.value = list
    }

    fun resetData() {
        iterate1 = 0
        min_idx = 0
        iterate2 = 1
        reset_enabled?.value = false
        run_enabled?.value = true
        step_enabled?.value = true
        mText!!.value = "Selection sort is one algorithms used to sort a sequence of numbers"
        alarmTimer.cancel()
        makeAllUnIterated()
        myList.shuffle()
        newList2 = ArrayList(myList)
        _unSortedData.value = newList2
    }


    fun runData() {
        reset_enabled?.value = true
        run_enabled?.value = false
        step_enabled?.value = false
        alarmTimer = object : CountDownTimer(ALARM_Run_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                sortFunction()
            }

            override fun onFinish() {
            }
        }
        alarmTimer.start()
    }

    fun stepData() {
        reset_enabled?.value = true
        run_enabled?.value = true
        alarmTimer = object : CountDownTimer(ALARM_Step_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                sortFunction()
            }

            override fun onFinish() {

            }
        }
        alarmTimer.start()
    }

    fun sortFunction() {
        if (iterate1 < listSize!!) {
            if(iterate1 == 0 && iterate2 == 1)
                mText!!.value = "Using linear Search to get smallest value in unsorted Array"
            if (iterate2 < listSize!!) {
                newList2[iterate2].iterate = true
                if (newList2[iterate2].data!! < newList2[min_idx].data!!) {
                    min_idx = iterate2
                    mText!!.value = "${newList2[min_idx].data!!} is the smallest number in unsorted array comparable to ${newList2[iterate1].data}"
                }
                val refreshlist = ArrayList(newList2)
                _unSortedData.value = refreshlist
                iterate2++
            } else {
                val x = newList2[min_idx]
                if (x != newList2[iterate1] && x.data!! < newList2[iterate1].data!!) {
                    newList2[min_idx] = newList2[iterate1]
                    newList2[iterate1] = x
                }
                newList2[iterate1].fullySorted = true
                mText!!.value = "${newList2[iterate1].data!!} on the left edge is considered fully sorted."
                makeAllUnIteratedwithSort()
                val refreshlist = ArrayList(newList2)
                _unSortedData.value = refreshlist
                iterate1++
                min_idx = iterate1
                iterate2 = iterate1 + 1
                if(iterate1 == listSize) {
                    alarmTimer.cancel()
                    mText!!.value = "Sorting is complete."
                }
            }
        }
    }

    fun makeAllUnIterated() {
        myList.forEach {
            it.iterate = false
            it.fullySorted = false
        }
    }

    fun makeAllUnIteratedwithSort() {
        newList2.forEach {
            it.iterate = false
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

    fun getStepEnabled(): LiveData<Boolean?>? {
        return step_enabled
    }
}