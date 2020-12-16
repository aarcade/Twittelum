package caelum.com.twittelumapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TwittelumDatabase
import caelum.com.twittelumapp.databinding.ActivityTweetBinding
import caelum.com.twittelumapp.modelo.Tweet
import caelum.com.twittelumapp.viewmodel.TweetViewModel
import caelum.com.twittelumapp.viewmodel.ViewModelFactory
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto: String? = null
    lateinit var viewModel: TweetViewModel
    private lateinit var binding : ActivityTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory)[TweetViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.tweet_menu_foto -> {
            tiraFoto()
            true
        }
        R.id.tweet_menu_cadastrar -> {
            publicaTweet()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    private fun tiraFoto() {
        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivity(vaiPraCamera)
    }
    fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twittelumapp.fileprovider", arquivo)
    }


    fun publicaTweet() {
        val conteudo = binding.tweetMensagem.text.toString()
        val tweet = Tweet(conteudo)
        val database = TwittelumDatabase.getDatabase(this)
        val tweetDao = database.getTweetDao()
        tweetDao.salva(tweet)

        Toast.makeText(this, conteudo, Toast.LENGTH_SHORT).show()

        finish()

    }

    override fun onResume() {super.onResume()
        if (localFoto != null) {
            carregaFoto()
        }
    }
    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        binding.tweetCard.visibility = View.VISIBLE
        binding.tweetFoto.setImageBitmap(bm)
        binding.tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
    }
}