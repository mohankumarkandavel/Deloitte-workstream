 Workload Management App
 ===

# Client

_Under the client directory_:

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.3.2.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/` on your browser. The app will automatically reload if you change any of the source files.

Log in with these credentials
> username: James\
> password: 123

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

# Server

_Under the server directory_:

## Start the server

To start the server, run
> `./gradlew bootRun`

The server will run on port 8080.

## Tests

To execute unit tests, run
> `./gradlew test`

## Build an executable JAR file

To build the JAR, run
>`./gradlew build`

It will also run the tests.

Then start the server with the command
> `java -jar build/libs/gs-rest-nz.co.vincens.service-0.1.0.jar`

The server will run on port 8080.