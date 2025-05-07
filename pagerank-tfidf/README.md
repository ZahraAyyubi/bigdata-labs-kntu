# Hadoop PageRank & Token Analysis – Big Data Coursework (Assignment 3)

This repository contains the final assignment project for the **Big Data Systems** course at *K. N. Toosi University of Technology*.  
It implements document-level token frequency analysis, IDF computation, and a PageRank-style algorithm using Hadoop MapReduce.

---

## 🧰 Technologies Used

- Java (JDK 8+)
- Apache Hadoop 2.10.1
- Composite Keys & Custom Partitioner
- In-mapper Combiner
- ChainMapper / ChainReducer
- Secondary Sort with OutputKeyComparatorClass

---

## 📂 Implemented Tasks

### 📄 Q4-1 – Top 10 Frequent Tokens

| Task | Description |
|------|-------------|
| TF Calculation | Count term frequency per document, extract top 10 frequent tokens |
| Key Format | `docID:token` |
| Advanced Features | Combiner used to reduce shuffle data and boost performance |
| Job Design | Two-stage job: first to compute frequencies, second to sort tokens |

### 📄 Q4-2 – IDF Calculation

| Task | Description |
|------|-------------|
| IDF Logic | Implemented inverse document frequency using log(N/df) |
| Step 1 | Count total number of documents using `URL` as identifier |
| Step 2 | Count in how many docs each token appears |
| Partitioner | Custom partitioner used to send same token to same reducer |
| Output | Key: token, Value: IDF value |

### 🔁 Q4-3 – Token Co-Occurrence

| Task | Description |
|------|-------------|
| Objective | Find pairwise token co-occurrence in same document |
| Technique | Implemented using in-mapper combiner with stripe-based pattern |
| Performance | Memory-optimized using local aggregation structure |

### 🌐 Q4-4 – PageRank for Tokens

| Task | Description |
|------|-------------|
| Assumption | Tokens in each document are linked to all others in the same doc |
| Initialization | Equal score assigned to all tokens initially |
| Iteration | Score distributed based on outgoing token links (adjacency list) |
| Reducer Logic | Accumulate inbound scores and update rank |
| Convergence | Iterative job executed until scores stabilized |
| Sorting | Tokens sorted by final PageRank score to get top 10 |

---

## 🏃 How to Run

```bash
# Compile Java code
hadoop com.sun.tools.javac.Main YourCode.java

# Package to JAR
jar cf YourCode.jar *.class

# Execute job
hadoop jar YourCode.jar YourCode input/ output/
```

---

## ⚠️ Notes

- Input files were preprocessed externally due to their large size (~500MB)
- Output was saved as `part-r-00000` and reviewed manually
- Each job design closely follows Hadoop's best practices and assignment specs
- Implementations focus on clarity, efficiency, and correct logical flow for educational purposes
