package com.example.h_mal.shopapicasestudy.models

import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity

data class ShopItem(
    val id : Int?,
    val name : String?,
    val category : String?,
    val price  : Double?,
    val stock : String?
){
    fun createFromObject(): ShopItemEntity {
        return ShopItemEntity(
            id,
            name,
            category,
            price,
            stock?.toInt()
        )
    }

    fun createCartItem(): CartItemEntity {
        return CartItemEntity(
            id,
            name,
            category,
            price,
            1
        )
    }
}