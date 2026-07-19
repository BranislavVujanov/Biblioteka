# Library Management System

A Java + MySQL application for for managing books, authors, library members, and book loans. Originally built with aim to demonstrate JDBC, layered application design, GUI development, and (in its second variant) socket-based client-server programming. The application includes authentication with role-based access for library employees and members. It has since been extensively refactored into a fully English, portfolio-ready application with improved architecture, naming, documentation, and code quality.

The system supports two roles:
- **Member (`USER`)** — browse the book catalog.
- **Employee (`ADMIN`)** — manage loans and perform full CRUD on books, authors, and library members.

## Two applications, one domain

This repo contains two independent implementations of the same core domain:

| | Folder | Description |
|---|---|---|
| **Desktop App** | `DesktopApp/MyLibraryApp` | A self-contained Swing application that talks to MySQL directly via JDBC. Implements the full set of requirements. |
| **Client-Server App** | `ClientServerApp/` | The same domain, split into a multi-threaded socket server and a Swing client that communicate over TCP. Built to demonstrate concurrent programming and network communication; implements the loan-management flow, the most complex slice of the requirements. |

The Client-Server app is further split into three NetBeans/Ant projects:
- `LibraryServer` — multi-threaded TCP server, JDBC access, business logic.
- `LibraryClient` — Swing UI, sends requests to the server and renders responses.
- `LibraryCommon` — shared domain and request/response classes used by both `LibraryClient` and `LibraryServer`.

## Architecture

The project follows a layered architecture that separates responsibilities into independent components.

```
Presentation Layer (Swing Forms)
        ↓
Controller / Service Layer
        ↓
Repository (JDBC)
        ↓
MySQL Database
```

The client-server version introduces an additional communication layer between the client application and the database.

```
Client
    ↓
TCP Socket Communication
    ↓
Server
    ↓
Repository
    ↓
MySQL
```

### Design patterns

The project applies several common software engineering principles and design patterns, including:

- **Repository Pattern** — isolates JDBC/SQL access behind repository interfaces.
- **Singleton Pattern** — used for database connection management.
- **Layered Architecture** — presentation, service, and repository layers are kept separate.
- **MVC-inspired separation of responsibilities** — Swing forms, controllers, and services each have a distinct role.
- **Client-Server Architecture** — in the socket-based variant, business logic and data access live on the server, with the client handling only presentation and communication.

## Domain model

- **UserProfile** — id, first name, last name, email, password, role (`USER`/`ADMIN`).
- **Book** — id, title, publishing year, copies in stock. May have one or more authors.
- **Author** — id, first name, last name. May have written more than one book (many-to-many with Book).
- **Loan** — id, issuing date, due date, active/returned status. Links a `UserProfile` to a `Book`.

Full diagrams and the original requirements are in [`docs/`](docs/):
- `Project Requirements.txt`
- `Domain Model.drawio` / `.pdf`
- `Relational Database Schema.drawio` / `.pdf`

## Tech stack

- **Java 17**
- **Swing** for the GUI
- **MySQL 8** via JDBC (MySQL Connector/J 8.0.31, in `lib/`)
- **Apache NetBeans** project/build format (Ant)
- Client-Server variant: raw **TCP sockets** + Java object serialization, multi-threaded server

## Getting started

### Prerequisites
- JDK 17+
- MySQL Server 8.x
- Apache NetBeans (recommended — the projects are in NetBeans/Ant format) or the `ant` CLI

### 1. Set up the database

```bash
mysql -u root -p < library_database.sql
```

This creates the `library` database (schema + sample data) if it doesn't already exist.

### 2. Configure database credentials

Both apps currently connect with credentials hardcoded in `MyDatabaseConnection.java`:
- `DesktopApp/MyLibraryApp/src/mylibraryapp/repository/connection/MyDatabaseConnection.java`
- `ClientServerApp/LibraryServer/src/libraryapp/server/repository/connection/MyDatabaseConnection.java`

Default: `jdbc:mysql://127.0.0.1:3306/library`, user `root`, password `root`. Update these to match your local MySQL setup.

### 3a. Run the Desktop App

Open `DesktopApp/MyLibraryApp` in NetBeans and run, or from the command line:

```bash
cd DesktopApp/MyLibraryApp
ant clean jar
java -jar dist/MyLibraryApp.jar
```

### 3b. Run the Client-Server App

Start the server, then one or more clients:

```bash
cd ClientServerApp/LibraryServer
ant clean jar
java -jar dist/LibraryServer.jar
```

```bash
cd ClientServerApp/LibraryClient
ant clean jar
java -jar dist/LibraryClient.jar
```

The client connects to `localhost:9000` by default — see `Communication.java` if you need to point it elsewhere.

### Try it out

The seed data includes two accounts:

| Email | Password | Role |
|---|---|---|
| `ana.anic@gmail.com` | `1234` | ADMIN (employee) |
| `pera.peric@gmail.com` | `1234` | USER (member) |

## Known limitations

This project was developed as a learning exercise and intentionally keeps some aspects simple. Current limitations include:

- Passwords are stored in plain text.
- Database configuration is hardcoded.
- Some SQL statements could be further refactored to use `PreparedStatement` consistently.
- Communication uses Java object serialization rather than a modern protocol such as REST or gRPC.
- Logging is minimal and primarily intended for debugging.

These choices were made to keep the project focused on desktop development, JDBC programming, and socket-based client-server communication.

## Future improvements

Possible future enhancements include:

- BCrypt password hashing
- Configuration using properties files
- Consistent use of `PreparedStatement`
- Logging framework

## Author

Branislav Vujanov
