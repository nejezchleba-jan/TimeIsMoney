package cz.jannejezchleba.timeismoney.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.domain.interfaces.GoalDao
import kotlinx.coroutines.*
import javax.inject.Inject


class GoalRepository @Inject constructor(private val goalDao: GoalDao) {
    val allGoals: LiveData<List<Goal>> = goalDao.getAllGoals()
    val allPinnedGoals: LiveData<List<Goal>> = goalDao.getAllPinnedGoals()
    val selectedGoal = MutableLiveData<Goal?>(null)

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertGoal(goal: Goal) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.insertGoal(goal)
        }
    }

    fun updateGoal(goal: Goal) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.updateGoal(goal)
        }
    }

    fun changedPinGoal(id: Int, isPinned: Boolean) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.changedPinGoal(id, isPinned)
        }
    }

    fun deleteGoal(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.deleteGoal(id)
        }
    }

   suspend fun getGoal(id: Int) {
        selectedGoal.value = asyncFind(id).await()
    }

    private fun asyncFind(id: Int): Deferred<Goal?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async goalDao.findGoal(id)
        }
}