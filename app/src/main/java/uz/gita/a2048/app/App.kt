package uz.gita.a2048.app

import android.app.Application
import uz.gita.a2048.repository.AppRepository
import uz.gita.a2048.settings.Settings

class App : Application() {
    override fun onCreate() {
        Settings.init(this)
        AppRepository.init(this)
        super.onCreate()
    }
}