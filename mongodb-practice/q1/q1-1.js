const MongoClient = require('mongodb').MongoClient;
const fs = require('fs');

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;

    const pizzaCollection = await client.db('hw1-data').collection("pizza")

    try {
        //fetch pizza with medium size
        const mediumSizePizzaNames = await pizzaCollection.find(
            { size: 'medium' },
            {// Include only the `name` field in the returned documents
                projection: { _id: 0, name: 1 },
            }).toArray()

        //write names to csv file
        fs.writeFileSync('./q1-1-out.csv', mediumSizePizzaNames.map(pizza => pizza.name).join('\n'))

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
