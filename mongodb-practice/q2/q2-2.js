const MongoClient = require('mongodb').MongoClient;
const fs = require('fs');

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const sportCollection = await client.db('hw1-data').collection("sport")
    try {
        //fetch sportsmen's name and joined field order by join date
        const sportsmen = await sportCollection.aggregate([
            //Sorts all input documents and returns them to the pipeline in sorted order by $sort stage
            {
                $sort: {
                    joined: 1 //Sort ascending
                }
            },
            {// Include only the `id` and `joined` fields in the returned documents
                $project: { _id: 0, id: 1, joined: 1 },
            }
        ]).toArray()

        //write names and joined field to csv file
        fs.writeFileSync('./q2-2-out.csv', sportsmen.map(sportsman => `${sportsman.id},${sportsman.joined}`).join('\n'))

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
