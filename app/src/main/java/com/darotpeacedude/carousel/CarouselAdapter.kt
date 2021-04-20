package com.darotpeacedude.carousel

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.darotpeacedude.carousel.databinding.CarouselItemBinding

class CarouselAdapter(var list: ArrayList<CarouselItem>, var vp:ViewPager2):RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    lateinit var binding: CarouselItemBinding
    inner class CarouselViewHolder(val binding: CarouselItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(carouselItem: CarouselItem){
            binding.image.setImageResource(carouselItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        if (position == list.size - 2 ){
            vp.post(runnable())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun runnable()= Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }
}