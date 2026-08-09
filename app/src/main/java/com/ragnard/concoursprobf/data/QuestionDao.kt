package com.ragnard.concoursprobf.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)

    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<Question>

    @Query("SELECT * FROM questions WHERE category = :category")
    suspend fun getByCategory(category: Category): List<Question>

    @Query("SELECT * FROM questions WHERE contest = :contest OR contest = 'GENERAL'")
    suspend fun getByContest(contest: Contest): List<Question>

    @Query("SELECT * FROM questions WHERE category = :category AND (contest = :contest OR contest = 'GENERAL')")
    suspend fun getByCategoryAndContest(category: Category, contest: Contest): List<Question>

    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestions(limit: Int): List<Question>

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun count(): Int
}
