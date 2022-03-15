# WorkoutData-SQLite

The primary purpose of this assignment is to have you create and use a SQLite database and ListViews (or RecyclerViews). 
This document contains information about the functionality that your app needs to include. Your version can have a different 
look and feel but needs to serve the same purpose and demonstrate the basic skills. 
You may use any API up to 9.0.

The app should be fairly simple but usable. Since the purpose is to keep information about what exercises you need to do and 
information about these exercises (weight, reps, ...), it is that kind of information you will need to keep in a database. 
Getting this running is a little more complicated than it sounds but it is still mostly database work.

Requirements

- There should be a list (using listview or recycleview) of saved exercises in your database. 
- You must use a database – when changes are made while using the app, the changes should be ‘permanent’.
- There should be two modes
  - creating/deleting/updating a core list of exercises 
  - giving the user a way to use these core exercises to create and execute a workout. If you
    want created workouts to be saved, this is fine but not necessary.
- There should be a way to get details about the exercises and to update these details
  (increase/decrease weights, ...). You can use the same Activity for these two things but multiple screens might be better. 
  Note: you should not be able to update the name of an exercise.
- There should be a way to add new exercises to the database. New exercises must have a name and you should not be able 
  to create duplicate exercise names. It is wise to have default values for the other info associated with an exercise.
- There should be a way to remove exercises from the database. I delete on long click in the first (list of exercises) mode (not shown here)
- Put at least 3 different starting exercises in your database to make grading of the assignment 
  easier. This should be done in the onCreate() in the database helper (not the main activity’s onCreate()).
- A user can remove an exercise they don’t want or that they have completed using a long click. 
