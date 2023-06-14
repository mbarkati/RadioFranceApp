package com.example.radiofranceapp.data.mappers

import com.example.BrandsQuery
import com.example.radiofranceapp.domain.model.SimpleBrand

fun BrandsQuery.Brand.toSimpBrand(): SimpleBrand {
    return SimpleBrand(
        id = id,
        title = title,
        description = description ?: ""
    )
}