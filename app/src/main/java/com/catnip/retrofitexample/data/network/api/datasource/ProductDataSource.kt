package com.catnip.retrofitexample.data.network.api.datasource

import com.catnip.retrofitexample.data.model.ProductsResponse
import com.catnip.retrofitexample.data.network.api.service.ProductService

interface ProductDataSource{
    suspend fun getProducts(): ProductsResponse
}

class ProductDataSourceImpl(private val service: ProductService): ProductDataSource{
    override suspend fun getProducts(): ProductsResponse {
        return service.getProducts()
    }

}