KonaCloud.io
============
Welcome to KonaCloud.io

KonaCloud.io is for developers by developers. A platform to run all your back-ends, APIs and bussiness logic. Runs JavaScript on the server-side so you don't have to learn a new language.
KONA is a Backend-as-a-Service platform which allows you to create data models, code custom APIs, create and use object storage buckets and reporting for your data.
KONA custom APIs are coded in the latest JavaScript. These are then executed by the all-new, super-fast  and powerful Nashorn JavaScript engine from Oracle's JVM 8.

- Website: http://konacloud.io
- Developer Portal: http://developer.konacloud.io

## Getting Started
Go to getting started http://konacloud.io/doc/getting-started.html

## Start with some tutorials

Check out some examples

### Anguar.js
http://konacloud.io/doc/quickstart_employee.html

### Ionic framework
http://konacloud.io/doc/users.html

### IOS
http://konacloud.io/doc/cookerapp.html

## Command Line Tool (KONA Cli)

Want to go offline?
Check out the kona cli tool http://konacloud.github.io/KONACli/


## Kona APP Architecture

![ScreenShot](http://i.imgur.com/bS6AJu3.png)


Templates and Examples
===============

### Model Resources

the code is automaticaly created

### Custom Resource

```js
var model = kona.model.open('{modelId}');

var get = function(req) {
	
	var id = req.params.get("id);
	return model.queryById(id);
};

```
### JOB

```js
var run = function(){
	log("hi);
}
```

### Library

```js
var abs = function(num){
	if (num>0)
		return num;
	else
		return num*(-1);
}
```

# Basic Uses

## Hello Word Demo

Hello word without models, buckets or nothing.
40 seconds.

<iframe width="560" height="315" src="//www.youtube.com/embed/vTJpU-TGKMU" frameborder="0" allowfullscreen></iframe>

## Object JS Mode

You can create and use JS Object and them pass they to the kona methods

```js
var obj = {
	"name": "Ear",
	"age":19
};
var str = JSON.stringify(obj); //this is a String.
```


## HOW to Use Object in Kona


Diferents ways to create objects, you can use JS Object or Java Object, for example

```js
var test = function() {

    var obj = kona.obj();
    obj.put("name","obj1");
    model.insert(obj);

    var obj2 = new Object();
    obj2.name = "obj2";
    model.insert(obj2);
    
    var obj3 = {
        name : "obj3"
    };
    model.insert(obj3);
};
```

You can create as ANY WAY object is created in java, for communication between the script and utlize kona.js
usually an object of type KonaDO compliant json format with other properties.

The following are valid

```js
var obj = kona.obj();
```

## List

```js
var obj = new ArrayList<KonaDO>(); //o
var obj = kona.list();
```

## Dates

Managin dates in KONA

We recomend to use the ISO standar for dates, for example

```js
{ "fecha" : "2014-07-08T03:30:28.650Z"}
```

```js
var test = function(){
   
   var obj = kona.obj();
   
   var now = new Date();
   obj.put("now in iso", now.toJSON());
   
   return obj;
};
```

##How to Log

Yo can log all kond of stuff, for example

```js
log("This is a common log");
log.err("Hi this is han error");
log.info("This is han info log");
log.war("This is han warining log");
```

If you use the "Debug" Button you can see the logs for the test method, and web yous clientes acces to the resources then the logs area stored and you can see them in the Log View

and the result is something like this

```js
//Use log('text') to log
This is a common log
[ERROR] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)Hi this is han error
[INFO] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)This is han info log
[WAR] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)This is han warining log
```

## TEST (Traducir)

Each API must implement the test method, like var test = function(){}

```js
	assert(p, q)
	notAssert(p, q)
```

Sending an error

```js
	if (somethingStrange()){
		kona.error("This is bad");
	}
```

Consideramos una buena practica que todos los metodos tengan su test, auque deben tener cuidado de no insertar datos en cada test (queda a criterio del desarrollador)



## Request Structure

Cada request a un resource viene con la siguiente estructura, por ejemplo

```js
req.header.get("some_header")
req.params.get("some_param")
req.body.get("field");

req.method //is the method

req.isPost();
req.isGet();
req.isPut();
req.isDelete();
```

### Getting the headers

```js
var get = function(req) {
    return req.headers.get("asd");
};
```

### Getting URL Params

The API URL its like konacloud.io/api.../myapi?param=someVAlue

```js
var get = function(req) {
    return req.params.get("param");
};
```

Importantly, the parameters obtained are Strings, to convert to another type you have to do it manually, eg

Getting a boolean value form URL

```js
var get = function(req) {
	var strParam = req.params.get("param");
	var booleanValue = (strParam === 'true');
	return booleanValue;
};
```

Getting a int value form URL

```js
var get = function(req) {
	var strParam = req.params.get("param");
	var myInt = parseInt(strParam);
};
```

## Libraries

First create a library, and save it for exampe as timeUtil
```js
var util = function(){
    return (new Date()).getTime();
};

var test = function(){
    return util();
}
```

From other API we include the library and use it.

```js
include("timeUtil");

var test = function(){
    return timeUtil.util();
};
```

# Safe methods

This methods allow us to play safe with JS and KONA Integration.

## To JavaScript

thi is to use the response as JS JSon, for example
```js
obj = kona.somepackage.somefunction();
var safeJSObject = toJS(obj);

var some = safeJSObject.someProperty
```

## To Kona Object (Java)

To call KONA functions from JS, just do this

```js
obj = {
	...
}
var safeKO = toJson(obj);
result = kona.somepackage.somefunction(safeKO);
```

#Recipes

## Login

Asume we have a model User with email and password atributes

```js
var login = function(req) {
    
    if (req.body.get("email") == null) {
        kona.error("email is missing");
    }
    
    var find = {
        email : req.body.get("email")
    }
    var list = model.buildQuery().find(find).list();
    if (list.size()==0){
        kona.error("user not found");
    }
    var user = list.get(0);
    if (user.get("password") == req.body.get("password")){
        return {succes:true};
    }else {
        return {succes:false};
    }
};
```

# STORAGE

The KONA default persistence is through the Model Service. 

The first thing to do is define our models, indicating for each attribute the name and type, in addition to the validations. 

If the user does not want to use this mecanism, KONA gives the possibility to use 

 - Redis 
 - Memcache 
 - MySQL 
 - Postgress


## Model Services

Are the services provided by KONA for managing entities, queries and their relationships.
As an example we will compare what we do with sql engines, how we do in KONA.
The first thing we do is create the contorlador of our model (Model Controller) to access their APIs.
For example suppose you have a Person model with name, lastName attributes, and email

```js
var mc = kona.model.open("Person");
```

The rest of you will be varible mc (Model Controller for Person).

### Insert a New Object


```js
var person = {
	name : "Bart",
	lastName : "Simpson",
	email : "bart@konacloud"
}

mc.insert(person);
```

With this always insert a new entity and creates ha new id for the object, if we get this object its looks like
```js
var person = {
	name : "Bart",
	lastName : "Simpson",
	email : "bart@konacloud",
	_id : "51243na314aae34"
}
```

### Update a Object

### Update By Id

To update an object is necessary to know its id.
Later when we see the queries, they get the id of all the items you want.

```js
var person = {
	name : "Bartolomeo",
	lastName : "Simpson",
	email : "bart@konacloud.io",
	_id : "51243na314aae34"
}

mc.save(person);
```


If an object has 10 attributes and we only 2 in the update, the object keeps the other equal to its previous value. 

This support for the upgrade of type HTTP PATH, the client need only send the value to change, for example 

We have the following object.

```js
var obj = {
	_id : "532452345.."
	name : "bart",
	email : "bart@email.com",
	account : "free"
}
```

And we need to update the account, so

```js
var objToUpdate = {
	_id : "532452345.."
	account : "business"
}

model.save(objToUpdate);
```

and the objecto finally is
```js
var obj = {
	_id : "532452345.."
	name : "bart",
	email : "bart@email.com",
	account : "business"
}
```

#### find And Update

If We want to update all the Persons who has the name eq "Bar"

```js
var barto = {
	name: "Bart"
}
var person {
	lastName: "Simpson";
}
mc.findAndUpdate(barto, person);
```
Then all the elements with name Bart wil hace the lastName eq Simpson

#### Add to list (Push)

For example if you have a list of friends per user and you want to add a friend to this list.

Our user model has an array of user ids.

```js
var modelUsers = kona.model.open('User');
var q = { _id: toUserId };
var u = { $push: { friends: fromUserId } };
modelUsers.findAndUpdate( q,  u);
```

### Delete a Object

### Delete By Id
To delete an object is necessary to know its id.

```js
mc.deleteById("51243na314aae34");
```

### delete all objects in a collection
```js
model.all().forEach(function(i) {
     model.deleteById(i._id);
});
```
   
### find And Delete
Same as before, if we want to delete all the Persons who has the name eq "Bar"

```js
var barto = {
	name: "Bart"
}
mc.findAndDelete(barto);
```

### Query List

```js
var m = kona.model.open("person");
var list = m.query('person',"{name:'me'}"); //obtenemos todas las personas con nombre igual a 'me'

```

### Query By Id

```js
var m = kona.model.open("person");
m.queryById("id1");

```

### Queries (New)

We will build the query with all the thing that we like, params, regexp, sorters, etc, and finaly we get the result in ha list or in a single element

firts of all web get the query object, here we can pass data to find or not

In this example we will do it in the large way, and finaly we show some helpers

### Build Query

```js
var q = mc.buildQuery();
```

### Get All

In SQL to get all the elements we do

select * from Person, and in Kona

```js
var list = q.list();
```

### Pagination

in mobile applications is very common to use pagination when you scrols on list views (android) for example,
it is very important to consider the paging when working with lots of data.
KONA default leaves a request ModelService.MAX_QUERY bring over data elements are at most 100 non premium accounts,
however it would be best not to have to send more request but the result page always.

The page is defined as a limit and offset, so the pages may have dynamic size.

```js
q = q.limit(10).offset(5);
var list = q.list();

or justo

var list = q.limit(10).offset(5).list();
```
we get at max 10 elements but we skip the firs 5 elements

### Complex Queries

Select * from Person where name = "Bart";

```js
var find = {
	name : "Bart"
}
var list = q.find(find).list();
```

If we know that our query will result in a single result allway, search by id or some atribute that is unique (see Contrasits).

We can do this in any query.

```js
var find = {
	name : "Bart"
}
var person = q.find(find).single();
```

Of course the object find can have many atributes as we want.

Query Examples

### Compare atributes in Queries

SELECT * FROM Person WHERE name = "Bart" OR lastName = "Simpson"

```js
find = { $or: [{ name: "Bart"},{lastName:"Simpson"}] };
var list = q.find(find).list();
```

If the person has an age, and we have to to the all the persons with age > 25

SELECT * FROM Person WHERE age > 25;

```js
find = { age: { $gt: 25 } };
var list = q.find(find).list();
```

age > 25 --> $gt: 25
age < 25 --> $lt: 25

SELECT * FROM Person WHERE age > 25 and age<=50;

find = { age: { $gt: 25, $lte: 50 }

#### Using the ID

to compare by id do this

```js
var _id = kona.mongo.objectId("");
```
### Like in Queries RegEX

We can use RegExp or if the only thing tha we need is the like we can do this.

SELECT * FROM Person WHERE name like "%Bart%";

```js
var find = {
	name : {
		$regex : ".*some.*"
	}
}
var person = q.find(find).list();
```


### Using or and regex

```js
var search = function(req) {
    var text = req.params.get("text");
    
    var exp = {
	        $regex : ".*"+text+".*"
    }
    
    var find = { $or: [{ Name: exp},{LastName:exp}] };
    var q = model.buildQuery();
    
    return q.find(find).list();
}
```

### Sorting

SELECT * FROM Person order by age ASC

```js
var find = {
	name : "/ar/"
}

var sorter = {
	age : 1
}
var person = q.find(find).sort(sorter).list();
```

SELECT * FROM Person order by age DESC

```js
var find = {
	name : "/ar/"
}

var sorter = {
	age : -1
}
var person = q.find(find).sort(sorter).list();
```

### Count

Select count(*) from Person where name = "Bart";

```js
var find = {
	name : "Bart"
}
var size = q.find(find).count();
```

### Get Only Some params

This is very important if we have 10 atributes but we only need 2 to show up.


```js
var find = {
	name : "Bart"
}

var keys = {
	name: 1
}
var names = q.find(find).keys(keys).list();
```

This will list only the names of the Persons

### find RegEx with case insensitive and pagination

A more real example for mobile apps.

Find a description that contains the text and case insensitive.


```js
var findByDescription = function(req)
{
    
    var text = req.params.get("text");
    var page = req.params.get("page");
    
	var find = {
        	descripcion : {
            	$regex : ".*" + text + ".*",
            	$options: 'i'
        }
    }
    
    find = toJson(find);
    var q = model.buildQuery().find(find).limit(MAX_ITEMS_PER_PAGE).offset(MAX_ITEMS_PER_PAGE*page);
    var list = q.list();
}
```

### Full Text Search

http://en.wikipedia.org/wiki/Full_text_search

```js
var test = function() {
    
    return model.buildQuery().textSearch("kona").list();
};
```

### Short way to Start


```js
var mc = kona.model.open("Person");

var find = {
	name : "Bart"
}

var list = mc.buildQuery(find).list();

var list = mc.buildQuery(find,10,5).list();

```


## REDIS

Redis is an open source, BSD licensed, advanced key-value store. It is often referred to as a data structure server since keys can contain strings, hashes, lists, sets and sorted sets.

more info http://redis.io/

We recomend the use of redislab as the redis cloud provider

http://redislabs.com/

Code Example

```js
var test = function(){
    
    var conf = {
        url : "pub-redis-16812.us-east-1-3.4.ec2.garantiadata.com",
        port: 16812
    }
    
    var r = kona.redis.open(conf);
    r.set("key","Kona");
    
    return r.get("key");
};
```

## MemCache

Free & open source, high-performance, distributed memory object caching system, generic in nature, but intended for use in speeding up dynamic web applications by alleviating database load.

Memcache is no for persistence, its only a cache.

We also recomend to use redislab

CodeExample

```js
var test = function(){
    
    var conf = {
        url : "pub-redis-16812.us-east-1-3.4.ec2.garantiadata.com",
        port: 16812
    }
    
    var r = kona.memcache.open(conf);
    
    r.set("key",3600,"Kona"); //one hour
    
    return r.get("key");
};
```

## MySQL

We reomend the use of cloud mysql prividers


http://aws.amazon.com/es/rds/mysql/

https://www.cleardb.com/

```js
var test = function(){
    var conn = {
        url : "smartech.com.uy:3306/sakila",
        user : "dba",
        password : "matematica"
    }
    
    var cm = kona.mysql.open(conn);
    
    var result = cm.run("SELECT * FROM sakila.actor limit 100");
    return result;
};
```

The result is a json like this

```js
[{ "actor_id" : 1 ,
  "first_name" : "PENELOPE" ,
  "last_name" : "GUINESS" ,
  "last_update" : { "$date" : "2006-02-15T06:34:33.000Z"}}]
```

## PostGres

We reomend the use of cloud postgress prividers

https://www.heroku.com/postgres

http://www.elephantsql.com/


```js
var test = function(){
    var conn = {
        url : "ec2-54-197-250-40.compute-1.amazonaws.com:5432/dbs0egkrel1vj5?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
        user : "gqlycejaedesyn",
        password : "mAPLqdMr7YU_I8qMZhUTyr_vhm"
    }
    
    var cm = kona.postgres.open(conn);
    
    var result = cm.run("select * from films limit 2");
    return result;
};
```

The result is a json like this

```js
[{ "code" : "a    " ,
  "title" : "A"},
  { "code" : "b    " ,
  "title" : "B"}]
```
# COMUNICATION

## EMAIL


There are 2 ways to send mails. 

SendGrid mediantes external services such as configuring a server or SMTP as gmail. 

We recommend using SendGrid. 

### SENDGRID

As of today has 200 mails per day free. 

then create the user account and get a pass. 

Example of code.

```js
var test = function(){
    var conf = {
        user: "user",
        password : "password"
    }
    
    var ms = kona.sendgrid.open(conf);
    
    var email = {
        to : "some@email.com",
        from : "info@yourcompany.com",
        fromName : "Your Company",
        subject : "Subject",
        text : "Some text"
    }
    
    ms.send(email);
};

```

### Custom SMTP Server

Example with Gmail

```js
var test = function(){
    
    var conf = {
        smtp : "smtp.gmail.com",
        port : "587",
        user : "account@gmail.com",
        pass : "password"
    };
    
    var ms = kona.email.open(conf);
    
    var email = {
        to : "some@email.com",
        subject : "Hi",
        text : "Hi from kona"
    }
    
    ms.send(email);
};
```
#### Advanced Configuration

Import and use direct the Simple Java Mail Api

more info  https://code.google.com/p/simple-java-mail/wiki/Manual

```js
load("nashorn:mozilla_compat.js");
importPackage(org.kona.js.mail);
```

```js
var email = new Email();
email.setFromAddress("taio", "taio@linux-mail.com");
email.setSubject("hey");
email.addRecipient("other", "some@kona.org", RecipientType.TO);
email.addRecipient("diego", "some@kona.org", RecipientType.BCC);
email.setText("Kona");
email.setTextHTML("<img src='..'><b>some text</b><img src='..'>");

email.addEmbeddedImage("wink1", imageByteArray, "image/png");
email.addEmbeddedImage("wink2", imageDatesource);
email.addAttachment("invitation", pdfByteArray, "application/pdf");
email.addAttachment("dresscode", odfDatasource);

m.send(email);

```

### Using Kona Mail Sender

```js
kona.email.send('email@server.com','Subject','Content');

```

## SMS

```js
  kona.sms.send('12312312','my sms text');

  Important the number must have the country code, because is global :)

  for example for Uruguay 59899123123 for the number 099 123 123

```

Demo Video
<iframe width="560" height="315" src="//www.youtube.com/embed/YnOMuFf7q0E" frameborder="0" allowfullscreen></iframe>

# SERVICES


## Rest Client v2


How to Call a Rest Service

### GET Example

```js
var test = function(){
    var request = {
        url : "http://app.konacloud.io/api/taio/sample1/mr_model1",
        method : "get",
        as : "json"
    }
    
    var data = kona.net.send(request);
    
    if (data.code == 200)
        return JSON.parse(data.response);
    kona.error("http error " + data.code)
};
```

### POST Example

```js
var test = function(){
    
    var data = {
        name : "My Name is Ear"
    }
    
    var request = {
        url : "http://app.konacloud.io/api/taio/hello/mr_Person",
        method : "post",
        data : data
    }
    
    var data = kona.net.send(request);
    
    if (data.code == 200)
        return JSON.parse(data.response);
        
    kona.error("http error " + data.code)
};
```

For PUT and Delete methods is the same way.


If you want the result as a String Stream just add the as parameter

```js
	as : "string"
```


## Rest Client v1 (old way)

Api to communicate by rest to other services on the web.

Supported Protocols and Architectures
REST (json)

### Fast Way

```js
function f(){
	var obj = kona.obj();
	var api = kona.net.get();
	var r = api.call("http://api.openweathermap.org/data/2.1/weather/city/3441575?units=metric");
	obj.put('temperatura',r.get("main").get("temp"));
	return obj;
}
```

Response
```js
{
temperatura: 19.25
}
```

### Metodo Get

```js
var api = kona.net().get();
}
```

### Metodo Post

```js
var data = kona.obj();
data.put("hola",123);

var api = kona.net.post(data);
var r = api.call("http://postexample.com");

```

### Metodo Put

```js
var data = kona.obj();
data.put("put",12);

var api = kona.net.put(data);
var r = api.call("http://putexample.com");

```

# Push Notifications

## Andorid Push Notification

### Server Side

First add the Android APiKey in the app lication settings

Code Example

```js
var test = function(){
    
    var payload = kona.obj("message","Hi from kona");
    payload.put("time",(new Date()).getTime());
    kona.notifications.androidPush("token",payload)
};

```

### Client Side

Ejemplo del codigo para el cliente se puede encontrar en 

https://github.com/konacloud/samples/tree/master/android%20push%20notification%20example


## IOS Push Notifications

```js
var test = function(){
    
    var payload = {"aps":{"alert":{"body":"push desde KONA","title":"el titulo","action":"Si o no?"},"sound":"default","badge":1},"micampo":"valor"};

	kona.notifications.iosPush("fc85574de6588e5926d286743e1b99f5aa8fd5fa1f600612a4b01376695fcad6",JSON.stringify(payload));
    return kona.obj(true);
};

```

# GeoCoding

The idea is to have an api to work with maps and locations

One line code Geocode api


```js
  kona.map.geocode("uruguay montevideo charrua 1880");

```
result

```js
{ "latitude" : -34.9062205 ,
  "longitude" : -56.174852}

```

One line code Geocode reverse api

```js
kona.map.reverse(-34.9062205,-56.174852);
```

result

```js
{ "region" : "Montevideo Department" ,
  "zip" : "11200" ,
  "street_name" : "CharrÃºa" ,
  "street_number" : "1880" ,
  "longitude" : -56.174852 ,
  "latitude" : -34.9062205 ,
  "country" : "Uruguay" ,
  "city" : "Montevideo"}

```

Demo Video

<iframe width="560" height="315" src="//www.youtube.com/embed/wAtOfGyngiY" frameborder="0" allowfullscreen></iframe>

# Utils


### QR Generation

```js
    return kona.img.qr("some text");
```

Demo video:

<iframe width="420" height="315" src="//www.youtube.com/embed/0LZ8L3sntfI" frameborder="0" allowfullscreen></iframe>

## Net Util Service

The idea is to have an api to manage net stuff

One line code

```js
    return kona.net.search("konacloud.org");
```

### Misc Utils

```js
/*
 * Search domain
 */

kona.net.search("domain");

/*
 * Ping a Host
 */

kona.net.ping("ip");

/*
 * Check open ports in host
 */

checkPort("host",80);

/*
 *  dn2ip
 */
dn2ip("host");

/*
 *  ip2dn
 */

ip2dn("ip");
```

# File Service

The File Service is to create files, pdf, txt, doc or ms ms docx, html formats are supported.

The idea is to write rich text (html) and convert them to other formats, persisting for the last file in a special bucket (default)

##Making a PDF and Sendit by email

```js
function get(){
    
    out = kona.obj();
    out.put('name','kona');M
    out.put('lastname','cloud');
    out.put('time',simple.format(new java.util.Date()));

    var doc = kona.file();
    doc.add(out);
    url = doc.build();

    kona.email.send("santiago@konacloud.org","The PDF","Here is the pdf " + url)
    return out;
}
```


# Buckets

When you create a bucket, only need to post data to the bucket URL

## Receive files in a bucket

All files are cached in the browser forever since a url is unique per file. 

### Resize

To resize the image can be sent the following parameters 

s: 

with the parameter s image is of size s (width and height) 

example 

http://url-to-image/filename?s=128 

This returns us an image of 128x128 

w and h: 

also can send the height and width separately 

http://url-to-image/filename?w=128&h=200 

This returns us an image of 128x200



#Schedule

Como ejecutar un cron job en Kona.

Creamos nuestro metodo y lo guardamos como JOB.

![ScreenShot](http://i.imgur.com/GblhAFm.png)

Deberiamos poder verlo de la siguiente manera

![ScreenShot](http://i.imgur.com/bAJvpzq.png)

Ahora en la ventana de Schedule, definimos el Cron Tab (Unix Cron Tab)

Ejemplos de unix cron tabs
http://en.wikipedia.org/wiki/Cron#Examples

Se puede partir de una ayuda visual para armar el cron, indicando minutos, horas, dia, mes, week

Si se ingresa un * en horas significa todas las horas por ejemplo, y si se ingresa 1 significa solo la hora 1.

![ScreenShot](http://i.imgur.com/otZxl5R.png)


Luego se puede editar manualmente el cron tab, verificar que sea valido el ingresado

![ScreenShot](http://i.imgur.com/yaq0MC7.png)


De cada Cron Job vemos la siguiente informacion y tenemos las siguientes opciones
![ScreenShot](http://i.imgur.com/D4ArwEu.png)

Podemos ejecutarlo y ver en la KConsole sus logs de ejecucion y el resultado si es que existe.

Para ver los logs de las ejecuciones programadas hacer click en Logs

kona.js


#OpenKonaJS


OpenKonaJS is han open framework used by KonaJS at runtime


We allow developers to add code and just send us the pull request

## How add code to OpenKonaJS

Its simple,

Tools:

Java
Eclipse

Firts create ha interface like MySampleService in the package org.kona.js (only on this package), them create ha Java class that implements the interface, like MySampleServiceImpl, please use the names that we recomend (Service and ServiceImpl).

Then, in the class OpenKonaJSFactory add ha public property (yes, do this in Java :) ), like

MySampleService sample = new MySampleServiceImpl();

or the other posibility is to add in IOpenKonaJSFactory one method and implement in OpenKonaJSFactory, but we recomen the other way, the namespace its very importante

When the finaly user use the code, he will just type

```js
var resp = kona.sample.myFunction("hi");
```

add all the methods in the interface and implement them in the ServiceImpl.

Finaly add ha test case for your method in the apropiate package.

# External Services

## Loggin in the cloud

### loggly

Simplify Log Management
With a simple setup process and easy-to-use tools, Loggly takes the hassle out of log management. Our cloud-based solution makes instant sense of tons of log data coming from applications, platforms, and systems.

more info at https://www.loggly.com


![ScreenShot](http://blog.sparklehouse.com/wp-content/uploads/2011/07/cc28b733b8y-logo.jpg.jpg)



Only need the endpoint url.

```js
var test = function(){
    //data to log
    var data = {
        userAgent : "android",
        date : new Date(),
    }
    
    //endpoint provided by loggly
    var conf = {
        url : "http://logs-01.loggly.com/inputs/3d2fd5d1-39a8-4a1d-8e49-5ce65d918092/tag/http/"
    }

    //open the connection
    var l = kona.loggly.open(conf);
    
    //send the log
    l.log(data);
    
    return happy;
};

```

Finaly you can see the logs on your dashboard

![ScreenShot](http://i.imgur.com/wzzUUmhl.png)



# Model View

## Model Validations

Los models son una ayuda para el usuario utilizar una estructura orientada a su negocio, de todas maneras
Kona no obliga a que los models tengan atributos ni que se respete su estructura si el usuario no lo desea, tenemos en cuenta la flexibilidad ya que no todas las aplicaciones tienen modelos estructurados y definidos.

Que gana el Usuario al definir sus Models y atributos:

Apis ya creadas y definidas
Validaciones
BackOffice Autogenerado

## Validaciones

Se definen validacinoes para cada atributo.

### NN (Not Null)

Significa que el valor del atributo no puede ser null. Si lo es una exception de tipo
KonaException es lanzada, con un mensaje por defecto auque puede ser customizable.

### UK (Unique Key)

Significa que no puede haber en ese modelo otra entidad con ese valor para ese atributo

### RegExp (Math Regular Expresion)

Valido solo para atributos de tipo String, la idea es validar su valor contra una expresion regular

### Error Messages

Por mensaje Kona tiene ciertos mensajes de error, que pueden no aplicar si estos se usan para el usuario final en un cliente, por lo que dejamos que se puedan definir mensajes para cada uno de estos errores.

En los mensajes se puede hacer referencia al valor ingresando {value} en el texto, por ejemplo

The email {value} is invalid

entonces para el email email@example.com el error sera

The email email@example.com is invalid

### RegExpresion Examples

Attributes validations, you can use a regexp to valida han attribute, for example


Username regular expression
^[a-z0-9_-]{3,15}$

Password regular expression
((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})

Hex color code regular expression
^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$

E-mail address regular expression
^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@
[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$

Image file extension regular expression
([^\s]+(\.(?i)(jpg|png|gif|bmp))$)

IP Address regular expression
^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.
([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$

Time in 12 Hours format regular expression
(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)

Time in 24 Hours format regular expression
([01]?[0-9]|2[0-3]):[0-5][0-9]

Date regular expression
(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)

HTML tag regular expression
<("[^"]*"|'[^']*'|[^'">])*>

HTML Links regular expression
(?i)<a([^>]+)>(.+?)</a>
\s*(?i)href\s*=\s*(\"([^"]*\")|'[^']*'|([^'">\s]+));


#Client SIDE

#Android SDK

## Download

https://github.com/konacloud/sdk/releases/latest

## Install

Just add the project as a library

## Dependencies

No dependencies for restClient

If you use buckets add this apache http mime
http://repo1.maven.org/maven2/org/apache/httpcomponents/httpmime/4.2.1/httpmime-4.2.1.jar

for converting Pojos into Strings we recomend to use Gson https://code.google.com/p/google-gson/

## Configuration

AndroidManigest.xml

```js
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

## Uses

### HTTP GET

```js
	KonaRequest request = new KonaRequest() {
	{
		this.url = "http://app.konacloud.io/...";
		this.method = HTTPMethod.GET;
		this.accessToken = "5b7...";
	}
	
	@Override
	public void onSuccess(String jsonObject) {
		//do something on success
	}
	
	@Override
	public void onFailure(KonaResponse res) {
		//do something on failure
		Toast.makeText(getActivity(), res.getMsg(), Toast.LENGTH_LONG).show();
	}
	};
	request.make();
```

### HTTP POST

```js
	final JSONObject json = new JSONObject();
	json.put("name", "kona");
	
	KonaRequest request = new KonaRequest() {
	{
		this.url = "http://app.konacloud.io/..";
		this.method = HTTPMethod.POST;
		this.data = json.toString();
		this.accessToken = "5b7fb5bd..";
	}
	
	@Override
	public void onSuccess(String jsonObject) {
		
	}
	
	@Override
	public void onFailure(KonaResponse res) {
	
	}
	};
	request.make();
```

### HTTP PUT

```js
	final JSONObject json = new JSONObject();
	json.put("name", "kona");
	
	KonaRequest request = new KonaRequest() {
	{
		this.url = "http://app.konacloud.io/..";
		this.method = HTTPMethod.PUT;
		this.data = json.toString();
		this.accessToken = "5b7fb...";
	}
	
	@Override
	public void onSuccess(String jsonObject) {
		
	}
	
	@Override
	public void onFailure(KonaResponse res) {
	
	}
	};
	request.make();
```

#### HTTP Delete

```js
	KonaRequest request = new KonaRequest() {
	{
		this.url = "http://app.konacloud.io/..";
		this.method = HTTPMethod.DELETE;
		this.accessToken = "5b7fb5bd..";
	}
	
	@Override
	public void onSuccess(String jsonObject) {
		
	}
	
	@Override
	public void onFailure(KonaResponse res) {
	
	}
	};
	request.make();
```



### GSON Integration

Download https://code.google.com/p/google-gson/downloads/list

#### Instanciate the Gson object
```js
Gson gson = new Gson();
```

#### ToString();

```js
MyPojo obj = new MyPojo();
String json = gson.toJson(obj);
```

#### POST Example with Gson

```js
	Gson gson = new Gson();
	
	MyPojo obj = new MyPojo();
	obj.setName("Kona");
	
	final String json = gson.toJson(obj);
	
	KonaRequest request = new KonaRequest() {
	{
		this.url = "http://app.konacloud.io/..";
		this.method = HTTPMethod.POST;
		this.data = json;
		this.accessToken = "5b7fb5bd..";
	}
	
	@Override
	public void onSuccess(String jsonObject) {
		
	}
	
	@Override
	public void onFailure(KonaResponse res) {
	
	}
	};
	request.make();
```

### Buckets

#### Post a FILE

```js
	KonaCallBack callback = new KonaCallBack() {

					@Override
					public void receive(String url) {
						Log.v(this.getClass().toString(), "url: " + url);
					}
				};
				KonaBucket
						.getInstance()
						.uploadImage(
								"http://bucket.konacloud.io/external/api/bucket/taio/hello/b1",
								byte_arr, callback);
```								
#### Example

Take a photo with the camera and send to a KONA backet storage.

```js

	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			// mImageView.setImageBitmap(imageBitmap);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			imageBitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream);
			byte[] byte_arr = stream.toByteArray();

			try {

				KonaCallBack callback = new KonaCallBack() {

					@Override
					public void receive(String url) {
						Log.v(this.getClass().toString(), "url: " + url);
					}
				};
				KonaBucket
						.getInstance()
						.uploadImage(
								"http://bucket.konacloud.io/external/api/bucket/taio/hello/b1",
								byte_arr, callback);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
```

#### Load a File in ImageView

```js
	KonaBucket.getInstance().loadImage("http://host",imageView);
```


### Example Cooker App

<iframe width="420" height="315" src="//www.youtube.com/embed/6Whhe71fyus" frameborder="0" allowfullscreen></iframe>

Its open :) Download and start playing with the SDK

- Post File to a bucket using the KONA Android SDK

- Post a model

- Get model and images from buckets

video https://www.youtube.com/watch?v=6Whhe71fyus

code https://github.com/konacloud/samples/tree/master/android-cookerapp


![ScreenShot](http://cdn.makeagif.com/media/7-03-2014/yMnWio.gif)


# JS (Jquery)

```js
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
```

Below are examples of post and gets

## POST

For this model example

![ScreenShot](http://i.imgur.com/SRcAHrul.png)


```js
var obj = {
  name: "myName",
  email: "myEmail@company.com"
}

$.ajax
    ({
        type: "POST",
        url: 'http://app.konacloud.io/user/app/modelId',
        dataType: 'json',
        data: JSON.stringify(obj),
        success: function (data) {
          console.log(data);
        }
})
```

## GET

```js
$.getJSON( "http://app.konacloud.io/user/app/modelId", function( data ) {
    if (!data.success){
      console.log("some error happend " + data.msg);
    }else{
        console.log(data.data);
    }
});
```

### Simple working example

```html
<html>
<head>
<title>KONA jQuery Hello World</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
</head>
<body>
<script type="text/javascript">

var URL = "http://app.konacloud.io/api/taio/samples/mr_User"
var obj = {
  name: "myName",
  email: "myEmail@company.com"
}
$.ajax
    ({
        type: "POST",
        url: URL,
        dataType: 'json',
        data: JSON.stringify(obj),
        success: function (data) {
          console.log(data);
        }
})
$.getJSON( URL, function( data ) {
    if (!data.success){
      console.log("some error happend " + data.msg);
    }else{
        console.log(data.data);
    }
});

</script>
This is Hello World by KONA Cloud Jquery Example - Just press F5 to insert and get
View the console for results
</body>
</html>

```

## X-AUTH-TOKEN

```js
$.ajax
    ({
        type: "POST",
         beforeSend: function (request)
         {
                request.setRequestHeader("X-AUTH-TOKEN", "e0899540-f8f4-40e7-a1f8-d18f4bf521e4");
         }
        url: URL,
        dataType: 'json',
        data: JSON.stringify(obj),
        success: function (data) {
          console.log(data);
        }
})
```

## File Upload to ha KONA Bucket Storage

We recommend to use this Jquery plugin

https://github.com/blueimp/jQuery-File-Upload

![ScreenShot](http://s17.postimg.org/ud60ssai5/KONA_JQuery_file_upload.gif)

### Simple usage

```html
<input id="fileupload" type="file" name="files[]" multiple>
```
```js
$('#fileupload').fileupload({
        url: "http://bucket.konacloud.io/external/api/bucket/taio/samples/b1",
        dataType: 'json',
        done: function (e, data) {
            console.log(data.result.data[0].url);
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
```

### Complete example

```html
<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery File Upload Demo - Basic version</title>
<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support and progress bar for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/jquery.fileupload.css">
</head>
<body>

<div class="container">
    <h1>KONA jQuery File Upload</h1>

    <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Select files...</span>
        <input id="fileupload" type="file" name="files[]" multiple>
    </span>
    <br>
    <br>
    <div id="progress" class="progress">
        <div class="progress-bar progress-bar-success"></div>
    </div>
    <div id="files" class="files"></div>
    <br>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script src="js/vendor/jquery.ui.widget.js"></script>

<script src="js/jquery.iframe-transport.js"></script>

<script src="js/jquery.fileupload.js"></script>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script>
/*jslint unparam: true */
/*global window, $ */
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:

    $('#fileupload').fileupload({
        url: "http://bucket.konacloud.io/external/api/bucket/taio/samples/b1",
        dataType: 'json',
        done: function (e, data) {
            console.log("hola");
            console.log(data.result.data[0].url);
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>
</body>
</html>

```

# PhoneGap

## POST File to a bucket with PhoneGap

```js
function uploadPhoto(imageURI) {
            var options = new FileUploadOptions();
            options.fileKey="file";
            options.fileName=imageURI.substr(imageURI.lastIndexOf('/')+1);
            options.mimeType="image/jpeg";

          
            var ft = new FileTransfer();
            ft.upload(imageURI, "http://app.konacloud.io/external/api/user/app/bucketName", win, fail, options);
        }

        function win(r) {
            
            var str = r.response;
            var obj = JSON.parse(str);
            var url = obj.data[0].url;
            
            alert("the file is in " + url);
        }
```
# Xamarin (Android & IOS)

Getting a list

```js
public static IEnumerable<Task> GetTasks ()
		{

			var request = WebRequest.Create(url);
			request.ContentType = "application/json";
			request.Method = "GET";
			request.Headers.Add ("X-AUTH-TOKEN", auth_token);

			using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
			{
				if (response.StatusCode != HttpStatusCode.OK) {
					Console.Out.WriteLine ("Error fetching data. Server returned status code: {0}", response.StatusCode);
				}

				var stream = response.GetResponseStream ();
				DataContractJsonSerializer ser = new DataContractJsonSerializer(typeof(KCRequest<List<Task>>));
				KCRequest<List<Task>> tasks = (KCRequest<List<Task>>)ser.ReadObject(stream);
				return tasks.data;
			}
		}

```


# Monitoring

<iframe width="560" height="315" src="//www.youtube.com/embed/B8Ebh38R4BI" frameborder="0" allowfullscreen></iframe>

# Anexo


Kona first Video 30 minutes duration
http://vimeo.com/82645251


SDK Reference

On top of the execution runtine, we created a powerful KonaCloud.IO SDK that helps developers code faster and with less errors with ready-to-use libraries that provide a lot of functionality right outside the box, such as push notifcations for iOS and Android, email sending, sms sending, calling external web services and connecting to SQL-based databases with ease.

We strive to create very simple and efficient helper functions so you don't have to reinvent the wheel. We are adding more functions every day to the SDK.

JavaScript

We love JavaScript and all of KonaCloud.IO is based on JavaScript libraries and functions.

Here are useful links to JavaScript reference and user guides that will help you be more efficient and productive.

A re-introduction to JavaScript (JS Tutorial)
https://developer.mozilla.org/en-US/docs/Web/JavaScript/A_re-introduction_to_JavaScript

Mozilla's JavaScript Guide
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide

Mozilla's JavaScript Reference
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference



# Getting Started Demos

3 minutes tutorial for the first backend
