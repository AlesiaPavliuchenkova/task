# task
Objective of the Test Project
=============================

Create a simple Web Application, which uses RESTful API to communicate with server.

Technical Requirements
======================

UI module:

1. Simple HTML form with next elements:
  - textarea field "Input";
  - textarea field "Output";
  - button "Proceed";
2. JavaScript code (jQuery is also allowed) to get data from the Input field, when click on the Proceed button, and send it to a server, using AJAX.
3. This should be a POST request to the Server, with JSON document (in any format) to transfer all text from the Input field.
4. Response should be displayed in the Output field, in next format: sorted words in descending order, with count of the entries in the text, and count of the unique words. Example:

    Hello - 5
    World - 3
    Earth - 2

    Unique: 3

Server module:

1. Simple server application on the Spring framework.
2. REST controller with an endpoint to process requests from the UI.
3. When controller received data (a list of words from the textarea), need to calculate count of unique words and their entries in the text.
4. Return response with a JSON body (in any format, which you like to use) to the UI.
5. Return some error code in case of any issues with a request.
