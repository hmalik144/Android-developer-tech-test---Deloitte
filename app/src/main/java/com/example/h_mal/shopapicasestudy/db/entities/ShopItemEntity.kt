package com.example.h_mal.shopapicasestudy.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id : Int? = null,
    val name : String? = null,
    val category : String? = null,
    val price  : Double? = null,
    val stock : Int? = null
){
    fun createCartItem(): CartItemEntity{
        return CartItemEntity(id, name, category, price,1)
    }
}