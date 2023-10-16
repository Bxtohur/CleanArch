package com.catnip.retrofitexample.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Keep
data class Product(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("price")
    val price: Long,
    @SerializedName("images")
    val images: List<String>
)

@Keep //agar tidak terkena obfuscated
data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)