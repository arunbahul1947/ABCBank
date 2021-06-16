package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList

class ViewModelCustom(application: Application) : AndroidViewModel(application) {

     val liveData: MutableLiveData<ArrayList<DataClass>> = MutableLiveData<ArrayList<DataClass>>()

    fun getData(key :String){

        val arrayListData: ArrayList<DataClass> = ArrayList()

        val dataClass= DataClass("One", R.drawable.a1)
        val dataClass2= DataClass("Two", R.drawable.a2)
        val dataClass3= DataClass("Three", R.drawable.a3)
        val dataClass4= DataClass("Four", R.drawable.a4)
        val dataClass5= DataClass("Five", R.drawable.a5)
        val dataClass6= DataClass("Six", R.drawable.a6)
        val dataClass7= DataClass("Seven",  R.drawable.a1)
        val dataClass8= DataClass("Eight",  R.drawable.a2)
        val dataClass9= DataClass("Nine",  R.drawable.a3)
        val dataClass10= DataClass("Ten",  R.drawable.a4)
        val dataClass11= DataClass("Eleven",  R.drawable.a5)
        val dataClass12= DataClass("Twelve",  R.drawable.a6)
        val dataClass13= DataClass("Thirteen",  R.drawable.a1)
        val dataClass14= DataClass("Fourteen",  R.drawable.a2)
        val dataClass15= DataClass("Fifteen",  R.drawable.a3)

        arrayListData.add(dataClass)
        arrayListData.add(dataClass2)
        arrayListData.add(dataClass3)
        arrayListData.add(dataClass4)
        arrayListData.add(dataClass5)
        arrayListData.add(dataClass6)
        arrayListData.add(dataClass7)
        arrayListData.add(dataClass8)
        arrayListData.add(dataClass9)
        arrayListData.add(dataClass10)
        arrayListData.add(dataClass11)
        arrayListData.add(dataClass12)
        arrayListData.add(dataClass13)
        arrayListData.add(dataClass14)
        arrayListData.add(dataClass15)
        when {
            key == "shuffle" -> {
                //    Shuffling data to making UI change
                arrayListData.shuffle()
                liveData.postValue(arrayListData)
            }
            key!= "" -> {
                //   Doing filter from the whole list
                val tempList: ArrayList<DataClass> = ArrayList()
                for(i in 0 until arrayListData.size) {
                    if (arrayListData[i].name?.contains(key, true)!!){
                        tempList.add(arrayListData[i])
                    }
                }
                liveData.postValue(tempList)
            }
            else -> {
                //   Normally returning list
                liveData.postValue(arrayListData)
            }
        }
    }

}