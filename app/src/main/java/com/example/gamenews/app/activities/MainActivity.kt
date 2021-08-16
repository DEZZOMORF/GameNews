package com.example.gamenews.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.app.adapters.NewsAdapter
import com.example.gamenews.app.fragments.NewsFragment
import com.google.android.material.tabs.TabLayout

var rvNewsList: RecyclerView? = null
var mTabLayout: TabLayout? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTabLayout = findViewById<TabLayout>(R.id.tabLayout)
        supportFragmentManager.beginTransaction().add(R.id.frgmCont, NewsFragment.newInstance(), "stories").commit()
        mTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                                .add(R.id.frgmCont, NewsFragment.newInstance(), "stories")
                                .commit()
                    }
                    1 -> { //TODO
                        supportFragmentManager.findFragmentByTag("stories")?.let {
                            supportFragmentManager.beginTransaction()
                                .hide(it)
                                .commit()
                        }
                    }
                    2 -> { //TODO
                        supportFragmentManager.findFragmentByTag("stories")?.let {
                            supportFragmentManager.beginTransaction()
                                .hide(it)
                                .commit()
                        }
                    }
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        rvNewsList = findViewById<RecyclerView>(R.id.rv_news_list)
        menuInflater.inflate(R.menu.menu, menu)
        var menuItem = menu?.findItem(R.id.action_search)
        var searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                val adapter = rvNewsList?.adapter as NewsAdapter
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}