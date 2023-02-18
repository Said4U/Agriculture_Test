package com.example.agriculture_test.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agriculture_test.viewmodel.MainActivityViewModel
import com.example.agriculture_test.R
import com.example.agriculture_test.view.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),  CustomAdapter.ItemClickListener {

    private val mainActivityViewModel = MainActivityViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        initObservers()

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainActivityViewModel.getSearchMedication(query.toString(), 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainActivityViewModel.itemSaveList.clear()
                mainActivityViewModel.getMedication("", 0)
            }
        })
    }

    private fun initObservers(){
        mainActivityViewModel.getMedication("", 0)

        mainActivityViewModel.apply {
            medication.observe(this@MainActivity) {
                val recyclerViewState = recyclerView.layoutManager!!.onSaveInstanceState()
                recyclerView.adapter = CustomAdapter(it, this@MainActivity)
                recyclerView.layoutManager!!.onRestoreInstanceState(recyclerViewState)

            }
        }

        var count = 0
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    count+=20
                    mainActivityViewModel.getMedication("", count)
                }
            }
        })
    }


    override fun onItemClick(id: Int) {
        val itemDetailIntent = Intent(this, ItemDetailActivity::class.java)
        itemDetailIntent.putExtra("itemId", id)
        startActivity(itemDetailIntent)
    }
}