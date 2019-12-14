package com.example.h_mal.shopapicasestudy.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItemToCart(item : CartItemEntity) : Long

    @Query("SELECT * FROM CartItemEntity")
    fun getCartlist() : LiveData<MutableList<CartItemEntity>>

    @Query("SELECT * FROM CartItemEntity WHERE id IN(:id)")
    fun getSingleItem(id: Int) : LiveData<CartItemEntity>

    @Delete
    fun delete(item: CartItemEntity) : Int

    @Query("UPDATE CartItemEntity SET quantity = quantity + 1 WHERE id IN (:id)")
    fun incremementItem(id : Int) : Int

    @Query("UPDATE CartItemEntity SET quantity = quantity - 1 WHERE id IN (:id)")
    fun decrementItem(id : Int) : Int

    @Query("SELECT SUM(price) FROM CartItemEntity")
    fun getTotalSumOFCart() : Double

    @Transaction
    fun upsert(item : CartItemEntity) : Int{
        val id = insertItemToCart(item)

        val idInt = id.toInt()

        if (idInt == -1){
            return incremementItem(item.id!!)
        }else{
            return idInt
        }
    }

    @Transaction
    fun downsert(item : CartItemEntity){
        val quantity = item.quantity!!

        if (quantity >1){
            decrementItem(item.id!!)
        }else{
            delete(item)
        }
    }
}