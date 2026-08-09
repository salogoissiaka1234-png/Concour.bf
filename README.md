# ConcoursPro BF — Squelette du projet

Application native Android (Kotlin + Jetpack Compose + Room) pour réviser
les QCM de Culture Générale et Psychotechnique des concours AGRE
(Cycle B IFPB, Infirmiers, Éducateurs de la Petite Enfance).

## Option 1 : Ouvrir dans Android Studio (PC)

1. Ouvre **Android Studio**.
2. `File > Open` → sélectionne le dossier `ConcoursProBF`.
3. Laisse Gradle synchroniser (première fois : peut prendre quelques minutes,
   il télécharge les dépendances).
4. Lance sur un émulateur ou ton téléphone (bouton ▶️ Run).

## Option 2 : Compiler un APK sans PC (GitHub Actions)

Un fichier `.github/workflows/build.yml` est déjà inclus : il dit à GitHub
de compiler automatiquement un APK à chaque fois que tu envoies du code.

**Depuis ton téléphone :**

1. Crée un compte sur [github.com](https://github.com) si tu n'en as pas.
2. Crée un nouveau repository (ex: `ConcoursProBF`), public ou privé.
3. Dans le repo, utilise **Add file > Upload files** pour uploader tout le
   contenu du dossier `ConcoursProBF` (tu peux uploader le zip extrait,
   dossier par dossier si l'upload web ne prend pas les sous-dossiers
   automatiquement — sinon utilise l'appli GitHub qui gère mieux les zips).
4. Une fois uploadé, va dans l'onglet **Actions** du repo : la compilation
   démarre automatiquement (icône jaune = en cours, vert = réussi).
5. Clique sur le run terminé → tu verras un artefact téléchargeable
   `ConcoursProBF-debug-apk` en bas de page → télécharge-le (c'est un .zip
   contenant l'APK).
6. Extrait le .zip, installe le `.apk` sur ton téléphone (autorise les
   sources inconnues si demandé).

⚠️ Cette méthode donne un APK de test signé "debug" (pas encore prêt pour
le Play Store, mais parfait pour tester l'appli maintenant, sans PC).

L'appli démarre déjà fonctionnelle avec 4 questions d'exemple (2 culture
générale, 2 psychotechnique) stockées automatiquement dans la base Room
au premier lancement — de quoi tester le flux complet : accueil → quiz →
correction immédiate → résultat.

## Ce qui est déjà en place

- **Architecture MVVM** : `data/` (Room + Repository), `ui/viewmodel/`,
  `ui/screens/` (Compose), `ui/navigation/`
- **Base de données locale** (Room) → fonctionne 100% hors-ligne
- **Flux complet** : Accueil → Quiz (10 questions aléatoires, correction
  immédiate avec couleurs vert/rouge + explication) → Résultat avec %
- **Thème** vert/or reprenant l'identité de tes autres documents

## Ce qu'il reste à faire ensemble demain

1. **Remplir la vraie banque de questions** (culture générale Burkina +
   psychotechnique logique/calcul) dans `QuestionRepository.kt`,
   idéalement en les import depuis un fichier JSON plutôt qu'en dur dans
   le code, pour pouvoir en ajouter facilement.
2. **Filtrer par concours** : écran de sélection du concours (Cycle B
   IFPB / Infirmiers / Éducateurs) avant de lancer le quiz — la structure
   de données (`Contest`) est déjà prête pour ça.
3. **Suivi de progression** : historique des scores dans le temps (Room
   le permet facilement, on ajoutera une table `QuizAttempt`).
4. **Monétisation** : intégration Google Play Billing pour débloquer les
   concours premium / questions illimitées.
5. **Icône + splash screen** personnalisés.

## Structure des fichiers

```
app/src/main/java/com/ragnard/concoursprobf/
├── data/
│   ├── Question.kt          → modèle d'une question
│   ├── Converters.kt        → conversion List<String>/enum <-> texte pour Room
│   ├── QuestionDao.kt       → requêtes base de données
│   ├── AppDatabase.kt       → configuration Room
│   └── QuestionRepository.kt → logique d'accès aux données + données d'exemple
├── ui/
│   ├── theme/Theme.kt
│   ├── viewmodel/QuizViewModel.kt
│   ├── screens/HomeScreen.kt
│   ├── screens/QuizScreen.kt
│   ├── screens/ResultScreen.kt
│   └── navigation/NavGraph.kt
└── MainActivity.kt
```
