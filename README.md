# Detoxiom
Detoxiom is an Open source Android app that makes social media detox easy with some simple gamification methods.

Language of the App is Farsi and if you look at this repo there is a high chance your first language is the same, well the good news is i write a LONG blog post about the production [here](sadraa.me/detoxiom) in Farsi :).

## Introduction
Detoxiom makes a widget looks like any addictive social media, but when you click on it, instead of those time consumer apps Detoxiom  will be open. 
<p align="center"> 
<img src="https://github.com/01sadra/Detoxiom/blob/master/app/src/main/res/drawable/teaching_app.gif">
</p>

Then you can try your chance for achiving a quote.It's a mini game and make the detox easier.I write a lot in the above post about gamification in detxiom but you can seek more info in this [life changig book](https://www.amazon.com/Power-Habit-What-Life-Business/dp/081298160X) and also [this one](https://www.amazon.ca/Hooked-How-Build-Habit-Forming...ebook/.../B00HJ4A43S).


## Screen shots

![screen 3](https://sadraa.me/wp-content/uploads/2017/12/Screenshot-from-2017-12-03-07-01-29.png)
![screen 2](https://sadraa.me/wp-content/uploads/2017/12/Screenshot-from-2017-12-03-07-02-44.png)

## Major dependecies
I used [Dagger 2](https://github.com/google/dagger) for dependency injection and butterknife for view injection. (God bless @JakeWharton)

also I used RxJava, RxAndroid and RxBinding libraries for "reactive programming".

<p align="center"> 
<img src="https://sadraa.me/wp-content/uploads/2017/12/reactive-programming.gif">
</p>


Also:
[Retrofit](https://github.com/square/retrofit) for networking 

[Room ORM](https://developer.android.com/topic/libraries/architecture/room.html) for database(taste like sugar ;))

[Lottie](https://github.com/airbnb/lottie-android) for animations

Also I used MVP pattern for Detoxiom. Maybe you find it a little weird, because I create a new Intractor for every fragment and it seems anti pattern. Honesty I'm feeling more comfortable in this way but I try to find(or create) better solutions for MV* in android.

## Contribution
I'm a lasy guy, I don't write even 1 line test for detoxiom and there is no build pass. If you send a pull request Please make sure it will work correctly and atleast app will not crash. Any kind of contribution will appriciate :heart: (specially typos :) ).

## Download 
You can get it from [CoffeBazar]().

