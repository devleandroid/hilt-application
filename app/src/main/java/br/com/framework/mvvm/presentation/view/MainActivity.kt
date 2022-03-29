package br.com.framework.mvvm.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.framework.mvvm.R
import br.com.framework.mvvm.databinding.ActivityMainBinding
import br.com.framework.mvvm.presentation.utils.NetworkHelper
import br.com.framework.mvvm.presentation.view.adapter.CountryAdapter
import br.com.framework.mvvm.presentation.viewmodel.MainViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    var progressBar = MutableLiveData<Int>()
    private val networkHelper: NetworkHelper by lazy {
        NetworkHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (networkHelper.isNetworkConnected()) {
            setUpUI()
            setUpObservers()
        }

    }

    private fun setUpUI() {
        binding.recyclerview.apply {
            binding.progressBar.visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = CountryAdapter(emptyList())
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setUpObservers() {
        mainViewModel.getCountry().observe(this, Observer {
            binding.recyclerview.apply {
                with(adapter as CountryAdapter) {
                    countries = it
                    notifyDataSetChanged()
                }
            }
            binding.progressBar.visibility = View.GONE
        })
    }
}