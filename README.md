# Fintech Risk Management Platform

A comprehensive Java-based fintech risk management platform built with Spring Boot, designed to handle real-time transaction processing, risk analysis, and compliance monitoring.

## Overview

This platform provides a robust solution for financial technology companies to manage and monitor transaction risks in real-time. It features advanced security mechanisms, event-driven architecture, and scalable data storage solutions.

## Features

- **Real-time Risk Analysis**: Automated risk scoring and detection for financial transactions
- **Transaction Management**: Complete transaction lifecycle management with audit trails
- **JWT Authentication**: Secure authentication and authorization using JWT tokens
- **Event-Driven Architecture**: Kafka-based event streaming for transaction processing
- **Multi-Database Support**: PostgreSQL for transactional data, MongoDB for logging and analytics
- **Admin Portal**: Administrative interface for user and system management
- **API Documentation**: Swagger/OpenAPI integration for comprehensive API documentation
- **Security First**: Spring Security integration with role-based access control

## Technology Stack

- **Java 17**
- **Spring Boot 4.0.0**
- **Spring Data JPA**
- **Spring Security**
- **Apache Kafka**
- **PostgreSQL** - Transactional data storage
- **MongoDB** - Logging, audit trails, and analytics
- **Lombok** - Code simplification
- **Swagger/OpenAPI** - API documentation
- **Maven** - Dependency management

## Prerequisites

Before running this application, ensure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- MongoDB 4.4+
- Apache Kafka 2.8+

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd fintech-risk-mgmt
```

### 2. Configure Databases

#### PostgreSQL Setup

```sql
CREATE DATABASE fintech_db;
```

Update database credentials in [application.properties](src/main/resources/application.properties):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fintech_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### MongoDB Setup

Ensure MongoDB is running on default port 27017, or update the connection string:

```properties
spring.mongodb.uri=mongodb://localhost:27017/fintech_mongo_db
```

### 3. Configure Kafka

Ensure Kafka is running and accessible. Default configuration uses localhost.

### 4. Update JWT Secret

For production environments, update the JWT secret in [application.properties](src/main/resources/application.properties):

```properties
jwt.secret=YourSecureSecretKeyHere
jwt.expiration-ms=3600000
```

### 5. Build the Application

```bash
./mvnw clean install
```

### 6. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/fintech_risk_platform_java/
│   │   ├── config/              # Configuration classes
│   │   │   ├── SecurityConfig.java
│   │   │   ├── SecurityBeansConfig.java
│   │   │   └── SwaggerConfig.java
│   │   ├── controller/          # REST API controllers
│   │   │   ├── AdminController.java
│   │   │   ├── AuthController.java
│   │   │   ├── RiskController.java
│   │   │   ├── TransactionController.java
│   │   │   └── dto/             # Data Transfer Objects
│   │   ├── model/               # Domain models
│   │   │   ├── Account.java
│   │   │   ├── Transaction.java
│   │   │   ├── TransactionEventEntity.java
│   │   │   ├── TransactionLog.java
│   │   │   ├── User.java
│   │   │   └── enums/           # Enumerations
│   │   ├── repository/          # Data access layer
│   │   ├── service/             # Business logic
│   │   │   ├── AdminService.java
│   │   │   ├── RiskEngineService.java
│   │   │   ├── TransactionService.java
│   │   │   └── TransactionEventService.java
│   │   └── utils/               # Utility classes
│   │       ├── kafka/event/     # Kafka event handlers
│   │       └── security/        # Security utilities (JWT)
│   └── resources/
│       └── application.properties
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - User login

### Transactions
- `POST /api/transactions` - Create a new transaction
- `GET /api/transactions` - List all transactions
- `GET /api/transactions/{id}` - Get transaction details

### Risk Management
- `GET /api/risk/analyze/{transactionId}` - Analyze transaction risk
- `GET /api/risk/score/{transactionId}` - Get risk score

### Admin
- `GET /api/admin/users` - List all users
- `PUT /api/admin/users/{id}` - Update user details
- `DELETE /api/admin/users/{id}` - Delete user

## Security

The application uses JWT-based authentication. To access protected endpoints:

1. Register or login to obtain a JWT token
2. Include the token in the Authorization header:
   ```
   Authorization: Bearer <your-jwt-token>
   ```

## Event-Driven Architecture

The platform uses Kafka for event-driven processing:

- **Transaction Events**: Real-time transaction status updates
- **Risk Events**: Risk analysis results and alerts
- **Audit Events**: System-wide audit trail

## Database Schema

### PostgreSQL (Transactional Data)
- `users` - User accounts and credentials
- `accounts` - Financial accounts
- `transactions` - Transaction records

### MongoDB (Analytics & Logging)
- `transaction_logs` - Detailed transaction logs
- `transaction_events` - Event stream history
- `audit_trail` - System audit records

## Testing

Run unit tests:

```bash
./mvnw test
```

## Building for Production

Create a production-ready JAR:

```bash
./mvnw clean package -DskipTests
```

The JAR file will be created in the `target/` directory.

## Configuration Properties

Key configuration options in [application.properties](src/main/resources/application.properties):

| Property | Description | Default |
|----------|-------------|---------|
| `server.port` | Server port | 8080 |
| `jwt.secret` | JWT signing key | (must be configured) |
| `jwt.expiration-ms` | JWT token expiration | 3600000 (1 hour) |
| `spring.jpa.hibernate.ddl-auto` | Hibernate DDL mode | update |

## Troubleshooting

### Database Connection Issues
- Ensure PostgreSQL and MongoDB are running
- Verify connection credentials in application.properties
- Check firewall settings

### Kafka Connection Issues
- Verify Kafka broker is running
- Check Kafka configuration in application.properties
- Ensure topics are created

### JWT Token Issues
- Verify the JWT secret is properly configured
- Check token expiration settings
- Ensure Authorization header format is correct

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the [LICENSE](LICENSE) file in the root directory.

## Contact

For questions or support, please open an issue in the repository.

## Acknowledgments

- Spring Boot team for the excellent framework
- Apache Kafka for event streaming capabilities
- The open-source community

---

**Note**: This is a development version. For production deployment, ensure all security configurations, secrets, and database credentials are properly secured and not committed to version control. 
