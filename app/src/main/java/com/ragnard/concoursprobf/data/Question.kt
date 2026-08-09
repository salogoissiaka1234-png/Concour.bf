package com.ragnard.concoursprobf.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Catégorie principale d'une question.
 * CULTURE_GENERALE : questions classiques (histoire, géo, institutions du Burkina...)
 * PSYCHOTECHNIQUE  : logique, suites numériques, raisonnement, calcul mental
 */
enum class Category {
    CULTURE_GENERALE,
    PSYCHOTECHNIQUE
}

/**
 * Concours ciblé, pour pouvoir filtrer les questions par examen.
 */
enum class Contest {
    CYCLE_B_IFPB,
    INFIRMIERS,
    EDUCATEURS_PETITE_ENFANCE,
    GENERAL // utile pour les questions communes à plusieurs concours
}

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: Category,
    val contest: Contest,
    val theme: String,
    val statement: String,
    val choices: List<String>,
    val correctIndex: Int,
    val explanation: String? = null
)
