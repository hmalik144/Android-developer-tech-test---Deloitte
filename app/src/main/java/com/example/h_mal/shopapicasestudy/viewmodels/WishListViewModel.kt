package com.example.h_mal.shopapicasestudy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.api.AsyncApiCall
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import com.example.h_mal.shopapicasestudy.ui.ResponseListener
import com.example.h_mal.shopapicasestudy.ui.WishListActivity.Companion.wishResponseListener

class WishListViewModel(
    item: ShopItemEntity?,
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel() {

    val id : Int? = item?.id
    val name : String? = item?.name
    val category :String? = item?.category
    val price : String? = item?.price.toString()
    val stock : String? = item?.stock.toString()

    fun deleteFromWishList(item : ShopItemEntity) = repository.deleteWishListItem(item)

    fun insertCartListItem(shopItem : ShopItemEntity): String{
        val response =
            AsyncApiCall(
                "https://private-anon-f3c1195210-ddshop.apiary-mock.com/products",
                wishResponseListener,
                "Failed to insert item"
            )
                .execute()
                .get()

        if (!response.isEmpty()){
            val i = shopItem.createCartItem()
            repository.addCartItem(i)
        }

        return response
    }

}