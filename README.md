# Kotlin Boilerplate Code
Boilerplate code for gradle based Kotlin application.

An application using Domain-Driven Design (DDD) and Command Query Responsibility Segregation (CQRS) principles keeping the code as simple as possible.

## Features
- Kotlin/JVM
- Kotlin Coroutine
- Strict Kotlin enable allWarningsAsErrors
- Unit test with [Kotest](https://kotest.io/), and [MockK](https://mockk.io/), and [Kotlin-faker](https://github.com/serpro69/kotlin-faker)
- Static code check using [Detekt](https://github.com/arturbosch/detekt)
- Code coverage using Jacoco
- Logging with [Kotlin-logging](https://github.com/MicroUtils/kotlin-logging)
- Gradle Kotlin DSL
- Bus Pattern Implementation
- Presentation layer web application using [Spring Boot](https://spring.io/projects/spring-boot)
- Persistence API Implementation by [Querydsl](https://querydsl.com/)

## Architecture overview
### Layers
- **Application**: Orchestrates the jobs in the domain needed to be done to accomplish a certain "use case"
- **Domain**: Where the business rules resides
- **Infrastructure**: Technologies concerns resides here (database access, sending emails, calling external APIs)

### CQRS
CQRS splits your application (and even the database in some cases) into two different paths: **Command** and **Query**.

#### Command side
Every operation that can trigger an side effect on the server must pass through the CQRS "command side". I like to put the `Handlers` (commands handlers and events handlers) inside the application layer because their goals are almost the same: orchestrate domain operations (also usually using infrastructure services).

#### Query side
Pretty straight forward, the controller receives the request, calls the related query repo and returns a **Response** (defined on infrastructure layer itself).

## Directory Structure
```
├── app
│   ├── api
│   │   └── src
│   │       ├── integration-test
│   │       │   └── kotlin
│   │       │       └── com.musinsa.codi.api
│   │       ├── main
│   │       │   └── kotlin
│   │       │       └── com.musinsa.codi.api
│   │       └── test
│   │           └── kotlin
│   │              └── com.musinsa.codi.api
│   └── core
│       └── src
│           ├── integration-test
│           │   └── kotlin
│           │       └── com.musinsa.codi.core
│           ├── main
│           │   └── kotlin
│           │       └── com.musinsa.codi.core
│           └── test
│               └── kotlin
│                  └── com.musinsa.codi.core
├── context
│   └── coordi
│       └── src
│           ├── integration-test
│           │   └── kotlin
│           │       └── com.musinsa.codi
│           ├── main
│           │   └── kotlin
│           │       └── com.musinsa.codi
│           │         └── application
│           │         └── domain
│           │         └── infrastructure
│           └── test
│               └── kotlin
│                  └── com.musinsa.codi
│                    └── application
│                    └── domain
│                    └── infrastructure
├── buildSrc
│   └── src
│       └── main
│           ├── kotlin
│           └── resources
├── context
│   └── codi
│       └── src
│           ├── integration-test
│           │   └── kotlin
│           │       └── com.musinsa.codi
│           ├── main
│           │   └── kotlin
│           │       └── com.musinsa.codi
│           │         └── application
│           │         └── domain
│           │         └── infrastructure
│           └── test
│               └── kotlin
│                  └── com.musinsa.codi
│                    └── application
│                    └── domain
│                    └── infrastructure
├── gradle
│   └── wrapper
└── shared
    └── src
        ├── main
        │   └── kotlin
        │       └── com.musinsa.shared
        │         └── application
        │         └── domain
        │         └── infrastructure
        └── test
            └── kotlin
               └── com.musinsa.shared
                 └── application
                 └── domain
                 └── infrastructure
```
### app
an application can be self-contained or a group of programs
### context
a bounded context is simply the boundary within a domain where a particular domain model applies
### shared
Designate some subset of the domain model that the several bounded contexts agree to share

## Requirements
### Install Amazon Corretto 11
- Download the Mac .pkg file from the [Downloads](https://docs.aws.amazon.com/ko_kr/corretto/latest/corretto-11-ug/downloads-list.html) page.
- Double-click the downloaded file to begin the installation wizard and follow the steps in the wizard.
- Once the wizard completes, Amazon Corretto 11 is installed in /Library/Java/JavaVirtualMachines/.
- You can run the following command in a terminal to get the complete installation path.
```sh
$ /usr/libexec/java_home --verbose
```
- Run the following command in the terminal to set the JAVA_HOME variable to the Amazon Corretto 16 version of the JDK. If this was set to another version previously, it is overridden.
```sh
export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home
```

### Install Gradle 7.1 or higher
```sh
$ brew install gradle
```

## Usage
```sh
$ git clone https://git.musinsa.com/scm/conmsa/kotlin-boilerplate.git ${YOUR_PROJECT_NAME}
$ cd ${YOUR_PROJECT_NAME}
$ ./gradlew clean build
```
## Test
### Unit Test
```sh
$ ./gradlew ${module}:test
```
### Integration Test
```sh
$ ./gradlew ${module}:integrationTest
```
