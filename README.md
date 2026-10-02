# 🍴 Cloud Kitchen — Online Food Ordering System

<div align="center">

![Cloud Kitchen Banner](https://img.shields.io/badge/Cloud%20Kitchen-Food%20Ordering%20System-ff6b35?style=for-the-badge&logo=spring&logoColor=white)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?style=flat&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-007396?style=flat&logo=java)](https://www.java.com)
[![H2 Database](https://img.shields.io/badge/Database-H2%20%2F%20PostgreSQL-003366?style=flat&logo=postgresql)](https://www.h2database.com)
[![Spring Security](https://img.shields.io/badge/Security-Spring%20Security-6DB33F?style=flat&logo=spring-security)](https://spring.io/projects/spring-security)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**A full-stack online food ordering platform for a cloud kitchen business**  
*Built with Spring Boot, Thymeleaf, Vanilla JS & Spring Security*

[🌐 Live Demo](https://cloud-kitchen-production.up.railway.app/cloud-kitchen/) · [📋 Report Bug](https://github.com/Tanvi269/cloud-kitchen/issues) · [✨ Request Feature](https://github.com/Tanvi269/cloud-kitchen/issues)

</div>

---

## 📸 Screenshots

| Customer Portal | Admin Dashboard |
|:---:|:---:|
| Dark premium UI with menu, cart & live order tracking | Manage orders, food availability & statuses |

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Deployment](#-deployment)
- [Admin Credentials](#-admin-credentials)
- [Contributing](#-contributing)

---

## 🎯 About the Project

**Cloud Kitchen** is a full-stack web application that allows customers to browse a food menu, add items to a cart, register/login, and place orders — which are then managed in real-time by an admin panel.

The system ensures:
- **Privacy**: Each customer only sees their own orders
- **Security**: Session-based authentication with Spring Security
- **Real-time Updates**: Live order status polling every 4 seconds
- **Admin Control**: Kitchen staff can update order statuses and food availability

---

## ✨ Features

### 👤 Customer Portal
- ✅ **Customer Registration & Login** — Secure session-based auth
- 🍽️ **Browse Menu** — Live food availability with search
- 🛒 **Shopping Cart** — Add/remove items, see live total
- 📦 **Place Orders** — Full checkout with name, phone, address & payment method
- 📋 **Live Order Tracking** — Real-time order status & preparation timer
- 🔐 **Order Privacy** — Customers only see their own orders

### 🔧 Admin Panel
- 🔒 **Secure Admin Login** — Separate protected admin portal
- 📊 **Order Dashboard** — View all orders with customer details
- 🔄 **Update Order Status** — CONFIRMED → PREPARING → DELIVERED
- ⏱️ **Preparation Timer** — Set and update remaining cooking time
- 🥗 **Food Availability** — Toggle items on/off (Out of Stock)

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java 21, Spring Boot 3.2.5 |
| **Web MVC** | Spring MVC, Thymeleaf |
| **Security** | Spring Security |
| **Database** | H2 (dev) / PostgreSQL (prod) |
| **ORM** | Spring Data JPA / Hibernate |
| **Frontend** | HTML5, Vanilla CSS, Vanilla JS |
| **Build Tool** | Maven |

---

## 📁 Project Structure

```
cloud-kitchen/
├── src/
│   ├── main/
│   │   ├── java/com/cloud/kitchen/
│   │   │   ├── CloudKitchenApplication.java   # Main entry point
│   │   │   ├── DataInitializer.java           # Seeds food items on startup
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java        # Spring Security config
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java       # Admin MVC controller
│   │   │   │   ├── CustomerController.java    # Customer auth REST API
│   │   │   │   ├── OrderApiController.java    # Orders REST API
│   │   │   │   └── FoodApiController.java     # Menu REST API
│   │   │   ├── model/
│   │   │   │   ├── User.java                  # Customer/Admin entity
│   │   │   │   ├── Order.java                 # Order entity
│   │   │   │   └── FoodItem.java              # Food item entity
│   │   │   └── repository/
│   │   │       ├── UserRepository.java
│   │   │       ├── OrderRepository.java
│   │   │       └── FoodItemRepository.java
│   │   └── resources/
│   │       ├── application.properties         # Local config (H2)
│   │       ├── application-prod.properties    # Production config (PostgreSQL)
│   │       ├── static/
│   │       │   ├── index.html                 # Customer portal
│   │       │   ├── admin-login.html           # Admin login page
│   │       │   └── images/                    # Food images
│   │       └── templates/
│   │           ├── admin-dashboard.html       # Admin dashboard (Thymeleaf)
│   │           └── admin-login.html           # Admin login template
├── Procfile                                   # Railway startup command
├── railway.json                               # Railway build config
├── pom.xml                                    # Maven dependencies
└── .gitignore
```

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.6+

### Installation & Run Locally

```bash
# 1. Clone the repository
git clone https://github.com/Tanvi269/cloud-kitchen.git
cd cloud-kitchen

# 2. Run the application
mvn spring-boot:run

# 3. Open in browser
# Customer Portal: http://localhost:9091/cloud-kitchen/
# Admin Panel:     http://localhost:9091/cloud-kitchen/admin
# H2 Console:      http://localhost:9091/cloud-kitchen/h2-console
```

---

## 🔌 API Endpoints

### Customer Auth
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/customer/register` | Register new customer |
| `POST` | `/api/customer/login` | Login customer |
| `GET` | `/api/customer/logout` | Logout customer |
| `GET` | `/api/customer/status` | Check login session |

### Orders
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/orders` | Get customer's own orders |
| `POST` | `/api/orders` | Place a new order |
| `PUT` | `/api/orders/{id}/status` | Update order status (admin) |

### Menu
| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/menu` | Get all food items with availability |

### Admin (Session Protected)
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/admin/login` | Admin login |
| `GET` | `/admin/dashboard` | View all orders & food items |
| `POST` | `/admin/orders/update-status` | Update order status |
| `POST` | `/admin/food/update-availability` | Toggle food availability |
| `GET` | `/admin/logout` | Admin logout |

---



## 🔐 Admin Credentials

> ⚠️ Change these before going live in production!

| Field | Value |
|---|---|
| **Email** | `admin@cloudkitchen.com` |
| **Password** | `admin123` |

---

## 🗃️ Database Schema

```
users         → id, name, email, password, role (CUSTOMER/ADMIN)
orders        → id, customer_name, phone_number, delivery_address,
                payment_method, items, total, status, food_items,
                preparation_time, remaining_time, order_start_time, user_id
food_items    → id, name, description, price, category, image_url, available
```

---

## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

<div align="center">
Made with ❤️ by <strong>Tanvi Patil</strong>
<br>
⭐ Star this repo if you found it helpful!
</div>
