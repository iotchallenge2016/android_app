# Parkify Mobile App

When the user launches the app there is a request for the data base to retrieve the sections of the parking lot,then the user selects the section where he/she is going. The algorithm from the data base determines the best parking section for the user according to the available spaces and the destination. 

<img src="https://github.com/iotchallenge2016/android_app/blob/master/app1.jpg" width="400"> 
<img src="https://github.com/iotchallenge2016/android_app/blob/master/app2.jpg" width="400"> 

The app makes a http request to the data base which returns a JSON,that contains the destinations for the users; When the users choose an option the request its made and another JSON file is recived with the best parking section available(determined by the graph algorithm).
