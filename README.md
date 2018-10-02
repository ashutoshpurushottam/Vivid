# Vivid
----
Vivid is an Android application written for training purpose. It has been developed to teach Android concepts like RxJava, MVP, and Dependency Injection. The associated tutorials are hosted at this [blog](https://eigendaksh.blogspot.in/2017/12/part-1-advanced-android-tutorial.html).

## First Screen
----

![Image of First Screen](https://github.com/ashutoshpurushottam/Images/blob/master/medium1.png?raw=true)

In the first screen, the app reads your location and fetches your weather information using [OpenWeatherMap API](https://openweathermap.org/api). It uses RxJava and Retrofit for this purpose. It also uses Room ORM to store your location and weather information. On clicking an item of the RecyclerView, a new screen will open and show details of the weather for the day. This screen design has been taken from Udacity's Sunshine application. I have made little modifications to it.  

## Second Screen
----

![Image of Second Screen](https://github.com/ashutoshpurushottam/Images/blob/master/medium2.png?raw=true)

The second screen displays all the notes that you have added. There is a FAB button which lets you open a new screen and add a new note. This screen demonstrates how to use Room library for storing data in app DB. It also demonstrates how you can stick to MVP pattern in a RecyclerView. 

## Info
----
As I take more classes and teach students new concepts, this application code will be updated. 
