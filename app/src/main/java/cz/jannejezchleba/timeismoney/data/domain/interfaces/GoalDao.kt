package cz.jannejezchleba.timeismoney.data.domain.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cz.jannejezchleba.timeismoney.data.domain.Goal

@Dao
interface GoalDao {
    @Insert
    fun insertGoal(goal: Goal)

    @Update
    fun updateGoal(goal: Goal)

    @Query("UPDATE goals SET isPinned = :isPinned WHERE goalId = :id")
    fun changedPinGoal(id: Int, isPinned: Boolean)

    @Query("SELECT * FROM goals WHERE goalId = :id")
    fun findGoal(id: Int): LiveData<Goal>

    @Query("DELETE FROM goals WHERE goalId = :id")
    fun deleteGoal(id: Int)

    @Query("SELECT * FROM goals")
    fun getAllGoals(): LiveData<List<Goal>>

    @Query("SELECT * FROM goals WHERE isPinned = 1")
    fun getAllPinnedGoals(): LiveData<List<Goal>>
}