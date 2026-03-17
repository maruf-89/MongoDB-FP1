# Functional Programming with MongoDB — Java Solutions

Solutions to a functional programming assignment in Java, connecting to a MongoDB Atlas database and processing movie data from 1975 using streams, lambdas and higher-order functions.

---

## Project Structure

The project is structured into multiple files, each with a specific responsibility:

- `Movie.java` – Immutable data model representing a movie, with a factory method to convert MongoDB documents
- `SearchMethods.java` – All 9 search functions answering the assignment questions, plus a higher-order function
- `MongoDBAtlasDownloadExample.java` – Main class, connects to Atlas, fetches movie data and prints all answers
- `MongoClientConnectionExample.java` – Utility class to verify the database connection with a ping
- `ConfigLoader.java` – Reads the database URI from `config.properties` to keep credentials out of the code
- `SearchMethodsTest.java` – 11 unit tests covering all 9 functions and the higher-order function

---

## How to Run the Project

1. Clone the repository from GitHub
2. Open the project in IntelliJ IDEA
3. Create a `config.properties` file in `src/main/resources/` based on the template below
4. Make sure your IP address is whitelisted in MongoDB Atlas under "Network Access"
5. Run `MongoDBAtlasDownloadExample.java` by clicking the green play button

---

## Database Configuration

The database URI is stored in a local `config.properties` file that is ignored by git to keep credentials safe.

Create the file at `src/main/resources/config.properties` with the following content:

```properties
mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
```

A template file `config.properties.example` is included in the repository showing the required format.

> You need a MongoDB Atlas account and access to the `sample_mflix` database to run this project.

---

## Running the Tests

1. Open `SearchMethodsTest.java` in the `test/` folder
2. Click the green play button next to `class SearchMethodsTest`

All 11 tests should pass. The tests use their own hardcoded test data and do not require any database connection.

---

## Purpose of the Project

The purpose of this project is to practice:

- Functional programming in Java
- Working with streams, lambdas and higher-order functions
- Connecting to a cloud database with MongoDB Atlas
- Writing immutable data models without side effects
- Writing unit tests with JUnit 5

---

## Author

Maruf Mulk

---

## GitHub Repository

https://github.com/maruf-89/MongoDB-FP1
