# note-app

# 1. Instalation.
  To run this application you need to install Docker(https://www.docker.com/)  and Maven Apache (https://maven.apache.org/download.cgi).
  Make sure both are available in your terminal:

   docker --version
   mvn --version
   
   You should see the version of Docker and Maven printed in the console.

# 2. Build 
  After installing dependencies, build Java-app and run them with Docker Compose:
  
  mvn -f pom.xml clean package -DskipTests

  docker compose up -d --build

#3 Creating notes
To execute commands, you need to run them inside the container. You can do this using the following command:

  docker exec -it note-app bash

After entering the container. You can create notes which contains Title, Created Date, Text and Tags  that can be empty. Tags is an enum from “BUSINESS”, “PERSONAL” and “IMPORTANT”:

  curl -v -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Morning Meeting","text":"Discussed project roadmap and upcoming deadlines.","tags":["BUSINESS"]}'

  curl -v -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Grocery Shopping","text":"Bought fruits, vegetables, and milk for the week.","tags":["PERSONAL"]}'

  сurl -v -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Important Client Call","text":"Call with ACME Corp. about contract renewal.","tags":["BUSINESS","IMPORTANT"]}'

  curl -v -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Workout Session","text":"Completed 5km run and strength training.","tags":["PERSONAL"]}'

  curl -v -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Project Deadline","text":"Finalize the report and submit to manager by Friday.","tags":["BUSINESS","IMPORTANT"]}'

Tags 
If everything is done correctly, you should see status 201 Created:

  * Mark bundle as not supporting multiuse
  < HTTP/1.1 201

If a note does not have a title or text, or already has an id when creating it, the server will respond with status 400 Bad Request:

  * Mark bundle as not supporting multiuse
  < HTTP/1.1 400

#4 Updating note
To update a note, first get all notes to copy the id of the note you want to update:

  curl -v -X GET http://localhost:8080/notes

Copy the "id" value, for example:

  "id": "exampleId123"

Then use this id in the following curl command to update the note:

  curl -v -X POST http://localhost:8080/exampleId123 -H "Content-Type: application/json" -d '{"title":"Project Deadline","text":"Finalize the report and submit to manager by Monday.","tags":["BUSINESS","IMPORTANT"]}'

If everything is done correctly, you should see status 200 OK

  Mark bundle as not supporting multiuse
  < HTTP/1.1 200

#5 Deleting note 
To delete note, you also need to copy id like on previous step. Then use this id in the following curl command to delete the note:

  curl -v -X DELETE http://localhost:8080/notes/exampleId123
  
If everything is done correctly, you should see status 204 No content:

  Mark bundle as not supporting multiuse
  < HTTP/1.1 204
  
#6 Getting stats about notes
You can see how many times words are repeated in your notes:

  curl -v -X GET http://localhost:8080/notes/stats

Example response you may get:

  {"title":"Watched a movie","map":{"the":1,"new":1,"incredible!":1,"sci-fi":1,"Saw":1,"was":1,"it":1,"movie,":1}}

This is HashMap, each entry in "map" contains a unique word from the note and the number of times it appears.

#7 Short listing 
Users can list notes showing only their title and createdAt by adding the query parameter:

   curl -v -X GET http://localhost:8080/notes?shortValue=true

Example response you may get:

  {"id":"69127a8509b51399fcd2e286","title":"Software Update","createdAt":"2025-11-10T23:51:33.254"},

#8 Getting text
Users can retrieve the text of a note by using the id of the desired note:

   curl -v -X GET http://localhost:8080/notes/text/69127a8009b51399fcd2e285

If the request is successful, you should see status 200 OK and the text of the note:

  Mark bundle as not supporting multiuse
  < HTTP/1.1 200
  
#9 Filtering and pagination
Users can filter notes by one or more provided tags and also specify the page number and page size for pagination.

  curl -v -X GET "http://localhost:8080/notes?tags=BUSINESS%20IMPORTANT&pages=0&size=2"

Example response you may get:
  {"id":"69127a5d09b51399fcd2e27f","title":"Important Client Call","createDate":"2025-11-10T23:50:53","tags":["BUSINESS","IMPORTANT"]},
  {"id":"69127a7a09b51399fcd2e284","title":"Project Deadline","createDate":"2025-11-10T23:51:22","tags":["BUSINESS","IMPORTANT"]}
