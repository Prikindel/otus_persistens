package ru.otus.mynotes

import android.app.Application
import androidx.room.Room
import ru.otus.mynotes.database.NotesDb

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            NotesDb::class.java,
            "notes_db"
        ).build()
    }

    companion object {
        lateinit var db: NotesDb
            private set
    }

    /*@Provide
    @Singleton
    fun provideDb(context: Context): NotesDb {
        return Room.databaseBuilder(
            context,
            NotesDb::class.java,
            "notes_db"
        ).build()
    }*/
}