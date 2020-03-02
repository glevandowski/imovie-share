IMovieShare
=====================================================

 The imovieshare app keeps you informed of the recent and upcoming films to be released.
In it you get information such as: overview, average votes, category and release date of the film.

Home screen       |  Details movie screen
:-------------------------:|:-------------------------:
![](https://github.com/glevandowski/IMovieShare/blob/master/screenshots/Screenshot_20200301-230806.png)  |  ![](https://github.com/glevandowski/IMovieShare/blob/master/screenshots/Screenshot_20200301-230810.png)

About used libraries
---------------

Render images:
- [Picasso](https://square.github.io/picasso/)

Architecture components - Jetpack:
- [Paging - Jetpack](https://developer.android.com/topic/libraries/architecture/paging)
- [ViewModel - Jetpack](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Navigation component - Jetpack](https://developer.android.com/guide/navigation)

Reactivity streams:
- [RXJava](http://reactivex.io/)
- [LiveData - Jetpack](https://developer.android.com/topic/libraries/architecture/livedata)

Dependency injection:
- [Dagger](https://square.github.io/dagger/)

Request and serialize/deserialize:
- [Gson](https://github.com/google/gson)
- [Retrofit](https://square.github.io/retrofit/)

Instrumented tests:
- [Espresso](https://developer.android.com/training/testing/espresso)
- [Mockito](https://site.mockito.org/)
- [Fragment testing](https://developer.android.com/training/basics/fragments/testing)

About used API
---------------
Using the API of the movie database api, here you can find the documentation:
- https://developers.themoviedb.org/3/getting-started/introduction

About architecture
---------------
 This project use Android Architecture Components (View Model, Live Data, Paging library) and Dagger 2 dependency injection to build a robust application (scalable, testable, less bug and easy to maintain).

 The entire architecture was based on the mvvm standard. Official google app architecture guide.:
- https://developer.android.com/jetpack/docs/guide

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

Getting started
---------------

This project uses the Gradle construction system.

1. Download by cloning this repository.
2. In Android Studio, select the `IMovieShare` directory that you downloaded.
3. If a gradient setting is requested, accept the default settings.
Alternatively, use the "gradlew build" command to build the project directly.
4. Add your API key to your application's `gradle.properties` file

Need help with a problem?
-------

Contact me.

- Linkedin: https://www.linkedin.com/in/gustavo-levandowski/
- Email: gustavolevandowski@gmail.com

