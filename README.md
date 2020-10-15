## About project
Implementing internet shop project to represent basic client-server architecture as well as Role Based Access Control.
## Used technologies
* Servlets
* Filters
* JSP
* Tomcat
* MySQL
* JDBC
## Project structure
* Users: Login and Registration, editing and removing users
* Products: ability to add new, edit existing and remove products
* Ordering steps: adding products to and removing from Shopping Cart, order confirmation, overviewing orders history, 
ability to overview order details and to cancel order
## Setting Up project
To set up the project, so you could start it locally, please follow next steps:
* run sql queries provided in src/main/resources/init_db.sql in your RDBMS to create local DB for the project
* to create user for the DB schema and configure his access rights, so the application could produce changes 
in the DB please run following queries in your RDBMS:<br>
    CREATE USER 'admin'@'localhost'<br>
      &emsp; IDENTIFIED BY 'matestudent';<br>
    GRANT ALL<br>
      &emsp; ON internet_shop.*<br>
      &emsp; TO 'admin'@'localhost';
* after registration and logging in as the new user please, use "Injecting test data in to DB" link to insert 
some test data as well as the admin user. After this procedure you may login as Admin using "admin" as login and 
"matestudent" password or as SuperAdmin using "SuperUser" for login and "matestudent" for password. 
The difference between these two users is that Admin only have admin privileges (which is creating, 
overviewing and editing content), so he can't make orders, either he doesn't have shopping cart, while SuperAdmin 
have both (admin and user). 
