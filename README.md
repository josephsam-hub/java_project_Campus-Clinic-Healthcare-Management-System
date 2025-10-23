# 🏥 Campus Clinic Healthcare Management System

A comprehensive web-based healthcare management system for campus clinics with role-based access control, appointment booking, and administrative features.

![Project Banner](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue) ![JWT](https://img.shields.io/badge/Security-JWT-orange) ![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [API Endpoints](#-api-endpoints)
- [User Roles](#-user-roles)
- [Screenshots](#-screenshots)
- [Testing](#-testing)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)
- [License](#-license)

---

## ✨ Features

### For Patients (Users)
- ✅ User registration and authentication
- ✅ View available doctors by specialization
- ✅ Book appointments with doctors
- ✅ View personal appointment history
- ✅ Update and cancel appointments
- ✅ Profile management

### For Administrators
- ✅ Manage all users (view, edit, delete)
- ✅ Manage doctors (add, edit, delete)
- ✅ View and manage all appointments
- ✅ Change appointment status
- ✅ Generate reports (doctor usage, patient visits)
- ✅ Full system oversight

### Security Features
- 🔐 JWT-based authentication
- 🔐 Role-based access control (USER/ADMIN)
- 🔐 Password encryption with BCrypt
- 🔐 Session management
- 🔐 Protected API endpoints

---

## 🛠 Technology Stack

### Backend
- **Framework:** Spring Boot 3.2.0
- **Language:** Java 17
- **Security:** Spring Security + JWT
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA (Hibernate)
- **Build Tool:** Maven
- **Authentication:** JSON Web Tokens (JWT)

### Frontend
- **HTML5**
- **CSS3** (Custom styling)
- **JavaScript** (Vanilla JS)
- **Single Page Application** (SPA)

### Tools & Libraries
- **JWT:** jjwt 0.11.5
- **Database Driver:** PostgreSQL JDBC Driver
- **Validation:** Spring Boot Starter Validation
- **IDE:** Eclipse with Spring Tool Suite

---

## 📁 Project Structure

```
campus-clinic/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── campusclinic/
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   └── CorsConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── AuthController.java
│   │   │           │   ├── UserController.java
│   │   │           │   ├── AdminController.java
│   │   │           │   ├── DoctorController.java
│   │   │           │   └── AppointmentController.java
│   │   │           ├── dto/
│   │   │           │   ├── LoginRequest.java
│   │   │           │   ├── LoginResponse.java
│   │   │           │   ├── RegisterRequest.java
│   │   │           │   ├── UserDTO.java
│   │   │           │   ├── DoctorDTO.java
│   │   │           │   ├── AppointmentDTO.java
│   │   │           │   ├── AppointmentCreateRequest.java
│   │   │           │   └── ErrorResponse.java
│   │   │           ├── model/
│   │   │           │   ├── User.java
│   │   │           │   ├── Doctor.java
│   │   │           │   └── Appointment.java
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── DoctorRepository.java
│   │   │           │   └── AppointmentRepository.java
│   │   │           ├── security/
│   │   │           │   ├── JwtUtil.java
│   │   │           │   ├── JwtAuthenticationFilter.java
│   │   │           │   └── CustomUserDetailsService.java
│   │   │           ├── service/
│   │   │           │   ├── AuthService.java
│   │   │           │   ├── UserService.java
│   │   │           │   ├── DoctorService.java
│   │   │           │   └── AppointmentService.java
│   │   │           └── AppoinmentBookingApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│
├── frontend/
│   └── index.html (Single-page application)
│
├── pom.xml
└── README.md
```

---

## 📦 Prerequisites

Before running this project, ensure you have the following installed:

- ☑️ **Java 17** or higher
- ☑️ **Maven 3.6+**
- ☑️ **PostgreSQL 12+**
- ☑️ **Eclipse IDE** (with Spring Tool Suite) or IntelliJ IDEA
- ☑️ **Postman** (for API testing)
- ☑️ **Git** (optional)

---

## 🚀 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/campus-clinic.git
cd campus-clinic
```

### Step 2: Create PostgreSQL Database

Open **pgAdmin** or PostgreSQL command line and create a database:

```sql
CREATE DATABASE campusclinic;
```

### Step 3: Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/campusclinic
spring.datasource.username=postgres
spring.datasource.password=your_password
```

Replace `your_password` with your actual PostgreSQL password.

### Step 4: Build the Project

```bash
mvn clean install
```

---

## ⚙️ Configuration

### application.properties

```properties
# Application Name
spring.application.name=campus-clinic
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/campusclinic
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Configuration
jwt.secret=campusclinic2025secretkeyforsigningJWTtokens12345678901234567890
jwt.expiration=86400000

# Logging
logging.level.com.campusclinic=DEBUG
```

---

## ▶️ Running the Application

### Method 1: Using Eclipse

1. Import project as **Maven Project**
2. Right-click on `AppoinmentBookingApplication.java`
3. Select **Run As** → **Spring Boot App**

### Method 2: Using Maven

```bash
mvn spring-boot:run
```

### Method 3: Using JAR

```bash
mvn clean package
java -jar target/campus-clinic-0.0.1-SNAPSHOT.jar
```

The application will start at: **http://localhost:8080**

---

## 🌐 API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | Login user/admin | No |
| POST | `/api/auth/logout` | Logout user | No |

### User Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/user/profile` | Get user profile | Yes (USER) |
| PUT | `/api/user/profile` | Update profile | Yes (USER) |

### Doctor Endpoints (Public)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/doctors` | Get all doctors | No |
| GET | `/api/doctors/{id}` | Get doctor by ID | No |
| GET | `/api/doctors/specialization/{spec}` | Search by specialization | No |

### Appointment Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/appointments` | Book appointment | Yes (USER) |
| GET | `/api/appointments/user` | Get user appointments | Yes (USER) |
| GET | `/api/appointments/{id}` | Get appointment by ID | Yes (USER) |
| PUT | `/api/appointments/{id}` | Update appointment | Yes (USER) |
| DELETE | `/api/appointments/{id}` | Cancel appointment | Yes (USER) |

### Admin Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/admin/users` | Get all users | Yes (ADMIN) |
| PUT | `/api/admin/users/{id}` | Update user | Yes (ADMIN) |
| DELETE | `/api/admin/users/{id}` | Delete user | Yes (ADMIN) |
| POST | `/api/admin/doctors` | Add doctor | Yes (ADMIN) |
| PUT | `/api/admin/doctors/{id}` | Update doctor | Yes (ADMIN) |
| DELETE | `/api/admin/doctors/{id}` | Delete doctor | Yes (ADMIN) |
| GET | `/api/admin/appointments` | Get all appointments | Yes (ADMIN) |
| PUT | `/api/admin/appointments/{id}` | Update appointment | Yes (ADMIN) |
| DELETE | `/api/admin/appointments/{id}` | Delete appointment | Yes (ADMIN) |
| GET | `/api/admin/reports/doctor-usage` | Doctor usage report | Yes (ADMIN) |
| GET | `/api/admin/reports/patient-visits` | Patient visits report | Yes (ADMIN) |

---

## 👥 User Roles

### Regular User (USER)
- View doctors
- Book appointments
- Manage own appointments
- Update profile

### Administrator (ADMIN)
- All user capabilities
- Manage users
- Manage doctors
- Manage all appointments
- Generate reports

---

## 📸 Screenshots

### Landing Page
![Landing Page](https://via.placeholder.com/800x400/667eea/ffffff?text=Campus+Clinic+Landing+Page)

### Login Page
![Login Page](https://via.placeholder.com/800x400/764ba2/ffffff?text=Login+Page)

### User Dashboard
[6]

### Doctor List
![Doctor List](https://via.placeholder.com/800x400/667eea/ffffff?text=Available+Doctors)

### Book Appointment
[7]

### Admin Dashboard
[6]

### Reports
![Reports](https://via.placeholder.com/800x400/764ba2/ffffff?text=Admin+Reports)

---

## 🧪 Testing

### Using Postman

1. **Register a User:**
   ```
   POST http://localhost:8080/api/auth/register
   Body (JSON):
   {
     "name": "John Doe",
     "email": "john@example.com",
     "password": "password123",
     "contact_no": "9876543210"
   }
   ```

2. **Login:**
   ```
   POST http://localhost:8080/api/auth/login
   Body (JSON):
   {
     "email": "john@example.com",
     "password": "password123"
   }
   ```
   Copy the `token` from response.

3. **Get Profile (Protected):**
   ```
   GET http://localhost:8080/api/user/profile
   Headers:
   Authorization: Bearer <your_token_here>
   ```

### Creating Admin User

Run this SQL in pgAdmin:

```sql
INSERT INTO users (name, email, password, role, contact_no, join_date)
VALUES (
  'Admin User',
  'admin@campusclinic.com',
  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhka',
  'ADMIN',
  '1234567890',
  NOW()
);
```

**Login credentials:**
- Email: `admin@campusclinic.com`
- Password: `admin123`

---

## 🐛 Troubleshooting

### Issue: 403 Forbidden Error

**Solution:**
- Ensure JWT token is included in Authorization header: `Bearer <token>`
- Check token expiration (24 hours validity)
- Verify user role matches endpoint requirements

### Issue: Database Connection Failed

**Solution:**
- Verify PostgreSQL is running
- Check database name, username, password in `application.properties`
- Ensure port 5432 is not blocked

### Issue: Port 8080 Already in Use

**Solution:**
Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Tables Not Created

**Solution:**
Check `spring.jpa.hibernate.ddl-auto=update` in properties file.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- PostgreSQL Community
- JWT.io
- All contributors and testers

---

## 📞 Support

For support, email support@campusclinic.com or open an issue in the GitHub repository.

---

## 🔗 Links

- **GitHub Repository:** https://github.com/yourusername/campus-clinic
- **Live Demo:** Coming Soon
- **Documentation:** [Wiki](https://github.com/yourusername/campus-clinic/wiki)

---

**Made with ❤️ for Campus Healthcare**
