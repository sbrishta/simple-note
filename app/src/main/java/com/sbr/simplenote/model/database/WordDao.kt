package com.sbr.simplenote.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sbr.simplenote.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word:Word)

    @Query ("DELETE FROM word_table")
    suspend fun deleteAll()

    @Query ("SELECT * FROM word_table ORDER BY word ASC" )
    fun getAllWords(): Flow<List<Word>>
}