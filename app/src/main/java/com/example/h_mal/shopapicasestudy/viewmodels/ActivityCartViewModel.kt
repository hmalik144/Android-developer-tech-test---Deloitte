package com.example.h_mal.shopapicasestudy.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.repositories.ShopItemRepository
import kotlin.math.log

class ActivityCartViewModel(
    private val repository: ShopItemRepository = ShopItemRepository()
) : ViewModel(){

    fun getCurrentDbItems() = repository.getCartList()

    fun setTotalPrice() : String{
        val total = repository.getTotalPrice().toString()
        Log.i("total", total)
        return total
    }
}