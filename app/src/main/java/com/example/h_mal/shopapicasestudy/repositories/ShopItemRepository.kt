package com.example.h_mal.shopapicasestudy.repositories

import com.example.h_mal.shopapicasestudy.api.Api
import com.example.h_mal.shopapicasestudy.ApiApplication
import com.example.h_mal.shopapicasestudy.db.AppDatabase
import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.models.ShopItem

class ShopItemRepository (
    val api: Api = Api(),
    val db : AppDatabase = ApiApplication.db!!
) {


    fun addWishItem(item: ShopItem): Long{
        val i = item.createFromObject()
        return db.getWishListDao().insertItemToWishList(i)
    }

    fun deleteWishListItem(item : ShopItemEntity) : Int =
        db.getWishListDao().delete(item)


    fun getWishList() = db.getWishListDao().getWishlist()

    fun addCartItem(item: CartItemEntity): Int
     = db.getCartDao().upsert(item)


    fun decrementItem(item: CartItemEntity): Int
    = db.getCartDao().decrementItem(item.id!!)

    fun deleteCartItem(item : CartItemEntity): Int =
        db.getCartDao().delete(item)

    fun getCartList() = db.getCartDao().getCartlist()

    fun getTotalPrice() :Double = db.getCartDao().getTotalSumOFCart()
}