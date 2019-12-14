package com.example.h_mal.shopapicasestudy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.api.AsyncApiCall
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import com.example.h_mal.shopapicasestudy.ui.ResponseListener

class ActivityWishViewModel(
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel(){

    fun getCurrentDbItems() = repository.getWishList()

}