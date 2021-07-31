package com.example.myapplication2.business_modules.tweets_list

import com.example.myapplication2.core.platform.adapter.Diffable

data class TweetDisplay(val id : Long, val title : String, val noOfLikes : Long) : Diffable {

    override fun isSame(other: Any): Boolean {
        if (other is TweetDisplay)
            return id == other.id
        return false
    }

    override fun isContentSame(other: Any): Boolean {
        if (other is TweetDisplay)
            return noOfLikes == other.noOfLikes
        return false
    }
}


