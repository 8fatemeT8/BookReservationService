# BookReservationService

This project can be a platform for libraries or users to get information about booked or available books.This project also provides users with the ability to book and return books.
This project is implemented using Mango and Postgarsqal databases and its api are as follows:

UserController :  
      api : localhost:8080/api/user/register , method : PostMapping ,
      body : 
          {
            "username":"",
            "password":"",
            "email":""
          }
      This api is used to register a user (In response server returns a jwtToken)


      api : localhost:8080/api/user/login , method : PostMapping ,
      body:
          {
            "username":"",
            "password":"",
            "email":""
          }
      This api is used for user login (In response server returns a jwtToken)
      

BookController :
      api : localhost:8080/api/book/not-reserved , method : GetMapping ,
      param : send Pageable object
      This api returns books that have not yet been booked
      
      api : localhost:8080/api/book/search , method : GetMapping ,
      param : send Pageable object and BookCriteria object
      In this api, the search is done according to the fields entered in the bookCriteria
      
                     
 ReservationController :
      api : localhost:8080/api/reservation , method : GetMapping ,
      param : send Pageable object
      This api returns all reservation records (What is reserved and what is returned)
      
      api : localhost:8080/api/reservation/reserved-book , method : GetMapping ,
      param : send Pageable object
      This api returns reservation records which its book was not returned yet
      
      api : localhost:8080/api/reservation/reserve-book/{id} , method : PutMapping ,
      pathVariable : send book id (Long)
      This api reserves book for the current user and adds new reservation record in mongodb (also changes the book reservation status)
      
      api : localhost:8080/api/reservation/return-book/{id} , method : PutMapping ,
      pathVariable : send reserve id (String)
      This api updates the book return date in the current reservation record(also changes the book reservation status )      
          
      
      
