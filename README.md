# jKool Streaming RESTFul API

###Why jKool Rest Clients?
This jKool Java Rest Client contains Java helper classes that will help you to get up and running very quickly with the jKool Streaming API. You will need a streaming  “token” in order to stream. This token will be associated with a repository that will be assigned to you when you sign-up for jKool.  The token is passed in the request header. We will be providing additional clients to use with other programming languages. Please check back. If you need them soon, they can be automatically generated with the Swagger Code Generator using the Swagger yaml file found it the "swagger" directory.  

###jKool Streaming Concepts and Documentation
You can find very comprehensive documentation on jKool Data Types and Concepts in our jKool Streaming Guide found here: https://www.jkoolcloud.com/download/jkool-model.pdf. But basically, there are four types of data that can be streamed into jKool. They are:
* Events: Represents a basic time series element containing time, message, severity and other fields associated with event.
* Activities: Represents a group of events and other activities (e.g. transactions).
* Snapshots: categorized metrics (name, value, type) at a "point in time". Snapshots can be associated with events, activities.
* Properties: simple metrics (name, value pairs). Properties can be associated with events, activities. 
* In addition to the Streaming Guide, this Git repository contains a Swagger yaml file. Open this file in a Swagger Editor and you will have detailed documentation of each field that comprises the above mentioned data.

###Using this helper code
To use this helper code please do the following:
* Run mvn install on the project. This will generate a jar file (found in the target directory).
* Import this jar file into your own project in which you wish to stream to jKool. 
* Please see the sample classes and run them in order to get a good understanding on how to use the helper code. You will be doing the following:
* Instantiate the `jKoolStream` object. You will need to pass it the access token you received when you signed up for jKool. This token will grant you access to stream and also ensure that the data goes to the proper repository.
```java
		jKoolStream jkSend = new jKoolStream("yourtoken");
```
* Instantiate the object you wish to stream. Then populate all of the fields you wish to stream. For example:
```java
		Event event = new Event("Casablanca");
		event.setAppl("WebOrders").setServer(InetAddress.getLocalHost().getHostName())
		        .setNetAddr(InetAddress.getLocalHost().getHostAddress()).setDataCenter("DCNY")
		        .setElapsedTimeUsec(TimeUnit.HOURS.toMicros(2)).setSourceUrl("http://www.movies.com")
		        .setLocation("New York, NY").setMsgText("Casablanca is playing.");

```
Please note that this example code depicts streaming in real-time. Therefore the start date of the event will default to the current date/time and the end date will default to the start date plus the elapsed time. You can however control start/end dates by setting start and end dates with with a java date object. For example:
```java
		String movieDate = new Date();
		event.setTimeUsec(movieDate).setElapsedTimeUsec(TimeUnit.HOURS.toMicros(2));
```

* Finally, invoke the post method on the `jKoolStream` object, passing it the object you wish to stream. For example:

```java
		jKoolSend jkSend = new jKoolStream("yourtoken");
		Response response = jkSend.post(event);
		response.close();
```
The Rest Client will properly format the entity into JSON format and stream it to jKool over default `https` protocol.

That's it!! Any problems or concerns, please email us at (`support at jkoolcloud.com`).

###Important note
This sample code showcases some basic examples of using jKool Rest API. jKool can handle very complex application interactions. For example, it is built with the ability to correlate events and track transactions among multiple applications. This can be used for complex tracking and analytics.

###Streaming with Curl
Data can also be streamed into jKool using Curl. Below is an example:

```java
curl -i -H "Content-Type:application/json" -H "token:YOURTOKEN" -X POST https://data.jkoolcloud.com/JESL/event -d '{"operation":"streamingwithcurl","type":"EVENT","start-time-usec":1457524800000000,"end-time-usec":1457524800000000,"msg-text":"Example Curl Streaming","source-fqn":"APPL=TestingCurl#SERVER=CurlServer100#NETADDR=11.0.0.2#DATACENTER=DC1#GEOADDR=52.52437,13.41053"}'
```

###Streaming with Python
Data can also be streamed into jKool using Python. To do so, you will need to use the Python "Request". Details on the Python Request can be found here - http://docs.python-requests.org/en/latest/user/quickstart/. Below is an example of using it to stream into jKool:

```java
import requests
headers = {'token': 'YOURTOKEN'}
payload={'operation':'streamingwithpython','type':'EVENT','start-time-usec':1457524800000000,'end-time-usec':1457524800000000,'msg-text':'Example Python Streaming','source-fqn':'APPL=TestingCurl#SERVER=CurlServer100#NETADDR=11.0.0.2#DATACENTER=DC1#GEOADDR=52.52437,13.41053'}
r = requests.post('https://data.jkoolcloud.com/JESL/event', headers=headers, json=payload)
```


