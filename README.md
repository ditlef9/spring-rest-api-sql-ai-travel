
# 🗺️ Smart Travel Planner API 🌍

The **Smart Travel Planner API** is a Java Spring Boot application designed to help users plan 
trips by suggesting itineraries, attractions, and accommodations based on their interests and travel history. 
This API includes user authentication and uses SQLite for data persistence.

## ✨ Features

- 🔐 **User Registration & Authentication**: Secure user signup and login.
- 🏖️ **Personalized Travel Recommendations**: Custom travel plans based on user preferences.
- 💾 **SQLite Database**: Stores user data, travel destinations, and preferences.
- 🔒 **Secure Endpoints**: Protected with Spring Security for authenticated access.

## 🛠️ Tech Stack

- **Java** (Spring Boot) ☕
- **SQLite** (Database) 📂
- **Spring Security** (Authentication) 🔐
- **Maven** (Build Tool) ⚙️
- **Spring doc-openapi** (API Documentation)

## 🚀 Installation on Windows

- **Java 23 or newer** Download and install
Set `JAVA_HOME`:<br>
System > Advanced system settings > Environment variables > 
System variables: [New]<br>
    * Variable Name: `JAVA_HOME`
    * Variable Value: `C:\Program Files\Java\jdk-23`<br><br>

Set `Java path`:<br>
Path > [Edit] > Then add the following on new line:<br> 
`%JAVA_HOME%\bin`
<br><br>

- **Gradle** Download and install


## 📖 API Endpoints - (Spring doc-openapi)


Documentation URL: http://localhost:8080/swagger-ui/index.html

(The artifact ID was fetched from https://github.com/springdoc/springdoc-openapi/blob/main/springdoc-openapi-starter-webmvc-ui/pom.xml (artifactId).)

**XML**:<br>
You can get JSON and XML by sending header `Accept` `application/xml` by adding the following to pom.xml:
```
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

## 📜 License

This project is licensed under the Apache License.

---

