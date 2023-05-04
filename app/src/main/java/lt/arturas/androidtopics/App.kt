package lt.arturas.androidtopics

import android.app.Application
import timber.log.Timber
import java.util.*

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}