package caelum.com.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import caelum.com.twittelumapp.bancodedados.TweetRepository
import caelum.com.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {
    fun lista() = repository.lista()
    fun salva(tweet: Tweet) = repository.salva(tweet)
    fun deleta(tweet: Tweet) = repository.deleta(tweet)
}

