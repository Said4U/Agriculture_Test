package com.example.agriculture_test.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import com.example.agriculture_test.R
import com.example.agriculture_test.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.card_view_design.*

class ItemDetailActivity : AppCompatActivity() {

    private val mainActivityViewModel = MainActivityViewModel()
    private val BASE_URL = "http://shans.d2.i-partner.ru"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        initObservers()

        var flag = false
        starIcon.setOnClickListener{
            if (!flag) {
                flag = !flag
                starIcon.setImageResource(R.drawable.star_icon_green)
            } else{
                flag = !flag
                starIcon.setImageResource(R.drawable.star_icon)
            }
        }
    }

    private fun initObservers() {
        mainActivityViewModel.getMedicationCard(intent.extras!!.getInt("itemId"))
        mainActivityViewModel.apply {
            medicationCard.observe(this@ItemDetailActivity) {
                Picasso.get().load(BASE_URL + it.image).fit().into(imageViewDetail)
                itemNameDetail.text = it.name
                itemDescriptionDetail.text = it.description
            }
        }
    }
}