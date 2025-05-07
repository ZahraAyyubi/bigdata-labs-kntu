const MongoClient = require('mongodb').MongoClient;
//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const pizzaCollection = await client.db('hw1-data').collection("pizza")
    try {

        const queryResult = await pizzaCollection.aggregate([
            /*
            The following aggregation uses the $multiply expression in the $project pipeline to multiply the price and the quantity fields:
            */
            { $project: { name: 1, size: 1, totalPrice: { $multiply: ["$price", "$quantity"] } } },
            {
                $group: { //group by (name,size)
                    _id: {
                        "type": "$name",
                        "size": "$size"
                    },
                    totalOrderAmounts: { $sum: "$totalPrice" }
                }
            }
        ]).toArray()
        console.log(queryResult)

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
