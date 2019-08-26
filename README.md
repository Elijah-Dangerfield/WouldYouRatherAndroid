# WouldYouRatherAndroid
This project is my first mobile application on the Google Play Store.

### TechStack
##### Kotlin
##### Firebase
##### Jetpack's: Navigation, View Model, Live Data

My goal in this project was to focus on a clean modern Android application following modern practices. That being said the seperation
of concerns can be explained below

## Files:
### view/GameFragment.kt
This fragment controls displaying the fetched questions and responding to user interatctions

### view/StartFragment.kt
This fragment controls displaying the options for packs and setting up the view model for the game to start

### viewmodel/GameViewModel.kt
This file is responible for fetching Questions from Firebase and exposing it as liveData

### model/Question.kt
This class is reponsible for modeling the shape of the data pulled from Firebase
