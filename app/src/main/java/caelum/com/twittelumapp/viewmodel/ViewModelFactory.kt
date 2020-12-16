package caelum.com.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TweetRepository
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import com.example.twiitelum.application.TwittelumApplication

object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val contexto = TwittelumApplication.getInstance()
        val database = TwittelumDatabase.getDatabase(contexto)
        val tweetDao = database.getTweetDao()
        val repository = TweetRepository(tweetDao)
        return TweetViewModel(repository) as T
    }
}
