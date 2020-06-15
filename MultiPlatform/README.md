# Prerequisites
Android Studio `3.5.x`
XCode `11.3.x`

# Structure
The project has three main subfolders:
* `app` is the Android app
* `ios` is the iOS app
* `shared` is the **Kotlin Multiplatform** library that's accessible to both apps

# Setup

You'll need to edit or override `local.properties` to point to your own Android SDK directory

To build the **Koltin Multiplatform** library you might want to run `./gradlew sharedcode:packForXCode` at least once.

# Cristian's Challenge

I made a To Do list app that runs without any problem in Android, it has all the logic in the shared section so if we have an iOS project that creates it
owns view it should be working without any problem, it just have to use the methods I defined in Task Controller and display the returned data. In this case
since I don't own a Macbook or have the change to have xcode running I couldn't make the iOS UI.

I tried to make this project following the base that all the logic should be shared and each platform should be responsible to present the date that will return
the shared code. To interact with the Shared code I follow a MVC because at least for what I saw we can not use a ViewModel in that section, at least we can not extend from that class
and considering for this kind of thing is easier and cleaner to implement a MVC I make it that way.

For all relate Android implementation that it is not common logic I implemented an MVVM, I made it this way to be able to use data binding and simplify the communication between the
viewModel and the layout. I created some Base classes, one for the Activity and another to the viewModel, in which we have all the common code for those kind of classes, I like to do this
to re-utilise the common code through those classes. I know that in this case it may be redundant but I made this project thinking in the future and considering that we may have another
activities, such as Task detail in which we can see a description of the task and edit it if we want, so in that case we will reuse a lot of code.