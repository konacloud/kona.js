KonaCloud.io
============

Welcome to KonaCloud.io

KonaCloud.io is for developers by developers. A platform to run all your back-ends, APIs and bussiness logic. Runs JavaScript on the server-side so you don't have to learn a new language.

KONA is a Backend-as-a-Service platform which allows you to create data models, code custom APIs, create and use object storage buckets and reporting for your data.

KONA custom APIs are coded in the latest JavaScript. These are then executed by the all-new, super-fast  and powerful Nashorn JavaScript engine from Oracle's JVM 8.

Website: http://konacloud.io
Developer Portal: http://developer.konacloud.io

# Videos

Hello work 40 sec Video 

https://www.youtube.com/watch?v=vTJpU-TGKMU

Kona first Video 30 minutes duration

http://vimeo.com/82645251

Kona-Facebook Demo Video (Deprecated)
http://www.youtube.com/watch?v=_tJVz3I9uts&feature=youtu.be
http://www.youtube.com/watch?v=QMZbURzh1eE


Kona-SMS Video
http://www.youtube.com/watch?v=YnOMuFf7q0E&feature=youtu.be

Kona GeoCode Example
http://www.youtube.com/watch?v=wAtOfGyngiY&feature=youtu.be


# KonaCloud.IO SDK Reference

On top of the execution runtine, we created a powerful KonaCloud.IO SDK that helps developers code faster and with less errors with ready-to-use libraries that provide a lot of functionality right outside the box, such as push notifcations for iOS and Android, email sending, sms sending, calling external web services and connecting to SQL-based databases with ease.

We strive to create very simple and efficient helper functions so you don't have to reinvent the wheel. We are adding more functions every day to the SDK.

# JavaScript

We love JavaScript and all of KonaCloud.IO is based on JavaScript libraries and functions.

Here are useful links to JavaScript reference and user guides that will help you be more efficient and productive.

A re-introduction to JavaScript (JS Tutorial)
https://developer.mozilla.org/en-US/docs/Web/JavaScript/A_re-introduction_to_JavaScript

Mozilla's JavaScript Guide
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide

Mozilla's JavaScript Reference
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference


#Kona APP Architecture

![ScreenShot](http://i.imgur.com/bS6AJu3.png)


Templates and Examples
===============

## Model Resources

```
/*
 * @autor {userId}
 */
var model = kona.model.open('modelId');

/*
 * @param req is the http request, req.params.get("")
 */
var get = function(req) {
	return model.all();
};

/*
 * @param req.body is a instance of {modelId}
 */
var post = function(req) {
	model.insert(req.body);
	return kona.obj(true);
};

/*
 * @param req.body is a instance of {modelId}
 */
var put = function(req) {
	model.save(req.body);
	return kona.obj(true);
};

/*
 * @param req.params.get("id") is the id of the instance to delete
 */
var del = function(req) {
	var id = req.params.get("id");
	model.deleteById(id);
	return kona.obj(true);
};

var test = function() {
	return get();
};
```

## Custom Resource

```
var model = kona.model.open('{modelId}');

var get = function(req) {
	
	var id = req.params.get("id);
	return model.queryById(id);
};

```
## JOB

```

var run = function(){
	log("hi);
}
```

## Library

```

var abs = function(num){
	if (num>0)
		return num;
	else
		return num*(-1);
}
```


Code Samples
===============

# BasicServices

## Object JS Mode

You can create and use JS Object and them pass they to the kona methods


```

var obj = {
	"name": "Ear",
	"age":19
};

var str = JSON.stringify(obj); //this is a String.

```


## HOW to Use Object in Kona


Diferents ways to create objects, you can use JS Object or Java Object, for example

```

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

#Basic Uses

##How to Log

Yo can log all kond of stuff, for example
```
log("This is a common log");
log.err("Hi this is han error");
log.info("This is han info log");
log.war("This is han warining log");

```

If you use the "Debug" Button you can see the logs for the test method, and web yous clientes acces to the resources then the logs area stored and you can see them in the Log View

and the result is something like this

```
//Use log('text') to log
This is a common log
[ERROR] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)Hi this is han error
[INFO] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)This is han info log
[WAR] Wed Jun 18 2014 01:22:21 GMT-0300 (UYT)This is han warining log
```

## TEST (Traducir)

Cada api debe implementar el metodo test para poder ser utiliada en la test suite.
El metodo test es un test unitario del recurso o metodo que se esta programando.

Se considera que un test es exitoso si no contiene error al terminar su ejecucion, alguna funciones de utilidad son:

```
	assert(p, q)
```

```
	notAssert(p, q)
```

Ademas se pueden hacer cosas como

```
	if (somethingStrange()){
		kona.error("This is bad");
	}
