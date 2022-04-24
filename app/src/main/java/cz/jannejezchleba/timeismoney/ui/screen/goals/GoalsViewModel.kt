package cz.jannejezchleba.timeismoney.ui.screen.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val repository: GoalRepository) : ViewModel() {
    val allGoals: LiveData<List<Goal>> = repository.allGoals
    val pinnedGoals: LiveData<List<Goal>> = repository.allPinnedGoals

    private var _computedTime = MutableStateFlow("0 DAYS")
    val computedTime: StateFlow<String> = _computedTime

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _goal = MutableLiveData<Goal?>(null)
    var goal: LiveData<Goal?> = _goal

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
            _goal.value = repository.findGoal(id).value
        }
    }

    fun pinGoal(id: Int) {
        repository.changedPinGoal(id, true)
    }

    fun unpinGoal(id: Int) {
        repository.changedPinGoal(id, false)
    }

    fun updateGoal(goal: Goal) {
        repository.updateGoal(goal)
    }

    fun deleteGoal(id: Int) {
        repository.deleteGoal(id)
    }
}