package com.example.onlineshop.Activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.onlineshop.Adapter.CategoryAdapter
import com.example.onlineshop.Adapter.RecommendedAdapter
import com.example.onlineshop.Adapter.SliderAdapter
import com.example.onlineshop.Model.SliderModel
import com.example.onlineshop.ViewModel.MainViewModel
import com.example.onlineshop.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initCategory()
        initRecommended()
    }

    private fun banners(image: List<SliderModel>) {

        binding.viewPager2.adapter = SliderAdapter(image, binding.viewPager2)
        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPager2.setPageTransformer(compositePageTransformer)

        if (image.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }

    private fun initBanner() {
        binding.progressBarSlider.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer {
            banners(it)
            binding.progressBarSlider.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.categories.observe(this, Observer {
            binding.rvCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategories()
    }

    private fun initRecommended() {
        binding.progressBarRecommendation.visibility = View.VISIBLE
        viewModel.recommended.observe(this, Observer {
            binding.rvRecommendation.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.rvRecommendation.adapter = RecommendedAdapter(it)
            binding.progressBarRecommendation.visibility = View.GONE

        })
        viewModel.loadRecommended()
    }
}