package com.example.gamenews.app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.app.adapters.NewsAdapter
import com.example.gamenews.app.models.News
import com.example.gamenews.app.viewmodels.NewsViewModel
import com.example.gamenews.utils.AdapterScrollListener

class NewsFragment : Fragment() {

    private lateinit var rvNewsList: RecyclerView
    private lateinit var viewModel: NewsViewModel
    private var newsAdapter: NewsAdapter = NewsAdapter()

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNewsList = view.findViewById<RecyclerView>(R.id.rv_news_list)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setupAdapter(view)
        addData ()
    }

    private fun setupAdapter(view: View) {
        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false )
        rvNewsList.layoutManager = layoutManager
        rvNewsList.adapter = newsAdapter
        rvNewsList.addOnScrollListener(AdapterScrollListener(viewModel))
    }

    private fun addData () {
        val newsList: LiveData<List<News>> = viewModel.newsList
        newsList.observe(viewLifecycleOwner, { newsAdapter.addData(it) })
    }
}
