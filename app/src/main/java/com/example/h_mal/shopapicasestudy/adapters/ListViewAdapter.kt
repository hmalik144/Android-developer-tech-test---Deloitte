package com.example.h_mal.shopapicasestudy.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.h_mal.shopapicasestudy.R
import com.example.h_mal.shopapicasestudy.databinding.ListItemLayoutBinding
import com.example.h_mal.shopapicasestudy.models.ShopItem
import com.example.h_mal.shopapicasestudy.ui.MainActivity
import com.example.h_mal.shopapicasestudy.ui.MainActivity.Companion.responseListener
import com.example.h_mal.shopapicasestudy.ui.ResponseListener
import com.example.h_mal.shopapicasestudy.viewmodels.ListViewModel
import kotlinx.android.synthetic.main.list_item_layout.view.*

class ListViewAdapter(context: Context,  objects: MutableList<ShopItem>) :
    ArrayAdapter<ShopItem>(context, 0, objects) {

    private var listViewBinding: ListItemLayoutBinding? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null)!!
            listViewBinding = DataBindingUtil.bind(view)
            view.setTag(listViewBinding)
        }else{
            listViewBinding = view.getTag() as ListItemLayoutBinding
        }

        val s = getItem(position)
        val vm = ListViewModel(s)

        listViewBinding?.viewmodel = vm

        view.setOnClickListener {
            //produce a dialog
            showDialog(vm,position)

        }


        return view
    }

    fun showDialog(vm : ListViewModel, position: Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Action")

        // Display a message on alert dialog
        builder.setMessage("Do you want to add to Cart or Wishlist?")

        builder.setPositiveButton("WishList"){dialog, which ->
            dialog.dismiss()

            val i = getItem(position)

            val id = vm.insertWishListItem(i)

            if (id > 0){
                Toast.makeText(context,"Item Successfully added to Wishlist",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to add Item to Wishlist",Toast.LENGTH_SHORT).show()
            }

        }


        builder.setNegativeButton("Cart"){dialog,which ->
            dialog.dismiss()

            val stock = getItem(position).stock?.toInt()

            if (stock == 0){
                Toast.makeText(context,"Item out of stock",Toast.LENGTH_SHORT).show()
                return@setNegativeButton
            }

            val i = getItem(position).createCartItem()

            val id = vm.insertCartListItem(i, responseListener)

            if (!id.isEmpty()){
                Toast.makeText(context,"Item Successfully added to Cart",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Failed to add Item to Cart",Toast.LENGTH_SHORT).show()
            }
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()
    }
}