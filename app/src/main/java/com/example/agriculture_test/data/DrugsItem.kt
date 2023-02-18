package com.example.agriculture_test.data

data class DrugsItem(
    val categories: Categories,
    val description: String,
    val documentation: String,
    val fields: List<Field>,
    val id: Int,
    val image: String,
    val name: String
)