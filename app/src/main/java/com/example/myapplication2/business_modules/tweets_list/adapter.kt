package com.example.myapplication2.business_modules.tweets_list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication2.R
import com.example.myapplication2.core.platform.adapter.AppListAdapter
import com.example.myapplication2.core.platform.adapter.DiffableListCallback
import javax.inject.Inject

class TweetsListAdapter
@Inject
constructor() : AppListAdapter() {
    val tweets = ArrayList<TweetDisplay>()

    override fun layoutId() = R.layout.item_tweet
    override fun itemClicked(position: Int) {

    }

    override fun viewHolder(view: View) = ItemClickViewHolder(view)

    override fun onCustomBindViewHolder(holder: AppListViewHolder, position: Int) {
        tweets[position].let {
            holder.itemView.findViewById<TextView>(R.id.tv_title).text = it.title
            holder.itemView.findViewById<TextView>(R.id.tv_likes).text = it.noOfLikes.toString()
        }
        holder.bind()
    }

    override fun getItemCount(): Int = tweets.size

    fun update(list : List<TweetDisplay>){
        this.tweets.clear()
        this.tweets.addAll(list)
        DiffUtil.calculateDiff(DiffableListCallback(this.tweets, list)).dispatchUpdatesTo(this)
    }
}


