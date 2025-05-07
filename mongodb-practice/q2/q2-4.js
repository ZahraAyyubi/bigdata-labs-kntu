const MongoClient = require('mongodb').MongoClient;
const fs = require('fs');

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const sportCollection = await client.db('hw1-data').collection("sport")
    try {
        const queryResult = await sportCollection.aggregate([
            {
                $match: { // get sportsmen that likes Swimming
                    likes: "Swimming"
                }
            },
            // Include only the `id` and `joined` fields in the returned documents
            { $project: { id: 1, joined: 1 } },

            //group documents by joined month
            {
                $group: {
                    _id: {//Whatever we put inside the _id field is used to group the documents.
                        "joinedMonth": { $month: { $toDate: "$joined" } }// with $month operator get month of join date
                    },
                    sportsmen: {
                        $push: "$id" //uses the $push accumulator to push sportsmen names for each group to an array
                    }
                }
            },
            {
                $sort: {
                    "_id.joinedMonth": 1
                }
            }
        ]).toArray()

        //save result to file
        fs.writeFileSync('./q2-4-out.csv', queryResult.map(group => `${group._id.joinedMonth},${[group.sportsmen]}`).join('\n'))
    } catch (err) {
        console.log(err)
    }
    await client.close()
});
