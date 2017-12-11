# User Registration, Email Verification, Resend Verification Email and Authentication using Firebase

An Android App that allows users in an organization to register for a service using only the email provided to
them by the organization after which users should verify their email, this is a compulsory step as they won't 
be able to use the service if email isn't verified. Users that don't get the verification mail can also resend
the mail using the link on RegisterActivity.

NOTE: For you to have a fully functional project that you can test, go to  https://console.firebase.google.com
 create a project and setup your app, download your custom * google-services.com * file and use it to replace
 mine found inside the app folder without doing this you won't be able to test this project. Also include the
 necessary firebase configurations in *build.gradle* files

#Requirements
** Android Version 26 ("Oreo")**
** Firebase Version 11.0.4 **
** Android Studio Version 2.3 **
** Firebase SDK **
** Google Play Services SDK **
** Samsung Galaxy J5 **

#Main Classes and Methods 
* LoginActivity.java
* RegisterActivity.java
* ResendVerificationDialog.java