```

Consideramos una buena practica que todos los metodos tengan su test, auque deben tener cuidado de no insertar datos en cada test (queda a criterio del desarrollador)

## Object (Simple Java Object KonaDO)

You can create as ANY WAY object is created in java, for communication between the script and utlize kona.js
usually an object of type KonaDO compliant json format with other properties.

The following are valid

```
var obj = kona.obj();
```
## List

```
var obj = new ArrayList<KonaDO>(); //o
var obj = kona.list();
```

## Request Structure

Cada request a un resource viene con la siguiente estructura, por ejemplo

```
req.header.get("some_header")
req.params.get("some_param")
req.body.get("field");
```

### Ejemplo de obtencion de un headers

```
var get = function(req) {
    return req.headers.get("asd");
};
```

### Obtener parametro URL

```
var get = function(req) {
    return req.params.get("asd");
};
```

## Libraries

Para definir librerias y utlizar en cualquier resource/job o otra libreria

Guardar el codigo como Library

Por ejemplo:

```
var util = function(){
    return (new Date()).getTime();
};

var test = function(){
    return util();
}
```

y lo Guardamos por ejemplo con el nombre timeUtil

Luego desde cualquier funcion simplemente lo incluimos al comienzo del script (es importante que sea al principio)

```
include("timeUtil");

var test = function(){
    return timeUtil.util();
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

```
var mc = kona.model.open("Person");
```

The rest of you will be varible mc (Model Controller for Person).

### Insert a New Object


```
var person = {
	name : "Bart",
	lastName : "Simpson",
	email : "bart@konacloud"
}

mc.insert(person);
```

With this always insert a new entity and creates ha new id for the object, if we get this object its looks like
```
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

```
var person = {
	name : "Bartolomeo",
	lastName : "Simpson",
	email : "bart@konacloud.io",
	_id : "51243na314aae34"
}

mc.save(person);
```

#### find And Update

If We want to update all the Persons who has the name eq "Bar"

```
var barto = {
	name: "Bart"
}
var person {
	lastName: "Simpson";
}
mc.findAndUpdate(barto, person);
```

Then all the elements with name Bart wil hace the lastName eq Simpson

### Delete a Object

### Delete By Id
To delete an object is necessary to know its id.

```
mc.deleteById("51243na314aae34");
```

### find And Delete
Same as before, if we want to delete all the Persons who has the name eq "Bar"

```
var barto = {
	name: "Bart"
}
mc.findAndDelete(barto);
```

### Query List

```
var m = kona.model.open("person");
var list = m.query('person',"{name:'me'}"); //obtenemos todas las personas con nombre igual a 'me'

```

### Query By Id

```
var m = kona.model.open("person");
m.queryById("id1");

```

### Queries (New)

We will build the query with all the thing that we like, params, regexp, sorters, etc, and finaly we get the result in ha list or in a single element

firts of all web get the query object, here we can pass data to find or not

In this example we will do it in the large way, and finaly we show some helpers

### Build Query

```
var q = mc.buildQuery();
```

### Get All

In SQL to get all the elements we do

select * from Person, and in Kona

```
var list = q.list();
```

### Pagination

in mobile applications is very common to use pagination when you scrols on list views (android) for example,
it is very important to consider the paging when working with lots of data.
KONA default leaves a request ModelService.MAX_QUERY bring over data elements are at most 100 non premium accounts,
however it would be best not to have to send more request but the result page always.

The page is defined as a limit and offset, so the pages may have dynamic size.

```
q = q.limit(10).offset(5);
var list = q.list();

or justo

var list = q.limit(10).offset(5).list();
```
we get at max 10 elements but we skip the firs 5 elements

### Complex Queries

Select * from Person where name = "Bart";

```
var find = {
	name : "Bart"
}
var list = q.find(find).list();
```

If we know that our query will result in a single result allway, search by id or some atribute that is unique (see Contrasits).

We can do this in any query.

```
var find = {
	name : "Bart"
}
var person = q.find(find).single();
```

Of course the object find can have many atributes as we want.

Query Examples

### Compare atributes in Queries

SELECT * FROM Person WHERE name = "Bart" OR lastName = "Simpson"

```
find = { $or: [{ name: "Bart"},{lastName:"Simpson"}] };
var list = q.find(find).list();
```

If the person has an age, and we have to to the all the persons with age > 25

SELECT * FROM Person WHERE age > 25;

```
find = { age: { $gt: 25 } };
var list = q.find(find).list();
```

age > 25 --> $gt: 25
age < 25 --> $lt: 25

SELECT * FROM Person WHERE age > 25 and age<=50;

find = { age: { $gt: 25, $lte: 50 }

### Like in Queries

We can use RegExp or if the only thing tha we need is the like we can do this.

SELECT * FROM Person WHERE name like "%Bart%";

```
var find = {
	name : "/ar/"
}
var person = q.find(find).list();
```

### Sorting

SELECT * FROM Person order by age ASC

```
var find = {
	name : "/ar/"
}

var sorter = {
	age : 1
}
var person = q.find(find).sort(sorter).list();
```

SELECT * FROM Person order by age DESC

```
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

```
var find = {
	name : "Bart"
}
var size = q.find(find).count();
```

### Get Only Some params

This is very important if we have 10 atributes but we only need 2 to show up.


```
var find = {
	name : "Bart"
}

var keys = {
	name: 1
}
var names = q.find(find).keys(keys).list();
```

This will list only the names of the Persons


### Short way to Start


```

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

```
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

```
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

```
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

```
[{ "actor_id" : 1 ,
  "first_name" : "PENELOPE" ,
  "last_name" : "GUINESS" ,
  "last_update" : { "$date" : "2006-02-15T06:34:33.000Z"}}]
```

## PostGres

We reomend the use of cloud postgress prividers

https://www.heroku.com/postgres

http://www.elephantsql.com/


```
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

```
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

```
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

```
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

```
load("nashorn:mozilla_compat.js");
importPackage(org.kona.js.mail);
```

```

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

```
kona.email.send('email@server.com','Subject','Content');

```

## SMS

```
  kona.sms.send('12312312','my sms text');

  Important the number must have the country code, because is global :)

  for example for Uruguay 59899123123 for the number 099 123 123

