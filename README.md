KonaCloud.io
============

Welcome to KonaCloud.io

KonaCloud.io is for developers by developers. A platform to run all your back-ends, APIs and bussiness logic. Runs JavaScript on the server-side so you don't have to learn a new language.

KONA is a Backend-as-a-Service platform which allows you to create data models, code custom APIs, create and use object storage buckets and reporting for your data.

KONA custom APIs are coded in the latest JavaScript. These are then executed by the all-new, super-fast  and powerful Nashorn JavaScript engine from Oracle's JVM 8.

Website: http://konacloud.io
Developer Portal: http://developer.konacloud.io

## Videos

30 minutes duration http://vimeo.com/82645251

Kona-Facebook Demo Video

http://www.youtube.com/watch?v=_tJVz3I9uts&feature=youtu.be

Kona-Twitter Demo Video

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


Code Samples
===============

## BasicServices

### Object JS Mode

You can create and use JS Object and them pass they to the kona methods

One Wey

```

var obj = {
	"name": "Ear",
	"age":19
};

var str = JSON.stringify(obj); //this is a String.

```


### HOW to Use Object in Kona


You can use JS Object or Java Object, for example

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

##Basic Uses

####How to Log

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

### TEST (Traducir)

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

### Object (Simple Java Object KonaDO)

You can create as ANY WAY object is created in java, for communication between the script and utlize kona.js
usually an object of type KonaDO compliant json format with other properties.

The following are valid

```
var obj = new KonaDO(); //o
var obj = kona.obj();
```
### List

```
var obj = new ArrayList<KonaDO>(); //o
var obj = kona.list();
```


### Mails

Sending mails is performed as follows
You can configure a service itself or use the default mails having kona.



### Request Structure

Cada request a un resource viene con la siguiente estructura, por ejemplo


req.header
req.params
req.body

##### Ejemplo de obtencion de un headers

```
var get = function(req) {
    return req.headers.get("asd");
};
```

##### Obtener parametro URL

```
var get = function(req) {
    return req.params.get("asd");
};
```

### Libraries

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


### Helpers

#### Fast Way (One line email)

```
kona.email.send('email@server.com','Subject','Content');

```

#### Settings and Customize

```
    var m = kona.email;
    m.put("smtp","smtp.gmail.com");
    m.put("user","cloudkona@gmail.com");
    m.put("port","587");
    m.put("pass","mypass");

    m.send("some@gmail.com","kona hi!","hola mail");
```


Advanced

```
load("nashorn:mozilla_compat.js");
importPackage(org.kona.js.mail);
```


Extended Sample
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

#### Getting Params from URL

for example http://konacloud/console/api/taio/app1/mr_person?param1=2

we need the value of param1, its simple

```
var value = request.get("param1");
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

## Model Services

The objective of this service is to provide an api to handle entities defined with functionality 'Model',
definimo eg if an entity 'person' to perform operariones with her and the others.

### Insert


```


var m = kona.model.open("person");
m.insert(person);

```

### Upadte

```
var m = kona.model.open("person");
m.save(person);

```
### Delete


#### Delete By ID
```
var m = kona.model.open("person");
m.deleteById("53486986a09e2c778e82bc37");

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

### Query Single

```
obj = kona.obj();
obj.put("field1","some");
var m = kona.model.open("person").query(obj).get(0);

```

## Push Notifications

### Andorid Push Notification

#### Server Side

Primero agregar el Android APiKey en el Settings de la aplicacion

![ScreenShot](http://i.imgur.com/RQUB62J.png)


Luego desde el codigo hacemos lo siguiente

```
var test = function(){
    
    var obj = kona.obj("message","Hi from kona");
    obj.put("time",(new Date()).getTime());
    kona.notifications.push("fc85574de6588e5926d286743e1b99f5aa8fd5fa1f600612a4b01376695fcad6",obj.toString());
    return kona.obj(true);
};

```

#### Client Side

Ejemplo del codigo para el cliente se puede encontrar en 

https://github.com/konacloud/samples/tree/master/android%20push%20notification%20example


### IOS Push Notifications


## Map Service

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


## Img Util Service


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

## SMS Service

The idea is to have an api to send sms

One line code


```
  kona.sms.send('12312312','my sms text');

  Important the number must have the country code, because is global :)

  for example for Uruguay 59899123123 for the number 099 123 123

```

Demo Video

http://www.youtube.com/watch?v=4TACroSs-rA&feature=youtu.be


### Basic


## File Service

The File Service is to create files, pdf, txt, doc or ms ms docx, html formats are supported.

The idea is to write rich text (html) and convert them to other formats, persisting for the last file in a special bucket (default)



### Example: Making a PDF and Sendit by email


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

## DB Service

Api for handling databases

First Connection in "Services" indicating user, pass, ip, then from the connection id used the service is created.

To connect to a db just type the following and have access to servico

```
    db = kona.db("mydb1"); //db es el acceso de DBServices
```

### querys

```
	result = db.query("select * from my_table");
```

### BackUp DB

```
	url = db.backup();
```

### Result

The result is a list of json in kona always jsons we deal with, or to facilitate json arrays for the programmer.


```
function get(){
    db = kona.db("mydb1");
    list = db.query("select * from my_table"); //list is a json list

    //example
    first = list.get(0);
    name = first.get("name");
}
```


## Make a backup every 24 hours in 3 lineas of code

How to make in kona a sql backup and run it every 24 hours
```
function run(){
    db = kona.db("mydb1");
    url = db.backup();
    kona.email.send("me@konacloud.org","Today backup","the backup is here " + url);
}
```

Then go to schedule and select to run every 24 hours this method

#Forms

## Form Controllers

```
importPackage(org.kona.js);

// call this before the form is builded
function onBefore() {

    var form = kona.obj();
    form.put("field0",1);

    list1 = kona.list();
    for (var i=0;i<99;i++){
        list1.add("cliente " + i);
    }
    form.put("field1",list1);

    list2 = kona.list();
    for (var i=0;i<99;i++){
        var obj = kona.obj();
        obj.put("name","some "+i);
        obj.put("last name","last "+i);
        list2.add(obj);
    }

    form.put("field3",list2);

	return form;
}

// call this on the form commit (if there is any) with the data posted
function onCommit(data) {
	val = data.get("field1");
	log("getting "+val);
    if (test_error)
        kona.obj("some error msg");
}
```


## Form Controller and Model integration

Example, set up a form with a table, and insert all the values from a model or q query)

Just do it!

// call this before the form is builded
function onBefore() {

    var form = kona.obj();
    form.put("text1","Valor por defecto");

    var model = kona.model('person');
    form.put("table1",model.all());

    form.put("table1.visibleColumns", "name,email");
    form.put("table1.columnHeaders", "Nombre,Correo");

	return form;
}

###Properties

form.put("table1.visibleColumns", "name,email");

only this fields are visible in the table

form.put("table1.columnHeaders", "Nombre,Correo");

change the captions of the table

Video


#Buckets

A bucket is a place to host static content and serve them fast.
Each bucket created in KONA has a unique url you can POST and GET files from. Any file you POST will return a JSON object with the success code and GET URL you an use to retrieve it.

![ScreenShot](http://i.imgur.com/OVFxuIr.png)

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



# UI Views

## Model View

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
