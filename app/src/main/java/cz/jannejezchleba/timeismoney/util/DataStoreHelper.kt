package cz.jannejezchleba.timeismoney.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHelper(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userData")
        val IS_USER_NEW= booleanPreferencesKey("is_first_start")
        val USER_SALARY_MONTH = stringPreferencesKey("user_salary_month")
        val USER_VACATION_DAYS = stringPreferencesKey("user_vacation_days")
        val USER_WORK_HOURS= stringPreferencesKey("user_work_hours")
        val USER_HOUR_RATE= stringPreferencesKey("user_hours_rate")
        val USER_SALARY_TYPE= booleanPreferencesKey("user_salary_type")
        val USER_DAILY_WAGE= intPreferencesKey("user_daily_wage")
    }

    val getUserIsNew: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[IS_USER_NEW] ?: true
        }

    suspend fun saveUserIsNew(isFirst: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_NEW] = isFirst
        }
    }

    val getSalary: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_SALARY_MONTH] ?: ""
        }

    suspend fun saveSalary(salary: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_SALARY_MONTH] = salary
        }
    }

    val getVacation: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_VACATION_DAYS] ?: ""
        }

    suspend fun saveVacation(vacation: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_VACATION_DAYS] = vacation
        }
    }

    val getHours: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_WORK_HOURS] ?: ""
        }

    suspend fun saveHours(hours: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_WORK_HOURS] = hours
        }
    }

    val getHourRate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_HOUR_RATE] ?: ""
        }

    suspend fun saveHourRate(hourRate: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_HOUR_RATE] = hourRate
        }
    }

    val getSalaryType: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_SALARY_TYPE] ?: true
        }

    suspend fun saveSalaryType(isSalary: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_SALARY_TYPE] = isSalary
        }
    }

    val getDailyWage: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_DAILY_WAGE] ?: 0
        }

    suspend fun saveDailyWage(wage: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_DAILY_WAGE] = wage
        }
    }
}