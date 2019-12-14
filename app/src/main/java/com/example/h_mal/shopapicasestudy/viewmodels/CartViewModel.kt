package com.example.h_mal.shopapicasestudy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.api.AsyncApiCall
import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import com.example.h_mal.shopapicasestudy.ui.CartActivity.Companion.cartResponseListener
import com.example.h_mal.shopapicasestudy.ui.ResponseListener

class CartViewModel(
    item: CartItemEntity?,
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel(){

    val id : Int? = item?.id
    val name : String? = item?.name
    val category :String? = item?.category
    val price : String? = item?.price.toString()
    val quantity : String? = item?.quantity.toString()

    fun deleteItemFromCart(item : CartItemEntity): String{
        val response =
            AsyncApiCall(
                "https://private-anon-21a48b3d0e-ddshop.apiary-mock.com/cart/1",
                cartResponseListener,
                "Failed to delete item"
            )
                .execute()
                .get()

        if (!response.isEmpty()){
            repository.deleteCartItem(item)

        }

        return response
    }

}