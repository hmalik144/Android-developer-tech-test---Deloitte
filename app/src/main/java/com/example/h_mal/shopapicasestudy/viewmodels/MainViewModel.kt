package com.example.h_mal.shopapicasestudy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.api.AsyncApiCall
import com.example.h_mal.shopapicasestudy.ui.ResponseListener
import com.example.h_mal.shopapicasestudy.models.ShopItem
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import com.example.h_mal.shopapicasestudy.ui.MainActivity.Companion.responseListener


class MainViewModel(
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel(){

    private var shopList: MutableLiveData<MutableList<ShopItem>> = MutableLiveData()


    fun getShopList(): MutableLiveData<MutableList<ShopItem>> {
        return shopList
    }


    fun callApiShopList(){
        val jsonResponse =
            AsyncApiCall(
                "https://private-anon-f3c1195210-ddshop.apiary-mock.com/products",
                responseListener
            )
            .execute()
            .get()

        shopList.value = repository.api.parseJson(jsonResponse)

    }

}