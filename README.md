# Java Project with Gradle - README

This is a Java project built using Gradle, designed to handle orders and their delays through a RESTful API. The application includes various test cases to ensure its functionality is working as 
expected. Before running the tests, make sure you have the necessary dependencies installed and the application is running on `http://localhost:8092`.

## Getting Started

### Prerequisites

To run this project, you need to have the following installed on your system:

- Java Development Kit (JDK)
- Gradle

### Running the Application

To run the application, follow these steps:

1. Ensure you have a PostgreSQL database set up with the appropriate credentials.
3. Replace the placeholder for the PostgreSQL password in the `application.yml` file.
4. Make sure Kafka is running with the specified bootstrap servers.
5. Clone the repository to your local machine.
6. Open a terminal or command prompt and navigate to the project's root directory.
7. Run the following command to create the necessary database schema:
   
```bash
createdb snappfood
```
8. Run the following Gradle command to build the project:

```bash
gradle build
```

9. Once the build is successful, you can run the application using the following Gradle command:

```bash
gradle run
```

The application should now be up and running at `http://localhost:8092`.


## Test Cases

### Test Title: Assign Agent Queue

Endpoint: `http://localhost:8092/api/cu/v1/orders/order-1/report-delay`

This test case checks if an agent queue can be assigned to the order with ID `order-1`.

### Test Title: Reported Before

Endpoint: `http://localhost:8092/api/cu/v1/orders/order-1/report-delay`

This test case verifies if the system handles the scenario when the delay is reported before.

### Test Title: New Estimated

Endpoint: `http://localhost:8092/api/cu/v1/orders/order-2/report-delay`

This test case checks if a new estimated delay can be added for the order with ID `order-2`.

### Test Title: Not Delayed

Endpoint: `http://localhost:8092/api/cu/v1/orders/order-2/report-delay`

This test case ensures that the system handles orders that are not delayed appropriately.

### Test Title: Assign Agent Queue

Endpoint: `http://localhost:8092/api/cu/v1/orders/order-4/report-delay`

This test case verifies if an agent queue can be assigned to the order with ID `order-4`.

### Test Title: Assign to Agent

Endpoint: `http://localhost:8092/api/ag/v1/orders/agent-1/assign`

This test case checks if an order can be assigned to the agent with ID `agent-1`.

### Test Title: Assign New Order to New Agent

Endpoint: `http://localhost:8092/api/ag/v1/orders/agent-2/assign`

This test case ensures that a new order can be assigned to a new agent with ID `agent-2`.

### Test Title: Agent Assigned Before

Endpoint: `http://localhost:8092/api/ag/v1/orders/agent-2/assign`

This test case verifies the system's response when attempting to assign an order to an agent who is already assigned.

### Test Title: Not Order Be Ready for Action

Endpoint: `http://localhost:8092/api/ag/v1/orders/agent-3/assign`

This test case checks how the system handles scenarios where the order is not ready to be assigned to the agent with ID `agent-3`.

### Test Title: Get Vendor Delay

Endpoint: `http://localhost:8092/api/ag/v1/orders/delay/per-vendor`

This test case retrieves the vendor delay information from the system.

Feel free to run the tests using the provided endpoints and verify the application's behavior accordingly.

**Note:** Make sure the application is running before executing the test cases.










# Spring Boot Project - README

This is a Spring Boot project configuration for the **Snapp Food** application. Below is an overview of the key configurations and settings in the `application.yml` file.

## Spring Configuration

### Spring Application Name

The name of the Spring application is set to `snapp-food`. This is the identifier used for the application.

### Data Source Configuration

The application connects to a PostgreSQL database with the following properties:

- **URL**: `jdbc:postgresql://localhost:5432/snappfood`
- **Username**: `amirhossein`
- **Password**: (Password is not specified here for security reasons)

### JPA (Java Persistence API) Configuration

- The default database schema for Hibernate is set to `snappfood`.
- The option to open the session in view is set to `false`, which is recommended to prevent performance issues.
- Hibernate `ddl-auto` property is set to `validate`, which validates the schema against the entities.

### Flyway Configuration

- The Flyway database migration tool is configured to use the default schema defined in JPA properties.
- `baseline-on-migrate` is set to `true` to baseline the existing schema before running migrations.

### Kafka Configuration

- The application uses Kafka as a messaging system with the bootstrap servers set to `localhost:9092`.
- The Kafka consumer group ID is set to the application name (`snapp-food`).

### Internationalization (I18n) Configuration

- Messages are loaded from the `messages/messages` basename for internationalization.

### Bean Definition Overriding

- The application allows bean definition overriding by setting `allow-bean-definition-overriding` to `true`.

### Data Rest Configuration

- The maximum page size for data rest responses is set to `0x7fffffff` (max integer value).

### SQL Initialization Configuration

- SQL initialization mode is set to `always`, meaning the SQL scripts will be executed on every application startup.

## Server Configuration

- The application runs on port `8092`.
- Error messages will include detailed information (`include-message: always`).
- Forward headers strategy is set to `framework` to handle proxy headers.

## Mocky Configuration

- The application uses `https://run.mocky.io/` as the base URL for mocking services.

## How to Run the Application

To run the application:

1. Ensure you have a PostgreSQL database set up with the appropriate credentials.
2. Replace the placeholder for the PostgreSQL password in the `application.yml` file.
3. Make sure Kafka is running with the specified bootstrap servers.
4. Run the Spring Boot application.

**Note**: Before deploying this application to production, ensure proper security measures are implemented and the application's properties are appropriately set for the target environment.

Please feel free to customize this README with additional information relevant to your project, such as application features, project structure, deployment instructions, and more.
