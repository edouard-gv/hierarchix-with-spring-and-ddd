# hierarchix-with-spring-and-ddd

## Description

Welcome to **Hierarchix**, a focused application created to demonstrate the implementation of Domain-Driven Design (DDD) patterns using Spring and Hibernate, with an emphasis on transaction management and aggregate handling. This project aims to illustrate how DDD principles can be effectively integrated within a Spring-based application, specifically in the domain of Organizational Design.

## Goals:
- **DDD Patterns**: Showcase how DDD patterns interact with Spring and Hibernate.
- **Transaction Management**: Demonstrate effective transaction management within DDD aggregates.

## Tech Stack:
- **Backend**: Java, Spring Framework (Spring Boot, Spring Data, Spring Security, Spring Transactions)
- **ORM**: Hibernate
- **Database**: H2
- **Architecture**: Domain-Driven Design (DDD), RESTful APIs

## Features:
- **Organizational Structure Management**: Define and manage organizational hierarchies, including departments and roles.
- **New Joiner Integration**: Handle the arrival of a new employee in a specific department.
- **Employee Transfer**: Manage the movement of an employee from one department to another.
- **Head of Department Promotion**: Process the promotion of a department head to a position outside their current department.

## Getting Started:
1. **Clone the Repository**: `git clone https://github.com/yourusername/OrgMaster.git`
2. **Navigate to the Project Directory**: `cd OrgMaster`
3. **Set Up the Database**: Configure your database connection settings in `application.properties`.
4. **Build and Run the Application**: `./mvnw spring-boot:run`
5. **Access the Application**: Open `http://localhost:8080` in your web browser.

## Contributing:
We welcome contributions from the community! Please read our contributing guidelines and code of conduct before getting started. Contributions can include new features, bug fixes, documentation improvements, and more.

## License:
This project is licensed under the MIT License.

## Contact:
For any questions or feedback, please open an issue on GitHub or reach out to us at [edouard@gomez-vaez.com].
