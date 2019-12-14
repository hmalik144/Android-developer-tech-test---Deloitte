package com.example.h_mal.shopapicasestudy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.api.AsyncApiCall
import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.models.ShopItem
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import com.example.h_mal.shopapicasestudy.ui.ResponseListener

class ListViewModel(
    item : ShopItem,
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel(){

    val id : Int? = item.id
    val name : String? = item.name
    val category :String? = item.category
    val price : String? = item.price.toString()
    val stock : String? = item.stock


    fun insertWishListItem(shopItem : ShopItem) = repository.addWishItem(shopItem)

    fun insertCartListItem(cartItem : CartItemEntity, responseListener : ResponseListener?): String{
        val response =
            AsyncApiCall(
                "https://private-anon-f3c1195210-ddshop.apiary-mock.com/products",
                responseListener
            )
                .execute()
                .get()

        if (!response.isEmpty()){
            repository.addCartItem(cartItem)
        }

        return response
    }
}