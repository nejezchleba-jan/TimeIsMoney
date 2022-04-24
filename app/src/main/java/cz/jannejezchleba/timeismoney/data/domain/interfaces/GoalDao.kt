package cz.jannejezchleba.timeismoney.data.domain.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cz.jannejezchleba.timeismoney.data.domain.Goal

@Dao
interface GoalDao {
    @Insert
    fun insertGoal(goal: Goal)

    @Query("SELECT * FROM goals WHERE goalId = :id")
    fun findGoal(id: Int): Goal

    @Query("DELETE FROM goals WHERE goalId = :id")
    fun deleteGoal(id: Int)

    @Query("SELECT * FROM goals")
    fun getAllGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals WHERE isPinned = 1")
    fun getAllPinnedGoals(): LiveData<List<Goal>>
}