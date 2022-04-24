package cz.jannejezchleba.timeismoney.ui.screen.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.jannejezchleba.timeismoney.data.domain.Goal
import cz.jannejezchleba.timeismoney.data.repository.GoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val repository: GoalRepository) : ViewModel() {
    val allGoals: LiveData<List<Goal>> = repository.allGoals

}