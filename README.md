# Detoxiom
[![GitHub issues](https://img.shields.io/github/issues/01sadra/Detoxiom.svg?style=plastic)](https://github.com/01sadra/Detoxiom/issues)
[![GitHub license](https://img.shields.io/github/license/01sadra/Detoxiom.svg?style=plastic)](https://github.com/01sadra/Detoxiom)
[![Twitter](https://img.shields.io/twitter/url/https/github.com/01sadra/Detoxiom.svg?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2F01sadra%2FDetoxiom)

<p align="center"> 
<img src="https://sadraa.me/wp-content/uploads/2017/12/quote.png" height="250" width="250">
</p>


Detoxiom is an Open source Android app that makes social media detox easy with some simple gamification methods.

Language of the App is Farsi and if you look at this repo there is a high chance that your first language is the same. 

Well, the good news is I wrote a LONG blog post about the production [here](https://sadraa.me/detxiom/), in Farsi :).

## Introduction
Detoxiom can make a widget that seems look like any of your apps, and when you click on it, instead of those time waster apps Detoxiom will open. 
<p align="center"> 
<img src="https://github.com/01sadra/Detoxiom/blob/master/app/src/main/res/drawable/teaching_app.gif">
</p>

Then you can try your chance of achieving a quote. It's a mini-game and makes the detox easier. I wrote a lot about gamification in Detxiom in the above post but you can seek more info in this [life-changing book](https://www.amazon.com/Power-Habit-What-Life-Business/dp/081298160X) and also [this one](https://www.amazon.ca/Hooked-How-Build-Habit-Forming-Products-ebook/dp/B00HJ4A43S).

## Screenshots
<p align="center"> 
<img src="https://sadraa.me/wp-content/uploads/2017/12/Screenshot-from-2017-12-03-07-01-29.png">
<img src="https://sadraa.me/wp-content/uploads/2017/12/Screenshot-from-2017-12-03-07-02-44.png">
</p>

## Dependecies
I used [Dagger 2](https://github.com/google/dagger) for dependency injection and Butterknife for view injection. (@GodBless   JakeWharton)

and I used RxJava, RxAndroid and RxBinding libraries for "reactive programming".

<p align="center"> 
<img src="https://sadraa.me/wp-content/uploads/2017/12/reactive-programming.gif">
</p>


And:

[Retrofit](https://github.com/square/retrofit) for networking 

[Room ORM](https://developer.android.com/topic/libraries/architecture/room.html) for database(taste like sugar ;))

[Lottie](https://github.com/airbnb/lottie-android) for animations

Also, I used MVP pattern for Detoxiom. Maybe you find it a little weird because I create a new Interactor for every fragment and it seems anti-pattern. Honestly, I'm feeling more comfortable in this way but I will try to find(or create) better solutions for MV* in Android.

## Contribution
I'm a [lazy guy](https://sadraa.me/%D8%AA%DA%A9%D9%86%DB%8C%DA%A9-%D9%BE%D9%88%D9%85%D9%88%D8%AF%D8%B1%D9%88-%D9%88-%D8%B2%D9%86%D8%AC%DB%8C%D8%B1%D9%87-%D8%B9%D8%A7%D8%AF%D8%AA-%D8%B3%D8%A7%DB%8C%D9%86%D9%81%DB%8C%D9%84%D8%AF/), I didn't write tests for Detoxiom and there is no CI process. If you send a pull request Please make sure your patch will work correctly and  app will not crash at least. Any kind of contribution will be appreciated. :heart: (specially typos :) ).

## Download 
You can get it from [cafebazaar](https://cafebazaar.ir/app/me.sadraa.detoxiom/?l=fa).

