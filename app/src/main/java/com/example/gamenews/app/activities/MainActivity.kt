package com.example.gamenews.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.app.adapters.NewsAdapter

var rvNewsList: RecyclerView? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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