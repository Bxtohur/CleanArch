package com.catnip.retrofitexample.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitohur.foodapp.utils.ResultWrapper
import com.catnip.retrofitexample.data.network.api.service.ProductService
import com.catnip.retrofitexample.data.model.ProductsResponse
import com.catnip.retrofitexample.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(private val repo : ProductRepository) : ViewModel() {
    val productList = MutableLiveData<ResultWrapper<ProductsResponse>>()

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getProducts().collect{
                productList.postValue(it)
            }
        }
    }
}