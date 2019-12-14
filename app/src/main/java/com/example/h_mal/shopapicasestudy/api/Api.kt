package com.example.h_mal.shopapicasestudy.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.h_mal.shopapicasestudy.models.ShopItem
import org.json.JSONArray

class Api {

    fun parseJson(jsonString : String) : MutableList<ShopItem>{



        val list = mutableListOf<ShopItem>()

        try {
            val json = JSONArray(jsonString)

            for (i in 0 until json.length()){
                val item = json.getJSONObject(i)

                val id = item.get("productId") as Int?
                val name = item.get("name") as String?
                val category = item.get("category") as String?
                val price = (item.get("price")).toString().toDouble()
                val stock = item.get("stock") as Int?

                val shopItem =
                    ShopItem(
                        id,
                        name,
                        category,
                        price,
                        stock.toString()
                    )

                list.add(shopItem)
            }
        }catch (e: Exception){
            Log.e("Error", e.message)
        }

        return list

    }



}