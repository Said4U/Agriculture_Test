package com.example.agriculture_test.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agriculture_test.data.DrugsItem
import com.example.agriculture_test.repository.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel {

    private  val _medication = MutableLiveData<List<DrugsItem>>()
    val medication : LiveData<List<DrugsItem>> = _medication

    private  val _medicationCard = MutableLiveData<DrugsItem>()
    val medicationCard : LiveData<DrugsItem> = _medicationCard

    var itemSaveList = arrayListOf<DrugsItem>()

    private var api = Api.create()

    fun getMedication(search: String, offset: Int){
        api.getMedication(search, offset).enqueue(object : Callback<List<DrugsItem>> {
            override fun onResponse(call: Call<List<DrugsItem>>, response: Response<List<DrugsItem>>) {
                itemSaveList += response.body()!!.toList()
                _medication.postValue(itemSaveList)
            }
            override fun onFailure(call: Call<List<DrugsItem>>, t: Throwable) {
                Log.e("Debug", t.message.toString())
            }
        })
    }

    fun getMedicationCard(id: Int){
        api.getMedicationCard(id).enqueue(object : Callback<DrugsItem> {
            override fun onResponse(call: Call<DrugsItem>, response: Response<DrugsItem>) {
                _medicationCard.postValue(response.body())
            }
            override fun onFailure(call: Call<DrugsItem>, t: Throwable) {
                Log.e("Debug", t.message.toString())
            }
        })
    }
}