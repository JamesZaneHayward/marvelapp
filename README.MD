# Marvel App
The original tech test design looked like a stock app from android studio so I have taken some liberties with the design to bring it more inline with the Marvel style. Primarily using Red and White to match with the iconic Marvel logo.<br/><br/>
A simple multi-screen app using Marvel's open API, using Kotlin and Clean Architecture. <br/>**Requires a local `keys.properties` file with a MARVEL_SECRET_KEY and MARVEL_PUBLIC_KEY to function. The app will not compile without this file.**

## Purpose
This has been built to display technical proficiency, covers some of the most requested features in tech tests.<br/>
Including, but not limited to:<br/>
-List of objects from API (Recycler View, adapter, and search function)
<br/>-Second fragment calling a second API endpoint
<br/>-Test suite (Unit tests and UI tests)

## Demo gif of the app for those who do not wish to acquire a key
![Marvelapp Demo](demo/marvelapp_demo.gif)

## Architecture
MVVM, Clean Architecture. <br/>
Models are used to define the data structure of the data used in the app, Comic and Hero are data classes containing the necessary data that gets mapped from the JSON. <br/>
Views handle the interactions of the user such as setting the onclicks of buttons. Also observes data in the view-model and handles the displaying of the data to the users.<br/>
View-Models handle the business logic calls sent via the views. Executes use cases and holds live data that the views display. <br/>
App is split into 4 layers: Data, Domain, Presentation, and Ui. <br/>
The app depends on abstractions found in the Domain layer (use cases, repositories) meaning that that modifications to the layers that depend on them should not affect each other as long as the Domain abstractions remain satisfied.

## Notable libraries used
Dagger2 for dependency injection
<br/>Retrofit okhttp for API calls
<br/>Moshi for JSON deserialization
<br/>RxJava and LiveData for a reactive UI
<br/>Picasso for image url handling

## Tests + testing libraries
Unit tests written for the ComicsList feature<br/>
UI tests written for RandomHero feature **To run, please set build variant to `mockDebug` to inject the mocked repositories**<br/>
<br/>Arch for instant executor rule when testing RxJava
<br/>JUnit
<br/>MockK
<br/>Espresso for UI Tests

### TODO:
Here is a list of next steps and issues identified which would be fixed as the next stage pf development were I to spend any more time on this app:<br/>
[ ] Add UI tests to Comics List<br/>
[ ] Add Unit tests to Random Hero<br/>
[ ] Build page for viewing details of a single comic<br/>
[ ] Add click-through to details page from comic list<br/>
[ ] Fix search filter not applying on refresh<br/>
[ ] Fix background resizing on soft keyboard open<br/>
[ ] Cache comics list for better user experience<br/>
