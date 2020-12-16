package bt.com.caelum.twittelumapp.db.TweetRepository

import bt.com.caelum.twittelumapp.db.TweetDao
import bt.com.caelum.twittelumapp.modelo.Tweet

class TweetRepository (private val fonteDeDados: TweetDao){
    fun lista() = fonteDeDados.lista()
    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)
    fun delete(tweet: Tweet)= fonteDeDados.delete(tweet)
}