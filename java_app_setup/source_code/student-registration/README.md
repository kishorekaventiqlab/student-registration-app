# Student Registration Web App (JSP + Servlets)

## Overview
Simple Java web application (Servlet + JSP) to register students and store data in MySQL.
Runs in Docker: Tomcat + MySQL containers.

## Quickstart (CentOS 10)
1. Install Docker & Docker Compose.
2. Clone this repo and run:
   ```bash
   ./build_and_deploy.sh
   ```
3. Access: http://<vm-ip>:8080/student-registration/

## DB credentials (default)
- Host: mysql-db
- DB: studentdb
- User: appuser
- Password: apppassword

## Notes
- Change creds for production.
- init-db.sql contains schema creation.
