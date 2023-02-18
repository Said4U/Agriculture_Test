package com.example.agriculture_test.repository

import com.example.agriculture_test.data.DrugsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {

    @GET("/api/ppp/index/")
    @Headers("accept: application/json")
    fun getMedication(@Query("search") search: String,
                      @Query("offset") offset: Int) : Call<List<DrugsItem>>

    @GET("/api/ppp/item/")
    @Headers("accept: application/json")
    fun getMedicationCard(@Query("id") id: Int) : Call<DrugsItem>

    companion object {

        private var BASE_URL = "http://shans.d2.i-partner.ru/"

        fun create() : Api {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)

        }
    }
}