package com.example.gamenews.app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.app.models.News
import com.example.gamenews.app.viewmodels.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var rvNewsList: RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        rvNewsList = view.findViewById<RecyclerView>(R.id.rv_news_list);
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        val newsList: LiveData<List<News>> = viewModel.newsList
        newsList.observe(viewLifecycleOwner,
            { Log.e("TAG", "${it[0].type}")})
    }
}
