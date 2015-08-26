# SpringSampleApplication
To store and query structured and unstructured data from both relational and key-value data stores.
This data can be of ANY structure (so long as the JSON is well formed).
Example of JSON is below.
This application will have a REST based interface that will accept JSON requests (over HTTP) and then pass them onto a queue system (JMS) to be persisted into the unstructured database.
The response of the success/failure of the write will then be passed onto the AMQP broker and ultimately A record will also be written to denote that a request has taking place to the postgres DB (which can also be queried over an REST interface).
The user can then query the store requesting for all JSON documents that contain a giving element and/or value

Sample Json

{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021"
  },
  "phoneNumber": [
    {
      "type": "home",
      "number": "212 555-1234"
    },
    {
      "type": "fax",
      "number": "646 555-4567"
    }
  ],
  "gender": {
    "type": "male"
  }
}