# MatchScores
MatchScores App is a sample platform which provides information about sport news, scores and standings.

## UI
MatchScores application consist of 5 different fragments and 1 root activity. Activity holds a container layout in order to manage fragments which will be controlled by navigation component.

[APK Link (https://drive.google.com/file/d/1lliVR5oNA3JT2NieSg_5rmSA5KyZrIwD/view?usp=sharing)](https://drive.google.com/file/d/1lliVR5oNA3JT2NieSg_5rmSA5KyZrIwD/view?usp=sharing)

Fragments :
* SplashFragment
* NewsFragment
* NewsDetailFragment
* ScoresFragment
* StandingsFragment

## App Video

<img src="https://user-images.githubusercontent.com/21987335/116003766-1360eb00-a608-11eb-9acf-6436f8c0ccbc.gif" width="400"/>

## 3rd party lib. usages & Tech Specs
* Patterns
    - MVVM design pattern
    - Repository pattern for data management
* JetPack Libs
    - Navigation Component
* Bottom Navigation with Navigation Component
* Retrofit
* Kotlin Coroutines
* LiveData
* Facebook Shimmer Lib.
* Glide image loading 
* Lottie animation Lib.
* Moshi Json handler
* Timber Client logging
* Dependency Injection (HILT) 
* DataBinding, ViewBinding
* Visitor Parttern for Recycler View
* RecyclerView with List Adapter and DiffUtil
* Single Activity multiple Fragments approach
* Unit testing samples & HILT integrations for testing
* MockK library for unit testing
* Junit5
* Thruth (assertions)

## Developer Comments
According to task section application is developed. rather than creating a dropdown menu, bottom navigation view (with navigation component) is used on application sample.

Feature layers are separated with MVVM design parttern, Data layers are separated with Repository pattern and multiview usage on RecyclerView is separetad with Visitor pattern. 

Unit test cases is created with MockK Lib. 

## Task

The complete application has 3 different sections (only 2 can be selected fornow), each of which can be selected from a drop down menu. These sections are categorized as:
1. News
2. Scores

You need to develop the drop down menu, and then implement each section so that it can be launched from the drop down menu.

#### News :
The application should load this page on startup, or from subsequent requests for News from the drop down menu. The main menu drops down or hide when the
button is toggled. Each option redirects to an independent view and controller.The options are News, Scores Standings. “Standings” should only implemented as drop
down menu item, corresponding page should not be implemented.

Feed Url : https://demo1899326.mockable.io/news

News Details :
* The feed is an REST feed.
* It should be possible to link through to the article detail page, which is a webview with the url in the “link” attribute.
* The application should load the article detail webview inside the application when the user clicks on the item from the news listing
* (Do not worry too much about getting the webview scaled correctly providing it shows correctly on one device)
* Thumbnails should be retrieved from “picURL” attribute. It can be cropped.

<img src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/wireframe1.png" width="300">

#### Scores :
The scores page shows live scores from a number of different soccer matches. The page should auto-refresh, and the feed is setup to give different values every time the
feed refreshes. This would help you to debug the application and ensure the model values are updated.

Feed URL : https://demo1899326.mockable.io/matches

Scores Details :
* The table should show all matches in the feed
* The page should have the date in as the header, taken from the attribute “date” in parent.
* Each row should show home team, away team, and the scores taken from the fs_A and fs_B attributes.
* The page should auto-refresh every 30 seconds.

<img src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/wireframe2.png" width="300">


#### ScreenShots

<img src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/1.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/2.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/3.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/4.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/5.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/6.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/7.png" width="200">   <img
src="https://github.com/AttilaAKINCI/MatchScores/blob/main/images/8.png" width="200">


# License

The code is licensed as:

```
Copyright 2021 Attila Akıncı

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
