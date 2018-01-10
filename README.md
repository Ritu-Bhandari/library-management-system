# Library-management
Library Management Project

# Library-management
Library Management Project


1) TO Add books :
http://localhost:8080/library/v1/add/books
POST :
[
{	"name" : "firstBook",
	"title" : "the first book",
	"author" : "author name 1"
},
{	"name" : "secondBook",
	"title" : "the second book",
	"author" : "author name 2"
},
{	"name" : "third book",
	"title" : "the third book",
	"author" : "author name 3"
}
]



2) To Add Users :
http://localhost:8080/library/v1/add/users
POST :
 
[
{	"name" : "ritu bhandari"
},
{	"name" : "ABC"
},
{	"name" : "XYZ"
}

]

3) To Search book by author name -> 

GET 
http://localhost:8080/library/v1/search/book?author=author name 1

To Search book by title -> 
GET
http://localhost:8080/library/v1/search/book?title=the second book 

4) To Search user by name 
GET 
http://localhost:8080/library/v1/search/user?name=ritu bhandari

5) Lend ->
http://localhost:8080/library/v1/lend
{
	"userId" : "7aa215b3-d9f1-460d-a5dd-25e08da48e52", -> unique id generated for user
	"booksId" : ["fee05076-6126-4b98-80c1-ab41c932ebbb"] -> book ids generated for books
}

When you load the same book -> status will change to assigned.

6) Return -> 
http://localhost:8080/library/v1/return
{
	"booksId" : ["49758cb1-4c21-4a92-a2a3-9b1d8f81cd5d"]
}

7)
To set the upper limit for an user:
POST
http://localhost:8080/library/v1/limit
{
	"userId" : "b39888d7-c476-4830-9e12-11348ed245ea",
	"upperLimit" : 3
}



