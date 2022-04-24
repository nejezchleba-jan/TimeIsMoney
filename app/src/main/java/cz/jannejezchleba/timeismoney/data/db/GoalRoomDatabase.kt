package cz.jannejezchleba.timeismoney.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.domain.interfaces.GoalDao

@Database(entities = [(Goal::class)], version = 1, exportSchema = false)
abstract class GoalRoomDatabase: RoomDatabase() {
    abstract fun getGoalDao(): GoalDao
}