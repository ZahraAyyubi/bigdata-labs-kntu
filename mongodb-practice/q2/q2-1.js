const MongoClient = require('mongodb').MongoClient;
const fs = require('fs');

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;

    const sportCollection = await client.db('hw1-data').collection("sport")

    try {
        //Names of sportsmen in alphabetical order
        const sportsmenNames = await sportCollection.aggregate([
            //Sorts all input documents and returns them to the pipeline in sorted order by $sort stage
            {
                $sort: {
                    id: 1 //Sort ascending
                }
            },
            {// Include only the `id` field in the returned documents
                $project: { _id: 0, id: 1 },
            }
        ]).toArray()

        //write names to csv file
        fs.writeFileSync('./q2-1-out.csv', sportsmenNames.map(sportsman => sportsman.id).join('\n'))

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
