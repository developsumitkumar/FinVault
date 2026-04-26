<div align="center">

<img src="https://img.shields.io/badge/Status-In%20Development-orange?style=for-the-badge" />
<img src="https://img.shields.io/badge/Java-Spring%20Boot-green?style=for-the-badge&logo=springboot" />
<img src="https://img.shields.io/badge/Frontend-React%20%2B%20Vite-61DAFB?style=for-the-badge&logo=react" />
<img src="https://img.shields.io/badge/Database-MongoDB-47A248?style=for-the-badge&logo=mongodb" />

<br/><br/>

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
├── finvault-backend/
│   ├── src/
│   │   ├── main/java/com/finvault/
│   │   │   ├── auth/          # JWT config, filters, token service
│   │   │   ├── user/          # User entity, roles, repository
│   │   │   ├── kyc/           # KYC submission, status workflow, audit log
│   │   │   ├── payments/      # Transaction lifecycle, fraud rules
│   │   │   ├── finance/       # Ledger entries, split logic, reports
│   │   │   └── common/        # Shared DTOs, exceptions, response wrappers
│   │   └── resources/
│   │       └── application.yml
│   └── pom.xml
│
├── finvault-frontend/          # React + Vite (upcoming)
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

[![GitHub](https://img.shields.io/badge/GitHub-developsumitkumar-181717?style=flat&logo=github)](https://github.com/developsumitkumar)
[![Portfolio](https://img.shields.io/badge/Portfolio-developsumitkumar.github.io-blue?style=flat)](https://developsumitkumar.github.io)
[![Email](https://img.shields.io/badge/Email-develop.sumitkumar@gmail.com-red?style=flat&logo=gmail)](mailto:develop.sumitkumar@gmail.com)

---

<div align="center">
<sub>Built to learn. Designed to impress. Architected to scale.</sub>
</div>
