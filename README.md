Android App "SmartSchool"
======================

# Description
SmartSchool is a project to organise all business processes in schools
and make student's school life easier.

The main idea is to reduce queues in school cafes
by ordering cafe's products using your phone
and to establish strong cooperation between all schools

This project is the Android app for the school's efficiency.

# Running the app
To connect with Server go to the `RetrofitService` class in the `retrofit` package and change IPv4-address to yours.

To login in the app use 
- email `test1@` and password `user` for USER role.
- email `test2@` and password `elder` for ELDER role.
- email `test3@` and password `teacher` for TEACHER role.
- email `test4@` and password `staff` for STAFF role.
- email `test5@` and password `director` for DIRECTOR role.

# Features
The app displays a menu of cafe products that users can order. Users have their own roles (user, elder, teacher, staff, director). 
Users with specifiec role can do electronic applications, assign users to the roles, view all orders and applications that already have been made.
<div>
  <img align="center" src="menu.jpg" alt="Menu screenshot" height="640" width="320">
</div>

# Development Environment
The app is written entirely in Java with Spring Framework and uses the Gradle build system.

To build the app, use the `gradlew build` command or use "Import Project" in
Android Studio.
