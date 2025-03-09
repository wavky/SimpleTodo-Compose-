package com.wavky.simpletodo.app

import android.app.Application
import com.wavky.simpletodo.domain.domainModules
import com.wavky.simpletodo.usecase.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidLogger()
      androidContext(this@AppApplication)
      modules(appModule + useCaseModule + domainModules)
    }
  }
}
