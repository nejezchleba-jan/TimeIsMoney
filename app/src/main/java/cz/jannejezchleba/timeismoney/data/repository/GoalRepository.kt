package cz.jannejezchleba.timeismoney.data.repository

import androidx.lifecycle.LiveData
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.domain.interfaces.GoalDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class GoalRepository @Inject constructor(private val goalDao: GoalDao) {
    val allGoals: LiveData<List<Goal>> = goalDao.getAllGoals()
    val allPinnedGoals: LiveData<List<Goal>> = goalDao.getAllPinnedGoals()

    val searchResult = MutableStateFlow<Goal?>(null)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertGoal(goal: Goal) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.insertGoal(goal)
        }
    }

    fun deleteGoal(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            goalDao.deleteGoal(id)
        }
    }

    fun findGoal(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = asyncFind(id)
        }
    }

    private suspend fun asyncFind(id: Int): Goal =
        coroutineScope.async(Dispatchers.IO) {
            return@async goalDao.findGoal(id)
        }.await()
}