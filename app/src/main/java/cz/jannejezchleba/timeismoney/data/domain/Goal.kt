package cz.jannejezchleba.timeismoney.data.domain

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "goalId")
    var id: Int? = null,

    var name: String = "",
    var price: Int = 0,
    var imagePath: String = "",
    var currency: String = "CZK",
    var dateCreated: String = LocalDate.now().toString(),
    var isPinned: Boolean = false
)