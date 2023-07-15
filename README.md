# My Personal Project

## Activity Organizational Agenda

For the CPSC 210 project, I plan on creating a **comprehensive activity tracker**, able to monitor tasks and 
differentiate between the different levels of pertinence for each task. 
Users will be able to add detailed tasks to an agenda, with a title, description,
the time it will take to complete, and a *priority level*. 
The agenda will be organized to optimize precedence and when users 
ask for the next task which needs to be completed, tasks will be moved
to and from two lists: ***Need to Complete***, and ***In Progress***.

This project will be primarily used by students who juggle numerous classes and activities
and often time have difficulty in prioritizing which work to accomplish first. I personally am someone
who often times will get bogged down with lots of work, and it is very easy for me 
to lose track of the work that I need to do, and the work that is the most important. I hope
to use this project as a way to keep myself organized and able to 
complete work on time in an efficient and effective manner.
## User Stories

- As a user, I want to be able to add activities to
my agenda, with a title, description, time and *priority level*
- As a user, I want to be able to view the list of activities that I 
need to accomplish, in the order of importance, regardless of when I added an activity
- As a user, I want to be able to start my next activity, and
see the activity be removed from the list and moved to the inProgress List
- As a user, I want to be able to complete an activity once I am finished
- As a user, I want to see what Activity I currently am working on
- As a user, I want to be able to delete activities from the agenda if I do not like them
- As a user, I want to be able to see the number of total activities in my agenda
- As a user, I want to be able to see what is the total time it will take to complete all the 
activities in my agenda
- As a user, I want to be able to save my Agenda to a file
- As a user, I want to be able to open my load my Agenda from a file
- As a user, I want to be given the option to either open my saved agenda
or start a new agenda
## Phase 4: Task 2
Tue Nov 23 23:53:15 PST 2021 Activity added: TEST

Tue Nov 23 23:53:22 PST 2021 Activity added: TEST2

Tue Nov 23 23:53:25 PST 2021 Activity removed: TEST
## Phase 4: Task 3
- If I had more time, I would want to add an exception for the *Add Activity* action, which
allows the user to input an Activity with a title, description, priority status and time. 
However, if a String or Double was inputted instead of an Integer for the time input, the 
program would break; I would have wanted to throw an exception that would determine if the
input was an Integer or not, and deal with it subsequently. 
- With more time, I would have wanted to represent more of my user actions and user stories on 
the Graphical User Interface. Due to time constraints, I was only able to represent 6 of 
my user stories: Add Activity, Remove Activity, See Agenda, Save Agenda, Load Agenda and Quit. 
However, it would have been optimal to implement the GUI buttons and actions for "See Current Activity," "Start
Next Activity", and "Finish Current Activity". 
- With more time, I would have wanted to add a large GUI representation of the number
of Activities remaining in the Agenda, as well as the total time it would take to
complete all the Activities. This would change as Activities were added and removed 
from the Agenda and would have been a nice visual component to the project if
I had more time. 
- Overall, in terms of refactoring, I believe that there is redundant code in my project
which could have simplified or cleaned up. For instance, in my GUI and console application,
I made use of large if statements, which acted based on user input. However, with more time,
I would have refactored them into switch statements, or something of that sort to make my 
methods less long and congested. 