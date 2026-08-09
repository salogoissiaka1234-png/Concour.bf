package com.ragnard.concoursprobf.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromChoicesList(choices: List<String>): String = choices.joinToString("|||")

    @TypeConverter
    fun toChoicesList(data: String): List<String> = data.split("|||")

    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(value: String): Category = Category.valueOf(value)

    @TypeConverter
    fun fromContest(contest: Contest): String = contest.name

    @TypeConverter
    fun toContest(value: String): Contest = Contest.valueOf(value)
}
