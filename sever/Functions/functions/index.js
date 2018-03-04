const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
// Import Admin SDK
const admin = require("firebase-admin");
admin.initializeApp(functions.config().firebase);

var db = admin.database();
var ref = db.ref("Priority0");

exports.sendAlert = functions.ref.onWrite(event => {
    var newPost = event.data.val();
    if(newPost.status === 3){ //check to see if the person pushed an emergency
        var postsRef = ref.child("Priority0"); //send a new entry with an alert code
        var newPostRef = postsRef.push();
        newPostRef.set({
            ID: newPost.ID,
            GPS: newPost.GPS,
            status: 5
        });
    }
    return event.data
  });
