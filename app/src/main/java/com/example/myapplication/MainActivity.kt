package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel : ViewModelCustom


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initObserver()

        setUpViewPager()

        viewModel.getData("")
    }

    private fun setUpViewPager() {
        val images = intArrayOf(R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6 )
        viewPager.adapter = CarouselAdapter(this@MainActivity, images)
        viewPagerIndicator.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(viewPagerIndicator))

        viewPagerIndicator.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.getData("shuffle")
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) { }
            override fun onTabReselected(tab: TabLayout.Tab) { }
        })
    }

    private fun initClicks() {
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
            {
                    doSearchingProcess(s.toString())
            }
        })
    }

    private fun doSearchingProcess(text: String) {
        progressBottom.visibility=View.VISIBLE
        recyclerView.visibility=View.GONE

        viewModel.getData(text)
    }


    private fun initObserver() {
        viewModel.liveData.observe(this, Observer {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
            recyclerView.visibility=View.VISIBLE
            progressBottom.visibility=View.GONE
            recyclerView.adapter= AdapterRecyclerView(this, it)



            initClicks()
        })
    }




    private fun initViewModel() {
        viewModel= ViewModelProvider(this).get(ViewModelCustom::class.java)
    }
}