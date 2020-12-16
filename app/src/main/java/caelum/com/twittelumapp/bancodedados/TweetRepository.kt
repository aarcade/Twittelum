package caelum.com.twittelumapp.bancodedados

import caelum.com.twittelumapp.modelo.Tweet

class TweetRepository
    (val fonteDeDados: TweetDao) {
    fun lista() = fonteDeDados.lista()
    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)
    fun deleta(tweet: Tweet) = fonteDeDados.deleta(tweet)
}
