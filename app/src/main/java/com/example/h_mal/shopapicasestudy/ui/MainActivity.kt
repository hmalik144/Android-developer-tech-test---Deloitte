package com.example.h_mal.shopapicasestudy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.h_mal.shopapicasestudy.R
import com.example.h_mal.shopapicasestudy.adapters.ListViewAdapter
import com.example.h_mal.shopapicasestudy.databinding.ActivityMainBinding
import com.example.h_mal.shopapicasestudy.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    ResponseListener {

    companion object{
        var responseListener: ResponseListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel

        responseListener = this

        //call to retrieve list
        viewModel.callApiShopList()

        //observable an live list to populate the adapter and applu
        viewModel.getShopList().observe(this, Observer {
            val adapater =
                ListViewAdapter(
                    this,
                    it
                )
            list_view.adapter = adapater
        })

    }

    //create a menu to navigate to other activities
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.wish_list ->{
                val intent = Intent(this,
                    WishListActivity::class.java)
                startActivity(intent)

            }
            R.id.cart ->{
                val intent = Intent(this,
                    CartActivity::class.java)
                startActivity(intent)
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onStarted() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        progress_bar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

        progress_bar.visibility = View.GONE
    }
}
