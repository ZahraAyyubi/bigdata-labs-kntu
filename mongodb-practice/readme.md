# MongoDB Practice
## Requirements to run codes
[1] node [2] npm(node package manager)

## Steps to run the codes
in the root directory of the project:
[1] run "npm install" command ==> to install dependencies and required packages
[2] run "node FILE_PATH" command ==> to run nodejs code for example: node import-data-to-mongodb.js

## 🗂️ Folder Structure

```
mongodb-practice/
├── q1/                        ← Solutions for Pizza Dataset
├── q2/                        ← Solutions for Sports Dataset
├── import-data-to-mongodb.js ← Data import script (JSON)
├── database-backup/          ← mongodump & mongorestore
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

## 🧰 MongoDB Tools & Features Practiced

- Querying nested arrays and documents
- Aggregation pipelines: `$match`, `$group`, `$sort`, `$project`
- Compound queries with logical operators (`$and`, `$in`, `$not`)
- Index creation
- Backup and restore using `mongodump`, `mongorestore`

---

## ⚠️ Notes

This project is an academic assignment and is not intended for production use.  
Data has been converted from Excel to JSON manually.  
Scripts were executed using the MongoDB shell and Node.js runtime.

