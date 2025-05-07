const MongoClient = require('mongodb').MongoClient;

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const pizzaCollection = await client.db('hw1-data').collection("pizza")
    try {
        //get pepperoni pizza counts
        //aggregate $count with condition
        /*
         the $match stage will exclude documents that their name field is not pepperoni.
         the $count stage returns a count of the remaining documents in the aggregation pipeline
          and assigns the value to a field called “pepperoniCount”.
        */
        const queryResult = await pizzaCollection.aggregate([
            {
                $match: {
                    name: "Pepperoni"
                }
            },
            {
                $count: "pepperoniCount"
            }
        ]).toArray()
        console.log("pepperoniCount: ", queryResult[0].pepperoniCount)

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
