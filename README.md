# LambdaTest Selenium Automation Project

## 📌 Project Overview

This project is a Selenium-based test automation framework that runs test cases on the **LambdaTest Cloud Selenium Grid**. It automates web application testing using **TestNG and WebDriverManager**.

---

## 🛠️ Tech Stack

- **Java** (Test automation scripting)
- **Selenium WebDriver** (Browser automation)
- **TestNG** (Test framework)
- **WebDriverManager** (Automated driver management)
- **LambdaTest Cloud Selenium Grid** (Remote test execution)
- **Maven** (Dependency management)

---

## 🚀 Getting Started

### **1️⃣ Clone the Repository**

Open a terminal and run:

```sh
 git clone https://github.com/ayushdhardwivedi55/LambdaTest01.git
 cd LambdaTest01
```

Or open directly in **Gitpod**:

```
https://gitpod.io/#https://github.com/ayushdhardwivedi55/Lambdatest01
```

### **2️⃣ Setup Environment Variables**

Create a `` file in the root directory and add:

```ini
LT_USERNAME=dhardwivediayush66
LT_ACCESS_KEY=LT_lvyNqyxmnL3iYLDs9WSorkL60Q3h10H65WiRq9iD7ONmsC4
```

### **3️⃣ Install Dependencies**

Run the following command:

```sh
mvn clean install
```

### **4️⃣ Run Tests on LambdaTest**

Execute all test cases with:

```sh
mvn test
```

Or run individual test classes:

```sh
mvn -Dtest=SimpleFormDemoclass test
mvn -Dtest=DragDropSliderTest test
mvn -Dtest=InputFormSubmitTest test
```

---

## 🧪 Test IDs (LambdaTest)

| Test Case                | Test ID                   |
| ------------------------ | ------------------------- |
| **Simple Form Demo**     | `GYQ8K-PQDRE-T5HXZ-ROWVH` |
| **Drag and Drop Slider** | `VFQQO-VES56-FLRLS-INI7O` |
| **Input Form Demo**      | `T0EVM-VFGZ5-4G69K-B5EJE` |

---

## 📄 Project Structure

```
LambdaTest01/
│-- src/
│   ├── test/java/lambda/
│   │   ├── SimpleFormDemoclass.java
│   │   ├── DragDropSliderTest.java
│   │   ├── InputFormSubmitTest.java
│-- pom.xml (Maven dependencies)
│-- testing.xml (TestNG configuration)
│-- .env (Environment variables)
│-- README.md (Project documentation)
```

---

## 📤 Pushing Changes to GitHub

### **1️⃣ Add all files**

```sh
git add .
```

### **2️⃣ Commit changes**

```sh
git commit -m "Updated test scripts and README"
```

### **3️⃣ Pull latest changes (to prevent conflicts)**

```sh
git pull --rebase origin main
```

### **4️⃣ Push to repository**

```sh
git push origin main
```

---

## 🛠 Troubleshooting

- **Test not running on LambdaTest?**
  - Ensure `.env` file has correct **username** and **access key**.
  - Check internet connectivity.
- **Maven dependency issue?**
  - Run: `mvn clean install -U` to force update dependencies.
- **Git push rejected?**
  - Run: `git pull --rebase origin main` before pushing again.

---

## 📞 Support

For issues, raise a ticket in the GitHub repository or contact **Ayush Dhar Dwivedi**.

Happy Testing! 🎯

