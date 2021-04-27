# Weekly Reflection Statements

## Week 1

1/19/2021

Time Spent: ~3 hours

This week, I started out by completing the initial setup for the class. With no major hiccups in that process, I was all
set to continue with exercise 1. As for the indie project, I was not quite certain if I wanted to make a JSON generator
since I have made a couple already in the past. Another option I had been considering was making an online multiplayer
crossword puzzle web application (for my grandparents). Aside from that, however, my first week back to class was not
extremely productive as a result of the long (but appreciated) winter break.

## Week 2

1/25/2021

Time Spent: ~10 hours

Once again, I felt a little sluggish getting started with this week's assignments and lectures. A plus, however, is that
I finally decided upon making (another) JSON generator for Minecraft. This time I hope to improve the UI and provide
more features for saving, loading, importing, and exporting Minecraft commands. I also completed the activities (both
in-class and out-of-class) and set up this GitHub repository for the JSON generator indie project. Finally, I completed
exercise 2 and familiarized myself with some features (including the debugger) of IntelliJ IDEA.

While researching JSON generators, I found the following related to (but not quite exactly) what I want to make:
- [Tellraw Generator for Minecraft](https://minecraftjson.com/) (*This is most similar in functionality*)
- [Minecraft Custom Sign Generator](https://minecraft.tools/en/sign.php)

## Week 3

2/1/2021

Time Spent: ~13 hours

This week, I had better motivation going into the activities and demos for the week. On Tuesday, I completed the Log4J
intro and application to exercise 2 (last week's exercise). I also completed the JUnit activity and applied it to
exercise 2. This week, I did little with my indie project aside from keeping track of what time I spent working with the
class assignments. Finally, I completed exercise 3 (inverse captcha exercise) and learned a bit more about JUnit and
Log4J. Aside from that, this week was not particularly busy for me.

## Week 4

2/8/2021

Time Spent: ~22.75 hours

This week, I started out by completing the requirements for the indie project checkpoint 1 (including creating this
reflection log). I completed the wireframe images using [figma](https://figma.com/) and created the screenDesign.md
culmination of the wireframes. I also created the user stories for the project as well as the application flow document.
To finish up the project 1 check point requirements that I was missing, I created the project plan document, which
describes weekly tasks completed for the indie project.

I also completed the project checkpoint 1 review and discussed the state of my project. After reviewing, I fixed the
MVP user stories and added a couple that were missing. Also, I added the wireframe dialogue for saving commands. On top
of that, I fixed the project plan to show a **plan** of what tasks I will develop for the project and when
(who would've guessed that a plan lists your plan?)

On Wednesday, I did a lot, but a lot did not happen. I completed exercise 4 after several issues with setting up SSH
authentication instead of HTTPS authentication for GitHub. Also, my VM began freezing up a lot and had to be hard-reset
a few times. This ultimately caused it to lose internet connection (must have gotten corrupted). Fortunately, I had a
backup copy and loaded that up with minimal work required. However, then I ran into dependency issues with IntelliJ and
Maven, only for it to turn out to be related to a Hibernate facet. After all the trouble, I was able to submit a working
version of exercise 4. At least now I know what to expect for implementing Hibernate into my indie project.

On Sunday night, I started creating the template JSPs for the webpage. I used [Bootstrap version 4.6](https://getbootstrap.com/docs/4.6/getting-started/introduction/)
for styling my web pages. There are a few more changes I would like to implement with the styling, but I think it looks
good overall.

## Week 5

2/15/2021

Time Spent: ~6.75 hours

I ran into some slight issues with my VM again, but I was able to load a backup, so no harm there. After that, I was
able to set up the entity, dao, and dao test classes with minimal problems.

On Sunday evening, I updated the entity classes to utilize the one-to-many relationships discussed [here](https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#associations).
I did run into a minor problem when I forgot to update the fetch type for the User entity to eager (rather than lazy),
but the error was clear enough to indicate my mistake. Aside from that, everything went smoothly.

## Week 6

2/22/2021

Time Spent: ~11.5

This week, I spent a good chunk of time cleaning up problems identified by the design/code review. There were a couple
of things pointed out that I changed, but there are a few more tasks I would like to get done. Overall, there were
little changes to the project that needed to be changed, but nothing too major. I also converted the project from
Maven to Gradle, which did cause a couple of hiccups that I have yet to fix.

On Sunday night, I worked further into developing JSPs for the web application as well as basic servlets, but I ran into
problems with the Java classes not deploying properly. While the servlets should have valid code, I need to figure out
why the classes are not being deployed into the target web application directory. Since I could not get the servlets
up and running, I had to deploy the user display exercise instead for this week's AWS exercise.

During the "vampire hours" of Sunday night, I managed to resolve application deployment problems by eliminating various
Maven files from an older version of the project as well as modeling the build.gradle file more closely to what I
experimented with for the Professional Development Activity assignment in class. While there does still appear to be
some remnants of Maven still associated with the project, I was able to get the deployment to work along with the
servlet access, so that's great. Now I need to go to bed.


## Week 7

3/1/2021

Time Spent: 12 hours

This week, I have spent time working out issues identified in the code review. I also started implementing user roles
so that there can be admin permissions associated with the web application. There were quite a few issues to work out
with the set up, but nothing caught me up for very long.

On Sunday evening, I also completed the exercise for the week. Set up for Tomcat's form authorization went smoothly
with no major problems. There was one small issue I had with deploying my application to Tomcat, but I think I managed
to update my run configuration to fix the issue.

Finally, I updated the entity and test classes after having updated the user and role tables. With these changes, I am
now able to run tests with 100% completion (for now) and test coverage of 86%+ in the GenericDao and entity classes.

## Week 8

3/8/2021

Time Spent: 8 hours

This week, I completed the lecture readings, videos and activities with minimal issues. I ran into some slight hiccups
with associating roles to users, but with a little research, I was able to resolve the problems.

On Sunday evening, I  completed the exercise and implemented a basic Minecraft profile lookup feature in my web
application. My plan here is to give registered users the ability to select their in-game username from a list of
command targets for convenience. In the process, I also implemented Gson into the application. I had prior experience
working with Google's Gson API, but it had been a while, so I am glad to have reviewed it.

## Spring Break

3/13/2021 - 3/21/2021

Time Spent: 3.5 hours

Over spring break, I tried to limit how much time I spent on classes, but I did meet with my team for the team project
and discussed a topic. We decided upon [this StateFarm Coding Competition Challenge](https://github.com/StateFarmInsCodingCompetition/2020-StateFarm-CodingCompetitionProblem),
but we need to verify that the project topic is okay. However, we were not concerned about its relevancy, so we began
developing a basic starting point for the team project using the State Farm Competition as a reference. We also assigned
tasks for each team member.

On the team, I was tasked with developing the view portion of the MVC web application. Since there is a lot to discuss
for the development and design yet, I created a basic index JSP page that includes a JQuery DataTable. We had discussed
using DataTables in the project as a means to neatly output results.

## Week 9

3/22/2021

Time Spent: 15.75 hours

This week, I started by preparing my project for checkpoint 3. This meant that I had to create a jsp page that can
display user data loaded from the database. In my case, I wanted to set up the user saved commands page, so I began
work with loading a list of commands from the Command DAO. This went smoothly, and I was able to display the results in
a jQuery DataTable. I noticed, however, that I did not have all the required columns associated with a command in my
database and Command entity, so I set out to add the missing columns and fields. For the most part, I was able to add
the missing data with ease, up until I encountered issues with my UserDao and CommandDao test classes. With a good chunk
of time and (eventually) scanning through the Hibernate log files. The problem I ran into here is that I had named a
column 'group', which was not a problem if the column was explicitly referenced in SQL, but Hibernate does not handle
the column names that way, resulting in SQL exceptions. Once this was fixed, however, I was ready to go for the database
output requirement for checkpoint 3.

However, I had more time available and was focused, so I also ended up creating servlets and/or JSPs for the generate,
view, and remove command pages. I also added a couple success/failure JSPs to correspond to certain actions in the
web application. Overall, this process went smoothly with minimal issues.

In order to reach checkpoint 3, I also had to deploy the project up to AWS. Despite being review, I had run into some
issues when trying to access the database through the web application. After much struggling, however, I realized I had
not updated all database login information. After that, the rest of the process to deploy the project was good.

On Sunday evening, I finished the activity for this week and completed exercise 9. I ran into a couple hiccups with Gson
failing to process my POJOs, but after a little digging, I found a way to exclude unwanted fields from the serialization
that Gson performs. When I did this, I was able to successfully serialize the objects. While I was doing this, I also
met with one of my teammates to discuss the plan for this week's exercise. All in all, I spent a lot more time that I
had been planning (partially due to my poor time management right now), but I feel happy with where I am at right now.

## Week 10

3/29/2021

Time Spent: 0.75 hours

On Monday, I completed the team merging activity with Lisa. Overall, it went pretty smoothly with the only major problem
we encountered being a lack of clarity in the instructions.

## Week 11

4/5/2021

This week, I did not do much with this project since I had to focus all efforts towards the team project for completing
that.

## Week 12

4/12/2021

After much work from finishing up the team project, I took a break from my Java course for a bit. For that reason, I did
not complete anything substantial on the indie project this week.

## Week 13

4/19/2021

Time Spent: 4.75 hours

This week, I completed the week 13 assignments and made some minor changes to this project. There is still a lot more to
do, so I am hoping to get an early start in week 14 (probably on Monday) so that I can check off a bunch of todos and
plans for the project.

## Week 14

4/26/2021

On Monday, I spent a good chunk of time fleshing out the text component layout on the website. While it does need more
styling, I am happy to say that basic functionality is working. I also started work on the generate command function
too, but there is still more that needs to be done to get that working properly. All of this development is being done
outside the VM so that I can more quickly and effectively develop the JavaScript code (I don't have to redeploy the
entire web server this way).
