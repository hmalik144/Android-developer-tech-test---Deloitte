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
import com.example.h_mal.shopapicasestudy.databinding.CartItemLayoutBinding
import com.example.h_mal.shopapicasestudy.databinding.ListItemLayoutBinding
import com.example.h_mal.shopapicasestudy.databinding.WishlistItemLayoutBinding
import com.example.h_mal.shopapicasestudy.db.entities.CartItemEntity
import com.example.h_mal.shopapicasestudy.db.entities.ShopItemEntity
import com.example.h_mal.shopapicasestudy.ui.WishListActivity.Companion.wishResponseListener
import com.example.h_mal.shopapicasestudy.viewmodels.CartViewModel
import com.example.h_mal.shopapicasestudy.viewmodels.ListViewModel
import com.example.h_mal.shopapicasestudy.viewmodels.WishListViewModel
import kotlinx.android.synthetic.main.list_item_layout.view.*

class CartlistAdapter(context: Context, objects: MutableList<CartItemEntity>) :
    ArrayAdapter<CartItemEntity>(context, 0, objects) {

    var cartBinding: CartItemLayoutBinding? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, null)!!
            cartBinding = DataBindingUtil.bind(view)
            view.tag = cartBinding

        }else{
            cartBinding = view.getTag() as CartItemLayoutBinding?
        }

        val i = getItem(position)

        val vm = CartViewModel(i)

        cartBinding?.viewmodel = vm

        view.setOnClickListener {
            showDialog(vm,position)
        }

        return view
    }

    fun showDialog(vm : CartViewModel, position: Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Action")

        // Display a message on alert dialog
        builder.setMessage("Do you want to delete?")

        builder.setPositiveButton("Delete"){dialog, which ->
            dialog.dismiss()

            val item = getItem(position)

            val s = vm.deleteItemFromCart(item)

            if (!s.isEmpty()){
                Toast.makeText(context,"Item Successfully added to Cart",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to add Item to Wishlist",Toast.LENGTH_SHORT).show()
            }

        }


        builder.setNegativeButton("Cancel"){dialog,which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }
}