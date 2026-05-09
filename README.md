# 🏥 Patient Appointment Management System (PAMS)

**PAMS** is a robust, full-stack web application designed to modernize healthcare scheduling. By bridging the gap between patients, doctors, and administrators, the system eliminates manual booking errors and ensures efficient communication through a modular, service-oriented architecture.

---

## 🚀 Key Features

### 👤 Patient Portal
* **Secure Access:** Multi-factor registration and login.
* **Smart Booking:** Real-time appointment scheduling based on doctor availability.
* **Profile Management:** Self-service updates for personal records.

### 🩺 Doctor Dashboard
* **Availability Logic:** Dynamic slot management to define active working hours.
* **Schedule Insights:** Integrated view of daily and weekly patient queues.

### 🛡️ Admin Operations
* **Centralized Oversight:** Full control over user accounts and system auditing.
* **Conflict Resolution:** Detailed system logs for tracking scheduling overlaps.

---

## 🧰 Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Backend** | Java, Spring Boot, Spring MVC |
| **Frontend** | HTML5, CSS3, JavaScript |
| **Database** | MySQL / SQL Server |
| **Architecture** | Model-View-Controller (MVC) |
| **API** | RESTful Web Services |

---

## 🛠️ Module-Level API Endpoints

### 💉 Patient & Doctor Management
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/registerPatient` | Onboards new users |
| `PUT` | `/updateDoctor` | Updates availability and credentials |
| `GET` | `/fetchDoctorByEmail/{email}` | Retrieves specific medical staff details |

### 📅 Appointment Operations
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/createAppointment` | Generates a new booking record |
| `DELETE` | `/deleteAppointment/{id}` | Cancels a medical appointment |
| `GET` | `/fetchAllAppointment` | System-wide queue retrieval |

---

## ⚙️ Installation & Setup

1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/kartikey-1112/PAMS-Patient-appointment-management-system-.git](https://github.com/kartikey-1112/PAMS-Patient-appointment-management-system-.git)
