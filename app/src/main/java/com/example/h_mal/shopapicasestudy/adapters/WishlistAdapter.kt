package com.example.h_mal.shopapicasestudy.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.h_mal.shopapicasestudy.R
import com.example.h_mal.shopapicasestudy.databinding.ListItemLayoutBinding
import com.example.h_mal.shopapicasestudy.databinding.WishlistItemLayoutBinding
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.ui.WishListActivity.Companion.wishResponseListener
import com.example.h_mal.shopapicasestudy.viewmodels.ListViewModel
import com.example.h_mal.shopapicasestudy.viewmodels.WishListViewModel
import kotlinx.android.synthetic.main.list_item_layout.view.*

class WishlistAdapter(context: Context, objects: MutableList<ShopItemEntity>) :
    ArrayAdapter<ShopItemEntity>(context, 0, objects) {

    var wishlistItemLayoutBinding : WishlistItemLayoutBinding? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.wishlist_item_layout, null)!!
            wishlistItemLayoutBinding = DataBindingUtil.bind(view)
            view.tag = wishlistItemLayoutBinding
        }else{
            wishlistItemLayoutBinding = view.tag as WishlistItemLayoutBinding
        }

        val item = getItem(position)
        val vm = WishListViewModel(item)

        wishlistItemLayoutBinding?.viewmodel = vm

        view.setOnClickListener {
            showDialog(vm,position)
        }

        return view
    }

    fun showDialog(vm : WishListViewModel, position: Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Action")

        // Display a message on alert dialog
        builder.setMessage("Do you want to add to Cart or Delete?")

        builder.setPositiveButton("Cart"){dialog, which ->
            dialog.dismiss()

            val stock = getItem(position).stock

            if (stock == 0){
                Toast.makeText(context,"Item out of stock",Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val i = getItem(position)

            val id = vm.insertCartListItem(i)

            if (!id.isEmpty()){
                Toast.makeText(context,"Item Successfully added to Cart",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to add Item to Cart",Toast.LENGTH_SHORT).show()
            }

        }


        builder.setNegativeButton("Delete"){dialog,which ->
            dialog.dismiss()

            val item = getItem(position)

            val i = vm.deleteFromWishList(item)

            if (i > 0){
                Toast.makeText(context,"Item Successfully removed from Wishlist", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to remove Item from Wishlist", Toast.LENGTH_SHORT).show()
            }
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }
}