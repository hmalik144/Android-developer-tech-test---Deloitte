package com.example.h_mal.shopapicasestudy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.h_mal.shopapicasestudy.R
import com.example.h_mal.shopapicasestudy.viewmodels.WishListViewModel
import com.example.h_mal.shopapicasestudy.adapters.WishlistAdapter
import com.example.h_mal.shopapicasestudy.databinding.ActivityWishListBinding
import com.example.h_mal.shopapicasestudy.viewmodels.ActivityWishViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_wish_list.*

class WishListActivity : AppCompatActivity(),
    ResponseListener {

    companion object{
        var wishResponseListener: ResponseListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWishListBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_wish_list
        )
        val viewModel = ViewModelProviders.of(this).get(ActivityWishViewModel::class.java)
        binding.viewmodel = viewModel

        wishResponseListener = this

        viewModel.getCurrentDbItems().observe(this, Observer {
            val adap =
                WishlistAdapter(
                    this,
                    it
                )
            wishlistview.adapter = adap

        })

        onSuccess()
    }

    override fun onStarted() {
        progress_bar_wish.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progress_bar_wish.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

        progress_bar_wish.visibility = View.GONE
    }
}
