# Cart Project

This project is a multi-service application using **Keycloak**, **Kafka**, and **MongoDB**. It is intended for development and testing purposes.

---

## ⚠️ Project Size

- This project uses several container images:
  - **MongoDB** ~280MB
  - **Kafka** ~440MB
  - **Zookeeper** ~110MB (required by Kafka)
  - **Keycloak** ~600MB
- Total download size: approximately **1–1.5GB**.
- Once pulled, containers will start quickly and only minor updates will need to be downloaded in the future.

---

## 🚀 Running the Project

Make sure you have [Docker](https://www.docker.com/products/docker-desktop/) and [Docker Compose](https://docs.docker.com/compose/) installed.  

From the project root directory, run:

```bash
docker compose up keycloak kafka mongo
```

After starting the services via Docker Compose:

Ensure Java 17+ is installed.

Run the application:

```bash
mvn spring-boot:run
```

📬 Testing the API with Postman

A Postman collection is included in the project for easy API testing:


