# GitHub UI Test Automation (MVP)

This repository contains a **basic UI test automation framework** for GitHub, built as an MVP to demonstrate core automation concepts and tooling.

The project focuses on validating key user interactions such as navigation and repository search using real, public GitHub data.

---

## Tech Stack
- **Java**
- **Selenium WebDriver**
- **Cucumber (Gherkin)**
- **TestNG**
- **Maven**

---

## Current Features (MVP)
- Navigate to GitHub Home page
- Validate page load via title
- Perform repository searches using the GitHub global search UI
- Verify that searched public repositories appear in search results

All test data uses real, publicly available GitHub repositories.

---

## How to Run Tests

Ensure you have:
- Java 17+
- Maven
- Chrome browser installed

Run tests using:
```bash
mvn test
```
