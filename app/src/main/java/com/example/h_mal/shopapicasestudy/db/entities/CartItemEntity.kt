package com.example.h_mal.shopapicasestudy.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Int? = null,
    val name : String? = null,
    val category : String? = null,
    val price  : Double? = null,
    val quantity : Int? = null
)