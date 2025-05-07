const MongoClient = require('mongodb').MongoClient;
const util = require('util')

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const pizzaCollection = await client.db('hw1-data').collection("pizza")
    try {

        const queryResult = await pizzaCollection.aggregate([
            /*
            The following aggregation uses the $multiply expression in the $project pipeline to multiply the price and the quantity fields:
            */
            { $project: { date: 1, size: 1, totalPrice: { $multiply: ["$price", "$quantity"] } } },
            /*
            To find the distinct items in a collection and group them, we can use the group stage on any field that we want to group by.
            Whatever we put inside the _id field is used to group the documents.
            There are a lot of accumulator functions available in the group stage which can be used to aggregate the data like:$count, $sum, $push accumulator
            */
            //we will need two groups in this case.
            //group by (month,size)
            {
                $group: {
                    _id: {
                        "size": "$size",
                        "month": { $month: { $toDate: "$date" } }// with $month operator get month of  date
                    },
                    totalPrice: { $sum: "$totalPrice" }
                }
            },
            //Sorting the groups order by month(to be sorted in each size array later)
            {
                $sort: {
                    "_id.month": 1,
                }
            },
            //second $group will then merge all documents with the same size into one
            // using the $push operator to merge all month in that size into an array
            {
                $group: {
                    _id: "$_id.size",
                    monthSales: {
                        $push: {
                            month: "$_id.month",
                            totalPrice: "$totalPrice",
                        }
                    }
                }
            },
            //Sorting the results order by size
            {
                $sort: {
                    "_id": -1,
                    "monthSales.month": -1
                }
            }
        ]).toArray()


        console.log(util.inspect(queryResult, false, null, true /* enable colors */))

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
