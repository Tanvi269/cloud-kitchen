## 🚀 How to Use



# Cloud Kitchen Display System

A web-based application developed to streamline kitchen order management, backed by a complete, end-to-end DevOps pipeline[cite: 6].

* **Student Name:** Tanvi Patil[cite: 6]
* **Roll No:** 23102B0036[cite: 6]
* **Repository:** [GitHub Repository](https://github.com/Tanvi269/cloud-kitchen)[cite: 6, 9]

---

## 📌 Project Overview
The Cloud Kitchen Display System is designed to solve the real-time operational challenges faced by cloud kitchens handling orders manually or through fragmented systems[cite: 6]. The Minimum Viable Product (MVP) offers a centralized order-management interface focusing on core workflow requirements[cite: 6].

### Core MVP Features:
* **Record Operations:** Create, view, update, and search kitchen orders[cite: 8].
* **Workflow Status:** Role-based order status tracking (e.g., Pending, Preparing, Ready, Completed)[cite: 8].
* **Summary Dashboard:** Real-time metrics and summary view for kitchen administrators and staff[cite: 8].

---

## 🛠️ Technology Stack
* **Programming Language & Framework:** Java, Spring Boot, Spring Data JPA[cite: 8]
* **Build Tool:** Maven[cite: 8]
* **CI/CD Automation:** Jenkins (Pipeline as Code via `Jenkinsfile`)[cite: 12, 13]
* **Containerization:** Docker[cite: 12]
* **Automated Testing:** Selenium WebDriver[cite: 14]
* **Configuration Management:** Ansible / Puppet

---

## 🚀 DevOps & CI/CD Lifecycle
This project demonstrates a complete automated software delivery pipeline:
1. **Version Control:** Managed via GitHub using feature branching strategies, pull requests, and semantic tagging[cite: 9, 10, 11].
2. **Continuous Integration:** Automated builds, compilation, and packaging executed through Jenkins[cite: 12, 13].
3. **Continuous Testing:** Automated UI and regression testing via Selenium quality gates that prevent faulty builds from progressing[cite: 14].
4. **Containerization:** Multi-stage Docker image creation and container lifecycle management[cite: 12].
5. **Provisioning & Deployment:** Automated server configuration and environment setup using configuration management scripts.

---

## ⚙️ Local Setup and Installation

### Prerequisites
* Java Development Kit (JDK 21 or compatible)[cite: 12]
* Apache Maven[cite: 12]
* Docker[cite: 12]

### Step-by-Step Guide
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Tanvi269/cloud-kitchen.git](https://github.com/Tanvi269/cloud-kitchen.git)
   cd cloud-kitchen
Build the application

   mvn clean install
Run via docker


   docker build -t cloud-kitchen .
docker run -p 9090:9090 cloud-kitchen

├── src/                # Application source code (Java/Spring Boot)
├── .github/            # Issue templates and configurations
├── Dockerfile          # Container build instructions
├── Jenkinsfile         # Pipeline as Code configuration
├── pom.xml             # Maven dependencies and build profile
└── README.md           # Project documentation
   
### 1. Customer Flow
* Open the application URL (or local `http://localhost:9090`).
* Browse available food items on the menu, add them to your cart, and place an order.
* Track your live order status in real time as it moves through different stages.

### 2. Admin Portal & Order Flow
* Click the floating **Admin Portal** button on the bottom-right of the page to open the dashboard.
* Admin login page will open.
* Put Email adress :- admin@cloudkitchen.com   Password :- admin123
* View incoming customer orders and manage their progression:
  * Click **Accept** to accept the incoming order.
  * Click **Prepare** to move the order into preparation.
  * Click **Completed** once the order is finished.
* Switch back to the customer dashboard to watch the order status update instantly across all stages.

  
