package bt.com.caelum.twittelumapp.viewmodel

import androidx.lifecycle.ViewModel
import bt.com.caelum.twittelumapp.db.TweetRepository.TweetRepository
import bt.com.caelum.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository): ViewModel() {
    fun lista()=  repository.lista()
    fun salva(tweet: Tweet)= repository.salva(tweet)
    fun delete(tweet: Tweet)=repository.delete(tweet)
}