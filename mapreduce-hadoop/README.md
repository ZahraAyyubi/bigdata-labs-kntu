# Hadoop MapReduce – Big Data Coursework (Assignment 2)

Java-based Hadoop MapReduce projects developed for the **Big Data Systems** course at *K. N. Toosi University of Technology*.

---

## 🧰 Technologies Used

- Java (JDK 8+)
- Apache Hadoop 2.10.1
- Linux (Debian recommended)
- Bash / Terminal

---

## 📦 Dataset Topics

- **Pizza Orders Dataset** — order analytics and pricing analysis

---

## 📂 Implemented Tasks

### 🍕 Pizza Orders – Implemented Questions

| Folder | Description |
|--------|-------------|
| `q1-1` | Count pizza orders by type and size, sorted descending |
| `q1-2` | Compute total price per pizza type and size, sorted ascending |
| `q1-4` | Identify month with most orders (all years combined) |

Each folder contains a structured implementation using a two-job MapReduce chain (preprocessing and aggregation).

### 📄 Word Count Example

| Folder | Description |
|--------|-------------|
| `wordcount` | Classic Hadoop Word Count job for testing and reference |

---

## 🔧 Features Used

- Multi-job architecture (first-job + second-job)
- Sorting and filtering using composite keys and custom comparators
- In-mapper aggregation and job chaining
- Input/output handling using Hadoop CLI
- Output stored as `part-r-00000`

---

## 🏃 How to Run

```bash
# Compile Java code
hadoop com.sun.tools.javac.Main YourCode.java

# Package classes into a JAR
jar cf YourCode.jar *.class

# Execute MapReduce job
hadoop jar YourCode.jar YourCode input/ output/
```

---

## ⚠️ Notes

This project is part of a university-level coursework and not optimized for production systems.  
Code was tested in pseudo-distributed mode on a Linux virtual machine.
