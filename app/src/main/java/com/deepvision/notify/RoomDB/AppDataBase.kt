package com.deepvision.notify.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.deepvision.notify.Util.DATABASE_NAME

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract val notesDao: NotesDao

    //singleton
    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                //if instance is null build and set instance to result
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)

                //build database with pre populated values
                .addCallback(

                    //implementation of an interface

                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            /** workanager which retrieves json data

                            // val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                            // WorkManager.getInstance(context).enqueue(request) **/

                        }
                    }
                )

                .fallbackToDestructiveMigration()
                .build()
        }

    }

}