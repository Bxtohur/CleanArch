package com.catnip.retrofitexample.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitohur.foodapp.utils.proceedWhen
import com.catnip.retrofitexample.data.model.Product
import com.catnip.retrofitexample.presentation.main.adapter.ProductListAdapter
import com.catnip.retrofitexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupList()
        observeData()
    }

    private fun observeData() {
        viewModel.productList.observe(this){
            it.proceedWhen (
                doOnSuccess = {
                    binding.rvProduct.isVisible = true
                    binding.layoutState.root.isVisible = false
                    binding.layoutState.pbLoading.isVisible = false
                    binding.layoutState.tvError.isVisible = false
                    binding.rvProduct.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = ProductListAdapter()
                    }
                    it.payload?.let {
                        ProductListAdapter().setData(it.products)
                    }
                }
            )
        }
    }

    private fun setupList() {
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}