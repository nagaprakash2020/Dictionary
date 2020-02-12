package com.learn.dictionary.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.dictionary.DictionaryViewModelFactory
import com.learn.dictionary.ViewModelKey
import com.learn.dictionary.synonyms.SynonymsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DictionaryViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SynonymsViewModel::class)
    internal abstract fun synonymsViewModel(viewModel: SynonymsViewModel): ViewModel
}