package com.shoppi.app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.shoppi.app.AssetLoader
import com.shoppi.app.GlideApp
import com.shoppi.app.HomeData
import com.shoppi.app.R

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
        val viewPagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

        val assetLoader = AssetLoader()
        val homeJsonString = assetLoader.getJsonString(requireContext(), "home.json")

        if (!homeJsonString.isNullOrEmpty()) {
            val gson = Gson()
            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)

            viewModel.title.observe(viewLifecycleOwner) { title ->
                Log.d("HomeFragment", "text: ${title.text}, iconUrl: ${title.iconUrl}")
                toolbarTitle.text = title.text
                GlideApp.with(this)
                    .load(title.iconUrl)
                    .into(toolbarIcon)
            }

            viewModel.topBanners.observe(viewLifecycleOwner) { topBanners ->
                viewPager.adapter = HomeBannerAdapter().apply {
                    submitList(topBanners)
                }
            }
            val pageWidth =  resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin =  resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            viewPager.offscreenPageLimit = 3
            viewPager.setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(viewPagerIndicator, viewPager) { tab, position ->

            }.attach()
        }
    }
}