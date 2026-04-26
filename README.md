
# FinVault

### A full-stack fintech platform simulating real-world financial workflows —
### KYC onboarding, payment processing, and personal finance tracking.

<br/>

</div>

---

## Overview

FinVault is a production-style fintech application built from scratch to demonstrate end-to-end backend architecture, compliance-aware design, and modern full-stack development. It mirrors the core workflows found in real financial products — secure onboarding, KYC verification, payment lifecycle management, and a double-entry expense ledger.

This project is being developed incrementally, with each module building on the last to reflect how fintech systems are actually structured in the industry.

---

## Architecture

```
React Frontend (Vite)
        │
        ▼
Spring Boot API Gateway  ←  JWT Authentication + Role-Based Access
        │
   ┌────┼────────────┐
   ▼    ▼            ▼
 KYC  Payments    Finance
Module  Module     Module
   │    │            │
   └────┴────────────┘
              │
              ▼
        MongoDB Atlas
  (users · kyc_docs · transactions
   ledger_entries · audit_logs)
```

**Module dependency chain:**
KYC verification → gates payment access → payments auto-post to the finance ledger → ledger entries power expense splitting and reports.

---

## Feature Progress

### Auth & Security
| Feature | Status |
|---|---|
| JWT login / register | ✅ Done |
| Role-based access (USER / ADMIN) | ✅ Done |
| Token refresh & expiry handling | ⏳ Planned |

### KYC Module
| Feature | Status |
|---|---|
| KYC document submission | ✅ Done |
| Admin approval / rejection workflow | ✅ Done |
| Status states: SUBMITTED → IN REVIEW → VERIFIED / REJECTED | ✅ Done |
| Immutable audit log per status change | ✅ Done |
| KYC gating on payment access | 🚧 In Progress |

### Payments Module
| Feature | Status |
|---|---|
| Initiate & process mock transactions | 🚧 In Progress |
| Payment states: INITIATED → PROCESSING → SUCCESS / FAILED / FLAGGED | 🚧 In Progress |
| Idempotency key enforcement | ⏳ Planned |
| Rule-based fraud flagging | ⏳ Planned |
| Reconciliation report | ⏳ Planned |

### Finance & Ledger Module
| Feature | Status |
|---|---|
| Double-entry expense ledger | ⏳ Planned |
| Group expense splitting | ⏳ Planned |
| Debt settlement with payment record | ⏳ Planned |
| Monthly category reports | ⏳ Planned |

### Frontend (React + Vite)
| Feature | Status |
|---|---|
| Auth screens (login / register) | ⏳ Planned |
| KYC submission form | ⏳ Planned |
| Admin dashboard | ⏳ Planned |
| Transaction history & ledger view | ⏳ Planned |
| Spending analytics dashboard | ⏳ Planned |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Backend framework | Spring Boot 3 |
| Security | Spring Security + JWT |
| Database | MongoDB Atlas |
| Frontend | React 18 + Vite |
| UI components | Material UI (MUI) |
| API style | RESTful JSON APIs |
| Build tool | Maven |
| Version control | Git + GitHub |

---

## Project Structure

```
FinVault/
│
├── finvault-backend/
│   ├── src/
│   │   ├── main/
│   │   │   └── java/com/finvault/
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java        # Spring Security + JWT filter chain
│   │   │       ├── controller/
│   │   │       │   ├── AdminController.java        # Admin: KYC approval/rejection
│   │   │       │   ├── AuthController.java         # Register, login endpoints
│   │   │       │   ├── KycController.java          # KYC submission & status
│   │   │       │   ├── UserController.java         # User profile endpoints
│   │   │       │   └── TestController.java         # Dev/test endpoint
│   │   │       ├── dto/
│   │   │       │   ├── AuthResponse.java           # JWT token response wrapper
│   │   │       │   ├── LoginRequest.java           # Login payload
│   │   │       │   └── RegisterRequest.java        # Registration payload
│   │   │       ├── model/
│   │   │       │   ├── User.java                   # User document (roles, status)
│   │   │       │   └── Kyc.java                    # KYC document (status, timestamps)
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java         # MongoDB user queries
│   │   │       │   └── KycRepository.java          # MongoDB KYC queries
│   │   │       ├── security/
│   │   │       │   ├── JwtService.java             # Token generation & validation
│   │   │       │   ├── JwtAuthenticationFilter.java # Request-level JWT filter
│   │   │       │   └── CustomUserDetailsService.java # Spring Security user loading
│   │   │       ├── service/
│   │   │       │   ├── UserService.java            # User business logic
│   │   │       │   └── KycService.java             # KYC workflow logic
│   │   │       └── FinvaultBackendApplication.java # Application entry point
│   │   ├── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw / mvnw.cmd
│   └── HELP.md
│
├── finvault-frontend/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── App.jsx                                 # Root component & routing
│   │   ├── App.css
│   │   ├── main.jsx                                # React DOM entry point
│   │   └── index.css
│   ├── index.html
│   ├── package.json
│   ├── eslint.config.js
│   └── .gitignore
│
└── .gitignore
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MongoDB Atlas account (or local MongoDB)
- Node.js 18+ *(for frontend, when available)*

### Backend Setup

```bash
# Clone the repository
git clone https://github.com/developsumitkumar/finvault.git
cd finvault/finvault-backend

# Set your MongoDB URI in application.yml
# spring.data.mongodb.uri=mongodb+srv://<user>:<pass>@cluster.mongodb.net/finvault

# Run the backend
./mvnw spring-boot:run
```

The API will start at `http://localhost:8080`.

### API Quick Reference

```
POST   /api/auth/register       Register a new user
POST   /api/auth/login          Login and receive JWT

POST   /api/kyc/submit          Submit KYC documents
GET    /api/kyc/status          Get current KYC status
PUT    /api/admin/kyc/{id}      Approve or reject KYC (ADMIN only)

POST   /api/payments/initiate   Start a payment
GET    /api/payments/history    View transaction history

GET    /api/finance/ledger      View expense ledger
POST   /api/finance/split       Create a group expense split
```

---

## Key Concepts Demonstrated

- **JWT authentication** — stateless auth with role claims embedded in the token
- **Role-based access control** — USER and ADMIN roles enforced at the controller level
- **KYC compliance workflow** — status state machine with immutable audit trail
- **Idempotency** — preventing duplicate payment records via idempotency keys
- **Double-entry ledger accounting** — debit/credit entries underpinning expense tracking
- **REST API design** — consistent response envelopes, error handling, HTTP semantics

---

## Roadmap

```
[✅] Phase 1 — Auth & JWT
[✅] Phase 2 — KYC module + admin workflow
[🚧] Phase 3 — Payments module + KYC gating
[⏳] Phase 4 — Ledger, splits & reconciliation
[⏳] Phase 5 — React frontend
[⏳] Phase 6 — Deployment (Railway / Render)
```

---

## Author

**Sumit Kumar**
Full-Stack Engineer · NIIT StackRoute Certified

