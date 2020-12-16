package com.example.twiitelum

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import caelum.com.twittelumapp.bancodedados.TweetDao
import caelum.com.twittelumapp.databinding.ActivityListaBinding
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.databinding.ActivityListaBinding
import com.example.twiitelum.db.TwittelumDatabase
import com.example.twiitelum.viewmodel.TweetViewModel
import com.example.twiitelum.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ListaActivity : AppCompatActivity() {

    private val binding: ActivityListaBinding by lazy {
        ActivityListaBinding.inflate(layoutInflater)
    }
    private val viweModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory)[TweetViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val listener = AdapterView.OnItemClickListener {
                adapter, item, posicao, id ->
            val tweet = binding.listaTweet.getItemAtPosition(posicao) as Tweet
            perguntaSePrecisaDeletarEsse(tweet)
        }
        binding.listaTweet.onItemClickListener = listener
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener{
            val intencao = Intent(this, MainActivity::class.java)

            startActivity(intencao)
        }
    }

    private fun perguntaSePrecisaDeletarEsse(tweet: Tweet) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Deseja mesmo apagar esse tweet?")
        dialog.setTitle("Atenção")
        dialog.setPositiveButton("Sim") { _,_ -> ViewModel.deleta(tweet)  }
        dialog.setNegativeButton("Não", null)
        dialog.show()
    }


    override fun onResume() {
        super.onResume()
        val tweetsLiveData: LiveData<List<Tweet>> = viweModel.lista()

        tweetsLiveData.observe(this, { tweets -> val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tweets)

            binding.listaTweet.adapter = adapter

        })
    }
}
