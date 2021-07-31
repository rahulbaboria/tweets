package com.example.myapplication2.repo.tweets

import com.example.myapplication2.business_modules.tweets_list.TweetDisplay

data class Tweet(val id : Long?, val title : String?, val noOfLikes : Long?){

    companion object{
        fun empty() = Tweet(0,"empty",0)
    }

    fun display() : TweetDisplay{
        return TweetDisplay(id?:0,title?:"empty",noOfLikes?:0)
    }
}