package com.shoppi.app

import com.google.gson.annotations.SerializedName

data class HomeData(
    val title: Title,
    @SerializedName("top_banners") val topBanners: List<Banner>
)

data class Banner(
    @SerializedName("background_image_url") val backgroundImageUrl: String,
    val badge: BannerBadge,
    val label: String,
    @SerializedName("product_detail") val productDetail: BannerProductDetail
)

data class BannerBadge(
    val label: String,
    @SerializedName("background_color") val backgroundColor: String
)

data class BannerProductDetail(
    @SerializedName("brand_name") val brandName: String,
    val label: String,
    @SerializedName("discount_rate") val discountRate: Int,
    val price: Int,
    @SerializedName("thumbnail_image_url") val thumbnailImageUrl: String,
    @SerializedName("product_id") val productId: String
)

data class Title(
    val text: String,
    @SerializedName("icon_url") val iconUrl: String
)
