package bt.com.caelum.twittelumapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import bt.com.caelum.twittelumapp.modelo.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)
    @Query ( "select * from Tweet")
    fun lista(): LiveData<List<Tweet>>
    @Delete
    fun delete(tweet: Tweet)

}

