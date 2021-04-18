package com.darotpeacedude.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.darotpeacedude.carousel.databinding.CarouselItemBinding

class CarouselAdapter(var list: List<CarouselItem>):RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
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
    }

    override fun getItemCount(): Int {
        return list.size
    }
}