Implementing internet shop project which represents basic client-server architecture.
To set up the project, so you could start it locally, please follow next steps:
* run sql queries provided in src/main/resources/init_db.sql in your RDBMS to create local DB for the project
* to create user for the DB schema and configure his access rights, so the application could produce changes in the DB please run following queries in your RDBMS:<br>
    CREATE USER 'admin'@'localhost'<br>
      &emsp; IDENTIFIED BY 'matestudent';<br>
    GRANT ALL<br>
      &emsp; ON internet_shop.*<br>
      &emsp; TO 'admin'@'localhost';
* Have Fun!
