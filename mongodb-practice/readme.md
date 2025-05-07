# MongoDB Practice – Big Data Coursework

This project was developed as part of the Big Data Systems course at **K. N. Toosi University of Technology**.  
It demonstrates core MongoDB functionalities including data import, document querying, aggregation pipelines, and backup/restore operations.

---

## 🚀 Requirements

- [1] Node.js  
- [2] npm (Node Package Manager)  
- [3] MongoDB installed locally or accessible via URI

---

## 🛠️ How to Run

From the root directory:

1. Install required packages:  
   ```bash
   npm install
   ```

2. Run any Node.js script, for example:  
   ```bash
   node import-data-to-mongodb.js
   ```

---

## 📁 Folder Structure

```
mongodb-practice/
├── q1/                        ← Solutions for Pizza Dataset
├── q2/                        ← Solutions for Sports Dataset
├── import-data-to-mongodb.js ← Data import script (JSON)
├── database-backup/          ← mongodump & mongorestore files
├── dataset/                  ← JSON data files
```

---

## ✅ Question-to-Code Mapping

### 🍕 Q1: Pizza Dataset – folder: `/q1`

| Task | Description |
|------|-------------|
| Q1-1 | List names of all medium-sized pizzas |
| Q1-2 | Count number of orders for pepperoni pizza |
| Q1-3 | Monthly order amounts, grouped by pizza size |
| Q1-4 | Average order value, grouped by pizza name |
| Q1-5 | Total orders grouped by pizza name and size using multi-stage aggregation |

📂 All queries implemented in `q1/queries.js`

---

### 🏋️ Q2: Sports Dataset – folder: `/q2`

| Task | Description |
|------|-------------|
| Q2-1 | List names of all members (A–Z) |
| Q2-2 | List members sorted by membership date |
| Q2-3 | Members interested in both tennis and volleyball |
| Q2-4 | Group swimming members by registration month |
| Q2-5 | Tennis players interested in swimming but haven’t declared it |

📂 All queries implemented in `q2/queries.js`

---

## 🔧 MongoDB Features Covered

- Querying nested documents and arrays
- Aggregation pipelines: `$match`, `$group`, `$sort`, `$project`
- Compound queries using `$and`, `$in`, `$not`
- Index creation and performance tuning
- Database backup and restore via `mongodump` / `mongorestore`

---

## ⚠️ Disclaimer

This project was developed for academic purposes only.  
Data was originally provided in Excel and converted to JSON manually.  
Scripts were tested using the MongoDB shell and Node.js.
