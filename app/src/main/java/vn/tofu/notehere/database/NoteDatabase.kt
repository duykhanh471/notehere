package vn.tofu.notehere.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao() : NoteDao
//    private class NoteDBCallback(
//        private val scope: CoroutineScope
//    ) : Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let {database ->
//                scope.launch {
//                    val noteDao = database.getNoteDao()
//                    noteDao.deleteAll()
//                    noteDao.insertNote(NoteData(
//                        0,
//                        "First note",
//                        "Ipsum loren etc"
//                    ))
//                    noteDao.insertNote(NoteData(
//                        0,
//                        "Second note",
//                        "Ipsum loren etc"
//                    ))
//                }
//            }
//        }
//    }
    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}

