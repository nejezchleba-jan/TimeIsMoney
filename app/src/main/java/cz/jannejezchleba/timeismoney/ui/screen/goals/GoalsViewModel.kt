package cz.jannejezchleba.timeismoney.ui.screen.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val repository: GoalRepository) : ViewModel() {
    val allGoals: LiveData<List<Goal>> = repository.allGoals
    val pinnedGoals: LiveData<List<Goal>> = repository.allPinnedGoals
    private val _selectedGoal: MutableLiveData<Goal?> = MutableLiveData(null)
    val selectedGoal: LiveData<Goal?> = _selectedGoal

        private var _computedTime = MutableLiveData("0 DAYS")
    val computedTime: LiveData<String> = _computedTime

    fun computeTimeForGoal(dailyWage: Int, price: Double) {
        if (dailyWage == 0 || price == 0.0) _computedTime.value = "0 DAYS"
        else {
            val df = DecimalFormat("#.##")
            val result: Double = price / dailyWage
            _computedTime.value = if (result < 1) {
                df.format(result * 24).toString() + " HOURS"
            } else {
                "${df.format(result)} DAYS"
            }
        }
    }
    fun saveGoal(name: String, price: Int, time: String, imagePath: String) {
        repository.insertGoal(Goal(name = name, price = price, time = time, imagePath = imagePath))
    }


    fun getGoal(id: Int) {
        viewModelScope.launch {
            val goal = repository.getGoal(id)
            _selectedGoal.value = goal
        }
    }

    fun pinGoal(id: Int) {
        viewModelScope.launch {
            repository.changedPinGoal(id, true)
        }
    }

    fun unpinGoal(id: Int) {
        viewModelScope.launch {
            repository.changedPinGoal(id, false)
        }
    }

    fun updateGoal(goal: Goal) {
        viewModelScope.launch {
            repository.updateGoal(goal)
        }
    }

    fun deleteGoal(id: Int) {
        viewModelScope.launch {
            repository.deleteGoal(id)
        }
    }
}