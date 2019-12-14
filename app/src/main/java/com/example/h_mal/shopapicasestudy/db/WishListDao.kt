package com.example.h_mal.shopapicasestudy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity

@Dao
interface WishListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemToWishList(item : ShopItemEntity) : Long

    @Query("SELECT * FROM ShopItemEntity")
    fun getWishlist() : LiveData<MutableList<ShopItemEntity>>

    @Query("SELECT * FROM ShopItemEntity WHERE id IN(:id)")
    fun getSingleItem(id: Int) : LiveData<ShopItemEntity>

    @Delete
    fun delete(item: ShopItemEntity) : Int

}