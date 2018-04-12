# Quiz
## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
### Prerequisites
The following items should be installed in your system:
* [Maven](https://maven.apache.org/) - Dependency Management
* [Git command line tool](https://help.github.com/articles/set-up-git/) - Version Control System
* [Intellij Idea](https://www.jetbrains.com/idea/) with JDK 1.8 at least - IDE
### Steps
1. In the command line
```
git clone https://github.com/AntonYakovenko/Quiz.git
```
or download ZIP

2. Inside Intellij Idea
```
File -> Open -> Quiz
```
3. You can find database configuration and dump in
```
quiz -> src -> main -> resources -> db.properties
```
```
quiz -> src -> main -> resources -> dump_quiz_db.sql
```
## Task
Build web-application that supports specified functionality:
* Following entities of subject domain, create classes describing them.
* Classes and methods should have a name reflecting their functionality and should be competently structured in packages.
* The code must correspond to Java Code Convention.
* Information about subject domain store in database, for access use JDBC API using a connection pool, standard or developed manually. MySQL or Oracle is recommended as a RDBMS.
* The application should support Cyrillic (to be multilingual), including when storing information in the database.
* The application architecture must correspond Model-View-Controller pattern.
* Using Servlet API and JSP, implement business logic.
* Use JSTL in JSP pages.
* When developing business logic also use sessions and filters.
* Information about occurring exceptions and events in the system can be processed by Log4j.
* Code must contain comments.

### Quizzes system
There is a list of quizzes. Each quiz contains questions with several answers, one of which is correct. User can view list of quizzes, pass them, postpone quizzes for later, and delete them from list of pending quizzes. When registering, user is checked for correctness of entered data. Quiz closes after passing and user can see his result.
## Execution
### Technologies used
* Java 9 - programming language
* Maven 3.5.3 - build tool
* Apache Tomcat 8.5.24 - application server
* Servlet API 3.1, Spring 5.0.3 IoC container, JSP 2.2, JSTL 1.2 - business logic
* MySQL 5.1.39 - data storage
* JDBC 4.2, BoneCP - access to data
* HTML 5, CSS 3, Bootstrap 4 - frontend
* Log4j 1.2.17 - logging events
* Git 2.17.0 - version control
* JUnit 4.12, Mockito 1.9.0 - testing
### Difficulties
* To inject dependencies externally from property files I create `@Inject` annotation to mark fields that I want to inject. Using reflection API I find fields marked by this annotatin and create beans. I specify bean implementation in `appContext-dao.xml`.
* To provide atomicity of transactions I use `TransactionManager`. It use thread local field to ensure that data commit will take place after all actions with data have finished, otherwise rollback occurs.
* When user logs out and logs in again, he returns to the page he left. To provide that I encode page URL and put it as parameter to login's page URL. When user sign in again, URL is decoded and user returns to the page he left.
* To check correctness of entered data at registration I create `UserValidator` which use regural exspressions or simple verifications and puts errors into `errorMap`.
* I handle answers using `answerMap` which has filled when user choose answers. Then i check how much user's `answerMap` correspond to database one and count the result of quiz.
