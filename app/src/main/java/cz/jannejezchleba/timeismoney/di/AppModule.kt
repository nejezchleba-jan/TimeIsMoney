package cz.jannejezchleba.timeismoney.di

import android.content.Context
import androidx.room.Room
import cz.jannejezchleba.timeismoney.data.db.GoalRoomDatabase
import cz.jannejezchleba.timeismoney.data.domain.interfaces.GoalDao
import cz.jannejezchleba.timeismoney.util.DataStoreHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesGoalRoomDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GoalRoomDatabase::class.java,
        "goal_database"
    ).build()

    @Singleton
    @Provides
    fun provideGoalDao(db: GoalRoomDatabase): GoalDao = db.getGoalDao()

    @Singleton
    @Provides
    fun providesDataStoreHelper( @ApplicationContext app: Context): DataStoreHelper = DataStoreHelper(app)
}