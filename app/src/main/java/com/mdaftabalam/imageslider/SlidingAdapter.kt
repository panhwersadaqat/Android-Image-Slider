package com.mdaftabalam.imageslider

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import java.util.*

class SlidingAdapter internal constructor(private val context: Context, private val imageModelArrayList: ArrayList<SlidingModel>) : PagerAdapter() {
    private val inflater: LayoutInflater
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.sliding_layout, view, false)!!
        val imageView = imageLayout.findViewById<ImageView>(R.id.image)
        imageView.setImageResource(imageModelArrayList[position].image_drawable)
        view.addView(imageLayout, 0)
        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}
    override fun saveState(): Parcelable? {
        return null
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}