# Currency Exchange & Discount Calculator

A Spring Boot application that calculates the final payable amount on a bill after applying eligible discounts and converting the total into a target currency using real-time exchange rates.

---

## 🔧 Technologies Used
- Java 17
- Spring Boot
- Spring Security (Basic Authentication)
- ExchangeRate API (https://v6.exchangerate-api.com)
- Maven
- JUnit + Mockito (for unit tests)
- JaCoCo (code coverage)
- SonarQube (static analysis)

---

## 📦 How to Build & Run

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd currency-exchange-discount
```

### 2. Set the API Key
Edit `src/main/resources/application.properties` and add:
```properties
exchange.api.key=bd702d19d3efac7716fe4f52
```

### 3. Build the Application
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

### 5. Access the Endpoint
Endpoint: `POST /api/calculate`

Basic Authentication:
```
Username: asad
Password: A$@d
```

Sample cURL:
```bash
curl -X POST http://localhost:8080/api/calculate \
  -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" \
  -H "Content-Type: application/json" \
  -d '{
        "items": [
            {"category": "OTHER", "price": 150},
            {"category": "GROCERIES", "price": 50},
            {"category": "OTHER", "price": 100}
        ],
        "totalAmount": 300,
        "userType": "EMPLOYEE",
        "customerTenure": 3,
        "originalCurrency": "USD",
        "targetCurrency": "EUR"
      }'
```

---

## 🧪 Running Unit Tests
```bash
mvn test
```

Includes tests for:
- Discount rules
- Service logic with mocked dependencies

---

## ✅ Generate Test Coverage Report
If using JaCoCo:
```bash
mvn clean test jacoco:report
```

Then open:
```
target/site/jacoco/index.html
```

---

## 🔍 Run Static Code Analysis with SonarQube
Ensure SonarQube is installed and running locally:
```bash
mvn sonar:sonar \
  -Dsonar.projectKey=currency-discount \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<your-sonarqube-token>
```

---

## 📜 Bonus Build Scripts (using Maven)

### 🔨 Build Project
```bash
mvn clean install
```

### ✅ Run Unit Tests + Generate Coverage
```bash
mvn clean test jacoco:report
```

### 🧼 Lint and Code Quality (via Sonar)
```bash
mvn sonar:sonar
```

---

## 🌐 API Integration
- Integrated with **ExchangeRate-API**
- Endpoint used: `https://v6.exchangerate-api.com/v6/bd702d19d3efac7716fe4f52/latest/{base_currency}`
- ✅ Caching is implemented to reduce API calls using Spring's `@Cacheable`

---

## ✅ Features
- Percentage and fixed discount rules
- Currency conversion using real-time API
- Secured endpoint using basic authentication
- Unit-tested business logic
- Exchange rate caching
- Static code analysis & coverage reporting

---

## 📂 Directory Structure
See inline structure in the main code file.
