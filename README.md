# iDemo
Android application for demo purposes.

![alt text](https://github.com/johnguild/iDemo/blob/master/Screenshot_1.png "Home Screen shot")

![alt text](https://github.com/johnguild/iDemo/blob/master/Screenshot_2.png "Details Screen shot with description")

![alt text](https://github.com/johnguild/iDemo/blob/master/Screenshot_3.png "Details Screen shot without description")

What the app can do?

    1. Show search result list from [itunes search api](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching).
    2. Search using term.
    3. View selected track details.

App Capabilities (Persistence).

    1. Search results list shows artwork/image has a placeholder  while loading and fallback image and when source error encountered.
    2. The app caches the last search results on local database and can be viewed even if the device is offline.
    3. The app remembers when the user views specific track details. When the user closed the app while viewing details and reopen it, the user will be shown the track details when he/she left.
    4. The app will show errors when having trouble loading content from the server.
    5. When searching failed due to network error the app will try to resume search action after turning on the wifi and coming back to the app.

Architecture.

    The app uses [MVVM pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) one of the Googles suggested [architecture](https://developer.android.com/jetpack/docs/guide).

    Main Pros is that Googles MVVM originally created to persist data whenever the Activity is forced to be killed either by system or User intervention.
    Usually when device is rotated on different orientation.

    Another Pros of using this is that Google provides open source components [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) that will make the project maintainable, scalable and testable.

    Cons is Google often depreciates existing Classes for improvement and security purposes then your projects needs to be updated before they drop the support for it.

    This project also uses Visitable approach in usage of Android's RecyclerView and ViewHolder.

    The project also has 3 types of Models, Network Objects, Database Objects, and App Objects. In this manner we separate all the data handling for 3 different scenarios. Fetching data to server, Saving/Updating to local database, and Displaying data on app ui.


Internal Documentation.
    Source code contains comments and instruction how or why it was structured or designed like that.

