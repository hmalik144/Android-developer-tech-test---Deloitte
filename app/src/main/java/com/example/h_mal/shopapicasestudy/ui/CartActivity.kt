package com.example.h_mal.shopapicasestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.h_mal.shopapicasestudy.R
import com.example.h_mal.shopapicasestudy.adapters.CartlistAdapter
import com.example.h_mal.shopapicasestudy.databinding.ActivityCartBinding
import com.example.h_mal.shopapicasestudy.viewmodels.ActivityCartViewModel
import com.example.h_mal.shopapicasestudy.viewmodels.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*

class CartActivity : AppCompatActivity(), ResponseListener {

    companion object{
        var cartResponseListener: ResponseListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCartBinding = DataBindingUtil.setContentView(this,R.layout.activity_cart)
        val cartViewModel =  ViewModelProviders.of(this).get(ActivityCartViewModel::class.java)
        binding.viewmodel = cartViewModel

        cartResponseListener = this

        cartViewModel.getCurrentDbItems().observe(this, Observer {
            val adap =
                CartlistAdapter(
                    this,
                    it
                )
            cart_list_view.adapter = adap

            Log.i("Total", "executed")

            total_text.text = cartViewModel.setTotalPrice()

            onSuccess()
        })


    }

    override fun onStarted() {
        progress_bar_cart.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progress_bar_cart.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

        progress_bar_cart.visibility = View.GONE
    }
}
