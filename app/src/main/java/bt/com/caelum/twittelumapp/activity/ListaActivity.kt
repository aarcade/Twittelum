package bt.com.caelum.twittelumapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import bt.com.caelum.twittelumapp.modelo.Tweet
import bt.com.caelum.twittelumapp.viewmodel.TweetViewModel
import bt.com.caelum.twittelumapp.viewmodel.ViewModelFactory
import com.example.twiitelum.R
import com.example.twiitelum.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    private val viewModel : TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }
    private lateinit var binding : ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabAdd.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)
        }
        viewModel.lista().observe(this, observer())
        val listener = AdapterView.OnItemClickListener {
                adapter, item, posicao, id ->
            val tweet = binding.listaTweet.getItemAtPosition(posicao) as Tweet
            perguntaSePrecisaDeletar(tweet)
        }
         binding.listaTweet.onItemClickListener = listener
    }

    private fun perguntaSePrecisaDeletar(tweet: Tweet) {
        val dialog = AlertDialog.Builder(this)
        dialog.setIcon(R.drawable.ic_aviso)
        dialog.setMessage("Apagando o tweet com o conteúdo: \n${tweet.conteudo}")
        dialog.setTitle("Atenção")
        dialog.setPositiveButton("Sim"){_,_ -> viewModel.delete(tweet)}
        dialog.setNegativeButton("Não", null)
        dialog.show()


    }

    private fun observer(): Observer<List<Tweet>> {
        return Observer{
            binding.listaTweet.adapter = ArrayAdapter <Tweet>(this, android.R.layout.simple_list_item_1, it)

        }


    }
}

