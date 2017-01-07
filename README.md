# CleanBuzz
<sup>If you can't be bothered to read, scroll down to the TLDR at the bottom of the page.</sup>

## Introduction

I'll let the app speak for itself first.

![15](https://raw.githubusercontent.com/zsmb13/CleanBuzz/master/docs/15.png)

So it's *just* FizzBuzz! You enter a number and you get the FizzBuzz-ified version back.

I know what you're thinking, this is simple. You could probably write this in 30 minutes, it's a layout file and a single 100-ish line Java Activity. 

## Implementation details
Of course, this *simple* app is more than meets the eye. Quite clearly inspired by [FizzBuzzEnterpriseEdition](https://github.com/EnterpriseQualityCoding/FizzBuzzEnterpriseEdition), this application is an extremely contrived example for using popular/trending/fancy Android development techniques and tools.

Instead of the aforementioned 100 lines of Java, this project is ~600 lines of code spread across ~30 files and many packages. But it's all for good (?) reason. 

### Language
And all those lines aren't even Java boilerplate, because the entire app is written in Kotlin to practice the language.

Kotlin is a language for the JVM created by [JetBrains](https://www.jetbrains.com/) (makers of IntelliJ IDEA, ReSharper, and more), which is highly interoperable with Java, and provides very nice syntax that's actually enjoyable to write code in. It's also quite legible if you're a Java developer, just try reading some of it.

Anyhow, you can learn more about it on the [official website](http://kotlinlang.org/).

### Architecture
The app is built with Clean architecture, more specifically along [Marko Milos](https://github.com/MarkoMilos)'s interpretation of it. You can watch the very underrated presentation he gave about it at [droidcon Zagreb 2016](http://droidcon.hr/) on YouTube [here](https://www.youtube.com/watch?v=3Mq5newPdck).

Here's just a high level overview - it's fairly close to most Clean architecture implementations, and it uses MVP for the presentation layer. Watch the presentation linked above for more details.
![architecture](https://raw.githubusercontent.com/zsmb13/CleanBuzz/master/docs/architecture.PNG)

### Libraries
- [RxJava](https://github.com/ReactiveX/RxJava): Reactive programming is getting more and more popular in every language and on every platform, and Android is no exception, so it would be a shame to miss out on it.
- [RxAndroid](https://github.com/ReactiveX/RxAndroid): Mandatory addon to the previous library, provides Android specific schedulers.
- [RxKotlin](https://github.com/ReactiveX/RxKotlin): Kotlin-friendly extensions for RxJava.
- [Anko](https://github.com/Kotlin/anko): The definitive Android library for Kotlin, with many utilities, generally making life easier.
- [Dagger 2](https://google.github.io/dagger/):  The go-to dependency injection framework.
- [Retrofit](https://square.github.io/retrofit/): Network communication library by Square.

##### Network communication?!
Yes, although it's not what you might think, the app isn't phising for your personal information. 

What it does do is use [buzzcloud.xyz](http://buzzcloud.xyz/)'s REST API. This wonderful websites offers free FaaS (FizzBuzz as a service), and thus provides the application's business logic.

## TODOs
- Tests. It's probably coming, it's just not here yet.

## More screenshots
![5](https://raw.githubusercontent.com/zsmb13/CleanBuzz/master/docs/5.png)
![8](https://raw.githubusercontent.com/zsmb13/CleanBuzz/master/docs/8.png)
![9](https://raw.githubusercontent.com/zsmb13/CleanBuzz/master/docs/9.png)

## TL;DR
This is a simple FizzBuzz app that uses Kotlin, Clean architecture, MVP, RxJava, Dagger, Retrofit, and a REST API for the business logic.
