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

Time Spend: 9.9 hours

This week, I have spent time working out issues identified in the code review. I also started implementing user roles
so that there can be admin permissions associated with the web application. There were quite a few issues to work out
with the set up, but nothing caught me up for very long.

On Sunday evening, I also completed the exercise for the week. Set up for Tomcat's form authorization went smoothly
with no major problems. There was one small issue I had with deploying my application to Tomcat, but I think I managed
to update my run configuration to fix the issue.
