package com.example.gamenews.app.adapters;

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamenews.R
import com.example.gamenews.app.models.News
import java.util.*


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>(), Filterable {
    private val newsList: MutableList<News> = LinkedList()
    private val filteredNews: MutableList<News> = LinkedList();

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.title)
        private val tvLink: TextView = view.findViewById(R.id.link)
        private val tvTime: TextView = view.findViewById(R.id.time)
        private val ivImage: ImageView = view.findViewById(R.id.image)

        fun bind(item: News) {
            Glide.with(this.itemView.context).load(item.img).into(ivImage)
            tvTitle.text = item.title
            tvLink.text = item.click_url?.substringAfterLast("/")
            tvTime.text = " - ${item.time}"
            this.itemView.setOnClickListener{ this.itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.click_url))) }
        }
    }

    fun addData(newData : List<News>) {
        newsList.addAll(newData)
        filteredNews.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredNews[position])
    }

    override fun getItemCount(): Int {
        return filteredNews.count()
    }

    //Filter
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<News> = ArrayList<News>()
                if (constraint.toString().isEmpty()) {
                    filteredList.addAll(newsList)
                } else {
                    for (item in newsList) {
                        if (item.title!!.contains(constraint.toString())) {
                            filteredList.add(item)
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredNews.clear()
                filteredNews.addAll(results?.values as Collection<News>)
                notifyDataSetChanged()
            }

        }
    }
}
