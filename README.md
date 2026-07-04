# CRUDbank — Bank Customer CRUD API

A simple Spring Boot REST API for managing bank customer records, built with Spring Data JPA and MySQL. Supports full Create, Read, Update, and Delete operations on customer data.

## Tech Stack

- **Java 21**
- **Spring Boot 4.1.0** (Spring Web MVC, Spring Data JPA)
- **MySQL** (via `mysql-connector-j`)
- **Lombok** — reduces boilerplate for getters/setters/constructors
- **Maven** — build and dependency management

## Project Structure

```
src/main/java/com/lakshay/CRUDbank/
├── CruDbankApplication.java        # Spring Boot entry point
├── controller/
│   └── CustomerController.java     # REST endpoints
├── service/
│   └── CustomerService.java        # Business logic
├── repository/
│   └── CustomerRepository.java     # Spring Data JPA repository
└── model/
    └── Customer.java               # Customer entity
```

## Customer Model

| Field         | Type   | Notes                  |
|---------------|--------|-------------------------|
| `Id`          | Long   | Auto-generated primary key |
| `firstName`   | String |                         |
| `lastName`    | String |                         |
| `email`       | String | Unique                 |
| `phoneNumber` | String |                         |

## Getting Started

### Prerequisites

- JDK 21+
- Maven (or use the included `mvnw` wrapper)
- MySQL server running locally

### 1. Set up the database

Create a MySQL database named `Customer`:

```sql
CREATE DATABASE Customer;
```

### 2. Configure the connection

Update `src/main/resources/application.properties` with your own MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Customer
spring.datasource.username=root
spring.datasource.password=your_password
```

> Hibernate is configured with `ddl-auto=update`, so the `Customer` table will be created/updated automatically on startup.

### 3. Run the application

Using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will start on `http://localhost:8080`.

## API Endpoints

Base path: `/api/customers`

| Method | Endpoint              | Description              |
|--------|------------------------|---------------------------|
| POST   | `/api/customers`       | Create a new customer     |
| GET    | `/api/customers`       | Get all customers         |
| GET    | `/api/customers/{Id}`  | Get a customer by ID      |
| PUT    | `/api/customers/{Id}`  | Update a customer by ID   |
| DELETE | `/api/customers/{Id}`  | Delete a customer by ID   |

### Example: Create Customer

**Request**

```http
POST /api/customers
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```

**Response**

```json
{
  "Id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```

### Example: Update Customer

```http
PUT /api/customers/1
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com",
  "phoneNumber": "9876543210"
}
```

### Example: Delete Customer

```http
DELETE /api/customers/1
```

Returns `200 OK` with the message `Customer deleted successfully`, or `404 Not Found` if the customer doesn't exist.

## Testing the API

A ready-to-use Postman collection is included at [`postman-api-collection.txt`](./postman-api-collection.txt). Import it into Postman to quickly test all endpoints (Create, Read, Update, Delete).

## Requirements Document

Full lab requirements for this project are available in [`Lab Requirements - Bank Customer CRUD API.pdf`](./Lab+Requirements-+Bank+Customer+CRUD+API.pdf).

## License

No license specified yet.
