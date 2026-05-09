# 🏥 Patient Appointment Management System (PAMS)

The **Patient Appointment Management System (PAMS)** is a web-based application designed to **streamline** the process of booking, managing, and tracking appointments in a healthcare facility. [cite_start]Built with **Spring Boot** and following the **Model-View-Controller (MVC) architecture**, this system focuses on improving patient care by minimizing scheduling conflicts and ensuring efficient communication between patients and healthcare providers[cite: 3, 5, 6].


---

## ✨ Features

[cite_start]PAMS supports multiple user roles: **Patient**, **Doctor**, and **Administrator**[cite: 8].

### Patient Module
* **Registration & Login:** Securely register and log in to the system.
* [cite_start]**Profile Management:** Update personal profile details[cite: 19, 25].
* [cite_start]**Appointment Management:** Book appointments based on doctor availability, view appointment history, and manage existing bookings[cite: 9, 20].

### Doctor Module
* **Profile Management:** Register and log in to the system.
* [cite_start]**Availability Management:** Specify and update availability for appointments[cite: 9, 44, 47].
* [cite_start]**Schedule View:** View and manage their professional schedule[cite: 45].

### Administrator Module
* [cite_start]**User Oversight:** Oversee system operations and manage users (Patients, Doctors, Admins)[cite: 78, 83].
* [cite_start]**System Logs:** View system logs for auditing and conflict resolution[cite: 78, 82].

### Core System Features
* [cite_start]**Notifications:** Automatic notifications for appointment confirmation and reminders (via email/SMS logic)[cite: 10, 95, 103].
* [cite_start]**Database Support:** Compatible with **MySQL** or **SQL Server**[cite: 11].

---

## 💻 Tech Stack

* **Backend:** Java, **Spring Boot**
* [cite_start]**Architecture:** Model-View-Controller (MVC) [cite: 5]
* [cite_start]**Database:** MySQL or SQL Server [cite: 11]
* **APIs:** RESTful Web Services
* **Build Tool:** Maven

---

## 🛠️ Module-Level API Endpoints

The system is organized into modular controllers, managing distinct business logic areas.

### 1. PatientController (`/com.pams.patient.controller`)
| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/registerPatient` | `POST` | [cite_start]Registers a new patient[cite: 17]. |
| `/loginPatient` | `POST` | [cite_start]Authenticates a patient[cite: 18]. |
| `/updatePatientProfile` | `PUT` | [cite_start]Updates an existing patient's profile[cite: 19]. |

### 2. DoctorController (`/com.pams.doctor.controller`)
| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/registerDoctor` | `POST` | [cite_start]Registers a new doctor [cite: 43] (Implicit from code). |
| `/loginDoctor` | `POST` | Authenticates a doctor. |
| `/updateDoctor` | `PUT` | [cite_start]Updates a doctor's profile/availability [cite: 44] (Implicit update from code). |
| `/fetchDoctorByEmail/{email}` | `GET` | Retrieves doctor details by email. |
| `/deleteDoctor/{email}` | `DELETE` | Removes a doctor from the system. |

### 3. AppointmentController (`/com.pams.appointment.controller`)
| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/createAppointment` | `POST` | [cite_start]Enables a patient to book an appointment[cite: 61]. |
| `/updateAppointment` | `POST` | [cite_start]Modifies an existing appointment [cite: 57] (Implemented as `POST` in code). |
| `/deleteAppointment/{id}` | `DELETE` | [cite_start]Cancels or removes an appointment[cite: 62]. |
| `/fetchAppointmentById/{id}` | `GET` | Retrieves details for a single appointment. |
| `/fetchAllAppointment` | `GET` | [cite_start]Retrieves all appointments in the system [cite: 63] (Implicit from code). |

### 4. AdminController (`/com.pams.admin.controller`)
| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/registerAdmin` | `POST` | [cite_start]Registers a new admin[cite: 81]. |
| `/loginAdmin` | `POST` | Authenticates an admin. |
| `/deleteAdmin/{email}` | `DELETE` | Removes an admin user. |

---
