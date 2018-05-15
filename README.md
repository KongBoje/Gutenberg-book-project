# Gutenberg-books-project

### Database and Test project
By: Michael, Lasse and Martin

Project for the DB part can be seen [here](https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/assignments/Project%20Description.ipynb)

Project for the test part can be seen [here](https://github.com/datsoftlyngby/soft2018spring-test-teaching-material/blob/master/exercises/Final%20Assignment%202018.pdf)

### Database paradigms
- MySQL/PostgreSQL
- NoSQL (MongoDB)
- Neo4j Graph-database

### Help material
- https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/lecture_notes/15-Advanced%20Neo4J.ipynb
- https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/lecture_notes/Modeling%20Guidelines.pdf
- https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/lecture_notes/Neo4j%20Performance.pdf

---

## Test strategy for TDD

### Test cases
| Test case ID        | Test case objective              | Test case description               | Expected result          |
| --------------------|:--------------------------------:|:-----------------------------------:| ------------------------:|
| 1                   | Check connection to the database | Get books Title and Author          | Name on title and Author |

## Test strategy for BDD/TDD

### Test cases
| Test case ID        | Test case objective           | Test case description                 | Expected result        |
| --------------------|:-----------------------------:|:-------------------------------------:| ----------------------:|
| 1                   | Test getMentioned()           | Get All mentioned Cities in a book    | citiesMentioned(int)   |
| 2                   | Test getBooksByName()         | Get All books with similar name       | books.size() = 2       |
| 3                   | Test plotCities()             | Get coordinates of cities from list   | expected.equals(actual)|
| 4                   | Test getNearbyCities()        | Get All books within a specified range| expected.equals(actual)|
| 5                   | Test getAllBooks()            | Get All books with similar name       | books.size() == total  |

### UI Test
TBD
