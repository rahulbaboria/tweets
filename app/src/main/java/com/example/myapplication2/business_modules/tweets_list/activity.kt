package com.example.myapplication2.business_modules.tweets_list

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.R
import com.example.myapplication2.core.platform.extensions.observe
import com.example.myapplication2.core.platform.extensions.viewModelInstance
import com.example.myapplication2.databinding.ActivityTweetsBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class TweetsListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var _viewModel: TweetsViewModel

    @Inject
    lateinit var _adapter : TweetsListAdapter


    lateinit var binding : ActivityTweetsBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AndroidInjection.inject(this)
        _viewModel = viewModelInstance(this, viewModelFactory) {
            observe(tweets,::inflateTweets)
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tweets)

    }

    private fun inflateTweets(tweets : List<TweetDisplay>?){
        tweets?.let {
            binding.list.apply {
                if (adapter==null){
                   layoutManager = LinearLayoutManager(this@TweetsListActivity)
                   adapter = TweetsListAdapter()
                }
            }
            _adapter.update(it)
        }
    }
}