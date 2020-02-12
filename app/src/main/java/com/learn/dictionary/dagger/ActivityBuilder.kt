package com.learn.dictionary.dagger

import dagger.android.ContributesAndroidInjector
import com.learn.dictionary.MainActivity
import com.learn.dictionary.synonyms.SynonymActivity
import dagger.Module


@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindSynonymActivity(): SynonymActivity
}