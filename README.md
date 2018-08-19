# Poker Hands Solution (Java)


## Prelimitary

The project is developed on top JVM with combine Java 8 code. It requires following tools on the developer workstation to develop/debug/test application.

* GIT
* JDK 8
* Gradle 4+ [Installation](https://gradle.org/install/)

### Initialize Project
 
Clone the project code from github repository
```
git clone git@github.com:rainmore/poker-hands-solution.git
```

Go to the project folder
```
cd poker-hands-solution
```

### Build & Run Project


Build jar

```
gradle build
```

Run project as jar

```
cat src/main/resources/poker-hands.txt | java -jar build/libs/poker-hands-solution-1.0.0.jar
```

Expect following in CLI

```
Player 1: 263 hands
Player 2: 237 hands
```