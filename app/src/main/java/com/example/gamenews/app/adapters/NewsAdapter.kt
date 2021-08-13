package com.example.gamenews.app.adapters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamenews.R
import com.example.gamenews.app.models.News
import java.util.*

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val newsList: MutableList<News> = LinkedList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)

        fun bind(item: News) {
            textView.text = item.type
        }
    }

    fun addData(newData : List<News>) {
        newsList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }
}
