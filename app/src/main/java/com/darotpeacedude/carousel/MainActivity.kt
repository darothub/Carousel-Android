package com.darotpeacedude.carousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.darotpeacedude.carousel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val list = arrayListOf<CarouselItem>(
            CarouselItem(R.drawable.ic_baseline_person_24),
            CarouselItem(R.drawable.ic_baseline_person_24),
            CarouselItem(R.drawable.ic_baseline_person_24),
            CarouselItem(R.drawable.ic_baseline_person_24),
            CarouselItem(R.drawable.ic_baseline_person_24)
        )

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val r = 1-Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }

        })

        binding.vp.apply {
            adapter = CarouselAdapter(list)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(compositePageTransformer)
            registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    val size = list.size
                    handler.removeCallbacks(runnable(size))
                    handler.postDelayed(runnable(size), 3000)
                }

            })
        }
    }

    private fun runnable(count : Int)= Runnable {
        var itemPos =  binding.vp.currentItem
        itemPos += 1
        Log.i("PosBack", "$itemPos")
        if (itemPos >= count) itemPos = 0
        binding.vp.currentItem = itemPos
    }
}