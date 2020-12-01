# cinema

In this project used N-tier architecture with DB layer, DAO layer, Service layer, Controllers layer and View layer. <br>
Project was developed according to SOLID principles with authorization and authentication.

One user can have multiple roles. <br>
##### No role: <br>
  - Registration
  - Authorization
  - View a list of available movies
  - View the list of cinema halls
  - Find session by date
##### User: <br>
  - View a list of available movies
  - View the list of cinema halls
  - View order list
  - Find session by date
  - Add sessions to shopping cart
  - Make an order
  - logout
##### Admin: <br>
  - View / add movie
  - View / add cinema hall
  - Add movie session
  - Find session by date
  - Find user by email
  - logout


# Technologies used <br>
**backend:** Java, Spring Core/MVC/Security, Hibernate, Jackson, Tomcat <br>
**database:** MySQL <br>

# To start the project you need: <br>
1) *Download and install* the [JDK](https://www.oracle.com/java/technologies/javase-downloads.html, "Download JDK") <br>
2) *Download and install* servlet container (for example Apache [Tomcat](https://tomcat.apache.org/download-90.cgi, "Download Tomcat")) <br>
3) *Download and install* [MySQL Server](https://dev.mysql.com/downloads/)<br>
+ Setup new connection with <br>
  + user: *"your username"* <br>
  + password: *"your password"*<br>
  + url: jdbc:mysql://*"your host name"*:*"your port"*/*"your name db"*?useUnicode=true&serverTimezone=UTC <br>
4) Run a project
