package bt.com.caelum.twittelumapp.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import bt.com.caelum.twittelumapp.R
import bt.com.caelum.twittelumapp.databinding.ActivityMainBinding
import bt.com.caelum.twittelumapp.modelo.Tweet
import bt.com.caelum.twittelumapp.viewmodel.TweetViewModel
import bt.com.caelum.twittelumapp.viewmodel.ViewModelFactory
import caelum.com.twittelumapp.R
import caelum.com.twittelumapp.databinding.ActivityMainBinding
import java.io.File

class TweetActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    private var localFoto: String ? = null
    private val viewModel : TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onResume() {
        super.onResume()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.save -> {
                publicaTweet()
            }
            R.id.camera -> {
                tiraFoto()
            }
            android.R.id.home ->finish()
        }
        return false
    }

    private fun tiraFoto() {
        val goCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto =defineLocalDaFoto()
        goCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivity(goCamera)

    }

    private fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twittelumapp.fileprovider", arquivo)

    }
    private fun publicaTweet() {
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.conteudoTweet)
        val mensagemDoTweet : String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        viewModel.salva(tweet)
        //Toast.makeText(this, mensagemDoTweet, Toast.LENGTH_LONG).show()
        Toast.makeText(this, "$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
        finish()
    }
    }

