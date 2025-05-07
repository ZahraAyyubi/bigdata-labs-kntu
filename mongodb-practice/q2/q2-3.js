const MongoClient = require('mongodb').MongoClient;
const fs = require('fs');

//connect to mongodb server
MongoClient.connect("mongodb://localhost:27017/", async (err, client) => {
    if (err) throw err;
    const sportCollection = await client.db('hw1-data').collection("sport")
    try {
        //fetch sportsmen's name and joined field order by join date
        const sportsmenNames = await sportCollection.find(

            // to find documents where likes field contains both sports (tennis and volleyball) should use $all operator
            {
                likes: {
                    $all: ["Volleyball", "Tennis"]
                }
            },
            {// Include only the `name` field in the returned documents
                projection: { _id: 0, id: 1 },
            }).sort({
                id: 1 // sort docs order by id(name)
            }).toArray()

        //write names field to csv file
        fs.writeFileSync('./q2-3-out.csv', sportsmenNames.map(sportsman => sportsman.id).join('\n'))

    } catch (err) {
        console.log(err)
    }
    await client.close()
});