```

Demo Video
http://www.youtube.com/watch?v=4TACroSs-rA&feature=youtu.be


# SERVICES


## Api Services

Api to communicate by rest to other services on the web.

Supported Protocols and Architectures
REST (json)

### Fast Way

```
function f(){
	var obj = kona.obj();
	var api = kona.api.get();
	var r = api.call("http://api.openweathermap.org/data/2.1/weather/city/3441575?units=metric");
	obj.put('temperatura',r.get("main").get("temp"));
	return obj;
}
```

La respuesta es
```
{
temperatura: 19.25
}
```

### Metodo Get

```
var api = kona.api().get();
}
```

### Metodo Post

```
var data = kona.obj();
data.put("hola",123);

var api = kona.api.post(data);
var r = api.call("http://postexample.com");

```

### Metodo Put

```
var data = kona.obj();
data.put("put",12);

var api = kona.api.put(data);
var r = api.call("http://putexample.com");

```

# Push Notifications

## Andorid Push Notification

### Server Side

First add the Android APiKey in the app lication settings

Code Example

```
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

```
var test = function(){
    
    var payload = {"aps":{"alert":{"body":"push desde KONA","title":"el titulo","action":"Si o no?"},"sound":"default","badge":1},"micampo":"valor"};

	kona.notifications.iosPush("fc85574de6588e5926d286743e1b99f5aa8fd5fa1f600612a4b01376695fcad6",JSON.stringify(payload));
    return kona.obj(true);
};

```

# GeoCoding

The idea is to have an api to work with maps and locations

One line code Geocode api


```
  kona.map.geocode("uruguay montevideo charrua 1880");

```
result

```
{ "latitude" : -34.9062205 ,
  "longitude" : -56.174852}

```

One line code Geocode reverse api

```
kona.map.reverse(-34.9062205,-56.174852);
```

result

```
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

http://www.youtube.com/watch?v=wAtOfGyngiY&feature=youtu.be


# Utils


### QR Generation

```
    return kona.img.qr("some text");

```

Demo video:

http://www.youtube.com/watch?v=0LZ8L3sntfI&feature=youtu.be


## Net Util Service

The idea is to have an api to manage net stuff

One line code

```
    return kona.net.search("konacloud.org");

```

### Misc Utils

```
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

public DBObject checkPort(String host, int port) throws Exception;

/*
 *  dn2ip
 */
public DBObject dn2ip(String host) throws Exception;

/*
 *  ip2dn
 */

public DBObject ip2dn(String ip) throws Exception;

```



# File Service

The File Service is to create files, pdf, txt, doc or ms ms docx, html formats are supported.

The idea is to write rich text (html) and convert them to other formats, persisting for the last file in a special bucket (default)



##Making a PDF and Sendit by email


```
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

```
var resp = kona.sample.myFunction("hi");
```

add all the methods in the interface and implement them in the ServiceImpl.

Finaly add ha test case for your method in the apropiate package.



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

```
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

## Uses

### HTTP GET

```
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

```
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

```
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

```
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
```
Gson gson = new Gson();
```

#### ToString();

```
MyPojo obj = new MyPojo();
String json = gson.toJson(obj);
```

#### POST Example

```
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

```
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

```

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

```
	KonaBucket.getInstance().loadImage("http://host",imageView);
```

# JS (Jquery)

We recommend to use jquery.

```
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
```

Below are examples of post and gets

## POST

For this model example

![ScreenShot](http://i.imgur.com/SRcAHrul.png)


```
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

```
$.getJSON( "http://app.konacloud.io/user/app/modelId", function( data ) {
    if (!data.success){
      console.log("some error happend " + data.msg);
    }else{
        console.log(data.data);
    }
});
```

### Simple working example

```
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

```
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

See the gif example

http://postimg.org/image/xwryild7v/

### Simple usage

```
<input id="fileupload" type="file" name="files[]" multiple>
```
```
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

```
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


