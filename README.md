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

1. Clone the repository to your local machine.
2. Open a terminal or command prompt and navigate to the project's root directory.
3. Run the following Gradle command to build the project:

```bash
gradle build
```

4. Once the build is successful, you can run the application using the following Gradle command:

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
