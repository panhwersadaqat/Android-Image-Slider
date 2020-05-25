package com.mdaftabalam.imageslider

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.mdaftabalam.imageslider.MainActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var imageModelArrayList: ArrayList<SlidingModel>? = null
    private val myImageList = intArrayOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()
        init()
    }

    private fun populateList(): ArrayList<SlidingModel> {
        val list = ArrayList<SlidingModel>()
        for (i in 0..2) {
            val imageModel = SlidingModel()
            imageModel.image_drawable = myImageList[i]
            list.add(imageModel)
        }
        return list
    }

    private fun init() {
        mPager = findViewById(R.id.pager)
        //disalble auto slide
//        val touchView = findViewById<View>(R.id.pager)
//        touchView.setOnTouchListener { v, event -> true }
        mPager!!.setAdapter(SlidingAdapter(this@MainActivity, imageModelArrayList!!))
        NUM_PAGES = imageModelArrayList!!.size


        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, false)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 4000)
    }

    companion object {
        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}