package com.shoppi.app

data class Banner(
    val backgroundImageUrl: String,
    val badge: BannerBadge,
    val label: String,
    val productDetail: BannerProductDetail
)

data class BannerBadge(
    val label: String,
    val backgroundColor: String
)

data class BannerProductDetail(
    val brandName: String,
    val label: String,
    val discountRate: Int,
    val price: Int,
    val thumbnailImageUrl: String,
    val productId: String
)