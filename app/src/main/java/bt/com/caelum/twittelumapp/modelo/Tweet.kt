package bt.com.caelum.twittelumapp.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Tweet(val conteudo: String,
                 @PrimaryKey (autoGenerate = true)
                 val id: Long=0
) {
    override fun toString(): String {
        return conteudo
    }


    }
