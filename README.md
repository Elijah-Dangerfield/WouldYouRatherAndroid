# WouldYouRatherAndroid
This project is my first mobile application on the Google Play Store. You can find the bad boy right here: [super dope link you need to click](https://play.google.com/store/apps/details?id=com.dangerfield.wouldyourather)

![](https://firebasestorage.googleapis.com/v0/b/github-images.appspot.com/o/Screen%20Shot%202019-12-31%20at%2014.37.29.png?alt=media&token=4c6c3ad1-8d4e-4082-bd53-104b2a8cf313)

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

### custom/*
These files are used as helpers to make sure my code is clean :)
