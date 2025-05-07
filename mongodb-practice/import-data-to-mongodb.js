const xlsx = require('node-xlsx');
const MongoClient = require('mongodb').MongoClient;

const pizzaData = {}, sportData = {}

//read xlsx files
const pizzaFirstSheet = xlsx.parse(__dirname + '/dataset/pizza.xlsx')[0];
const sportFirstSheet = xlsx.parse(__dirname + '/dataset/sport.xlsx')[0];

//fetch columns and documents
pizzaData.columns = pizzaFirstSheet["data"][0]
pizzaData.records = pizzaFirstSheet["data"].splice(1)
//map excel records to mongodb document objects
const pizzaDocuments = pizzaData.records.map((record) => {
    const document = {}
    pizzaData.columns.forEach((column, index) => {
        document[column] = record[index]
    });
    return document
})

sportData.columns = sportFirstSheet["data"][0]
sportData.records = sportFirstSheet["data"].splice(1)
//map excel records to mongodb document objects
const sportDocuments = sportData.records.map((record) => {
    const document = {}
    sportData.columns.forEach((column, index) => {

        if (column === 'likes') {// convert string to array of words
            // with slice remove first and last bracket => remove any space and ' char => split words to an array
            document[column] = record[index].slice(1, -1).replaceAll(/\s*'\s*/g, "").split(',')
        } else {
            document[column] = record[index]
        }
    });
    return document
})


//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;

    /*
    If the database or collections have not been created before, they will be created when insert data
    */
    const database = client.db('hw1-data')//connect to db

    const pizzaCollection = database.collection("pizza")
    await pizzaCollection.createIndex({ "id": 1 }) //create index on id field in pizza collection


    const sportCollection = database.collection("sport")
    await sportCollection.createIndex({ "id": 1 }) //create index on id field in pizza collection

    try {
        //insert documents to collections
        await pizzaCollection.insertMany(pizzaDocuments)
        await sportCollection.insertMany(sportDocuments)
    } catch (err) {
        console.log(err.errmsg)
    }

    await client.close()
});