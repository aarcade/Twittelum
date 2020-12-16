package bt.com.caelum.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bt.com.caelum.twittelumapp.application.TwittelumApplication
import bt.com.caelum.twittelumapp.db.TweetRepository.TweetRepository
import bt.com.caelum.twittelumapp.db.TwittelumDataBase

object ViewModelFactory: ViewModelProvider.Factory {
    private val database : TwittelumDataBase = TwittelumDataBase.getInstance(TwittelumApplication.getInstance())
    private val tweetRepository = TweetRepository(database.tweetDao())
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TweetViewModel(tweetRepository) as T
}

