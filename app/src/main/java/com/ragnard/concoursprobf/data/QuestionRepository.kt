package com.ragnard.concoursprobf.data

class QuestionRepository(private val dao: QuestionDao) {

    suspend fun getQuestions(category: Category, contest: Contest): List<Question> {
        return dao.getByCategoryAndContest(category, contest)
    }

    suspend fun getRandomQuiz(limit: Int = 20): List<Question> {
        return dao.getRandomQuestions(limit)
    }

    suspend fun seedIfEmpty() {
        if (dao.count() == 0) {
            dao.insertAll(sampleQuestions())
        }
    }

    private fun sampleQuestions(): List<Question> = listOf(
        Question(
            category = Category.CULTURE_GENERALE,
            contest = Contest.GENERAL,
            theme = "Géographie du Burkina Faso",
            statement = "Quel fleuve ne traverse PAS le Burkina Faso ?",
            choices = listOf("Le Mouhoun", "Le Nakambé", "Le Nil", "Le Nazinon"),
            correctIndex = 2,
            explanation = "Le Nil coule en Afrique de l'Est/du Nord-Est. Les fleuves du Burkina sont le Mouhoun, le Nakambé et le Nazinon (anciens Volta Noire, Blanche, Rouge)."
        ),
        Question(
            category = Category.CULTURE_GENERALE,
            contest = Contest.GENERAL,
            theme = "Histoire du Burkina Faso",
            statement = "En quelle année la Haute-Volta a-t-elle été renommée Burkina Faso ?",
            choices = listOf("1960", "1984", "1987", "1991"),
            correctIndex = 1,
            explanation = "Le pays a été renommé Burkina Faso le 4 août 1984, sous Thomas Sankara."
        ),
        Question(
            category = Category.PSYCHOTECHNIQUE,
            contest = Contest.GENERAL,
            theme = "Suites logiques",
            statement = "Complète la suite : 2, 4, 8, 16, ...",
            choices = listOf("18", "24", "32", "20"),
            correctIndex = 2,
            explanation = "Chaque nombre est multiplié par 2 : 16 × 2 = 32."
        ),
        Question(
            category = Category.PSYCHOTECHNIQUE,
            contest = Contest.GENERAL,
            theme = "Calcul mental",
            statement = "Un article coûte 4500 FCFA. Avec une remise de 20%, quel est le nouveau prix ?",
            choices = listOf("3600 FCFA", "3800 FCFA", "4000 FCFA", "3200 FCFA"),
            correctIndex = 0,
            explanation = "20% de 4500 = 900. 4500 - 900 = 3600 FCFA."
        )
    )
}
