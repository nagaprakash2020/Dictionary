package com.learn.dictionary.dagger

import android.content.Context
import com.learn.dictionary.DictionaryApp
import com.learn.dictionary.DictionaryViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ViewModelModule::class,
        NetworkModule::class, ActivityBuilder::class]
)
interface ApplicationComponent : AndroidInjector<DictionaryApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun provideFactory(): DictionaryViewModelFactory
}