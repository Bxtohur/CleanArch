package com.catnip.retrofitexample.data.repository

import com.bitohur.foodapp.utils.ResultWrapper
import com.bitohur.foodapp.utils.proceed
import com.bitohur.foodapp.utils.proceedFlow
import com.catnip.retrofitexample.data.model.Product
import com.catnip.retrofitexample.data.model.ProductsResponse
import com.catnip.retrofitexample.data.network.api.datasource.ProductDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

interface ProductRepository{
    suspend fun getProducts(): Flow<ResultWrapper<ProductsResponse>>
}

class ProductRepositoryImpl(private val productDataSource: ProductDataSource): ProductRepository{
    override suspend fun getProducts(): Flow<ResultWrapper<ProductsResponse>> {
        return proceedFlow { productDataSource.getProducts() }.map {
            if (it.payload?.products?.isEmpty() == true)
                ResultWrapper.Empty(it.payload)
            else
                it
        }.onStart {
            emit(ResultWrapper.Loading())
            delay(2000)
        }
    }

}