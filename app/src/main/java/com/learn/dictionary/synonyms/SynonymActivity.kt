package com.learn.dictionary.synonyms

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.dictionary.R
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_synonym.*
import javax.inject.Inject

class SynonymActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var synonymsViewModel: SynonymsViewModel
    private lateinit var adapter: SynonymAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synonym)

        synonymsViewModel = ViewModelProvider(this,viewModelFactory)[SynonymsViewModel::class.java]

        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                synonymsViewModel.setSearchString(query)
            }
        }
        setRecyclerView()
        setObservers()
    }

    private fun setRecyclerView() {
        searchResultList.layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.divider)?.let {
            itemDecorator.setDrawable(it)
        }
        searchResultList.addItemDecoration(itemDecorator)
        adapter = SynonymAdapter()
        searchResultList.adapter = adapter
    }

    private fun setObservers() {
        synonymsViewModel.synonymsList.observe(this, Observer {
            adapter.dataSet = it.list
            adapter.notifyDataSetChanged()
        })

//        synonymsViewModel.showDialog.observe(this, Observer { showDialog ->
//            if(showDialog){
//
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        synonymsViewModel.cancelJob()
    }

}
