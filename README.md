KONA CLOUD
===========

KONA CLOUD is a powerful platform as a service built on top of solid and agile technologies. Is scalable as it sits on top of elastic cloud providers.

http://konacloud.io
http://developer.konacloud.io

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


# kona.js User Guide

KONA is a Backend-as-a-Service platform which allows you to create data models, develop custom APIs and much more. KONA APIs are coded in standard JavaScript 1.5. KONA runs on top of the super fast Nashorn JavaScript engine from Oracle on top of the Java 8 VM.

We have created a beautiful kona.js sdk in order to provide the developers easy to use and convenient functions that make your life easier while coding your business logic within the APIs.

This SDK has functions for sending emails, sending sms, calling external web services, connnecting to SQL-based databases and more.

We strive to create very simple and efficient helper functions so you don't have to reinvent the wheel. We are adding more functions every day to the kona.js package.

kona.js samples
===============

## BasicServices

### Object

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

### Contratos y assertions

For unit-test the assertions are very useful, and the contracts gral methods

"What is a contract

A contract is an assertion in the code. 
If a contract is violated (hence if the condition asserted is false) we consider that there must be a bug."

Ref: http://codebetter.com/patricksmacchia/2013/12/18/code-contracts-is-the-next-coding-practice-you-should-learn-and-use/#

With kona try out the developer by good software development practices.

#### Assert (Aserciones)


Equals:
```
if (somecondition)
	assert(myvar,null);
	
if (somecondition)
	assertEquals(myvar,null);	
```

Not Equals:
```
if (somecondition)
	assertNotEquals(myvar,null);
```

#### Contract (Contratos)

```
function some(str){
	Contract(str!="","the str value cannot be null);
	Contract(str!="");
}
```

The contract and the assertions stops de excecutions and leave a log, para el contrato se pueden configurar notificaciones automaticas ya que si se viola un contrato estamos ante un bug en nuestra aplicacion.

### Call method

To call this method simply persisted in another 'file' or api, you must do the following from the code

```
call.nombreDelMetodo(parametro);
```

What methods are virtual, making performance by kona inyecctar the script is going to run a js object with certain
methods thus emulate the behavior of the call, such

```
var call = new KonaCaller();
function KonaCaller () {
    this.method1 = function() {
        return 'Hi from method';
    };
}
```

### Java API

Simply ImportPackage with the package name eg java.util or java.text and from there use
the api directly as estuvise compile a java environment.

### Mails

Sending mails is performed as follows
You can configure a service itself or use the default mails having kona.

#### Fast Way (One line email)
```
kona.email().send('mimail@miserver.com','Asunto','Contenido');

```

#### Settings and Customize

```
    var m = kona.email();    
    m.put("smtp","smtp.gmail.com");
    m.put("user","cloudkona@gmail.com");
    m.put("port","587");
    m.put("pass","mypass");
    
    m.send("some@gmail.com","kona hi!","hola mail");
```


Advanced

> importPackage(org.kona.js.mail); 


```

var email = new Email();
email.setFromAddress("taio", "taio@linux-mail.com");
email.setSubject("hey");
email.addRecipient("other", "alguien@kona.org", RecipientType.TO);
email.addRecipient("diego", "alguien2@kona.org", RecipientType.BCC);
email.setText("Kona");
email.setTextHTML("<img src='..'><b>some text</b><img src='..'>");

email.addEmbeddedImage("wink1", imageByteArray, "image/png");
email.addEmbeddedImage("wink2", imageDatesource);
email.addAttachment("invitation", pdfByteArray, "application/pdf");
email.addAttachment("dresscode", odfDatasource);

m.send(email);

```
Interface

https://github.com/aspekt/kona/blob/master/dashboard/src/org/kona/js/EmailService.java


## Api Services

Api to communicate by rest to other services on the web.

Supported Protocols and Architectures
Rest (json)
Odata

### Fast Way

```
function f(){
var obj = kona.obj();
var api = kona.api().get();
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

Propiedades


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

var api = kona.api().post(data);
var r = api.call("http://postexample.com");

```

### Metodo Put

```
var data = kona.obj();
data.put("put",12);

var api = kona.api().put(data);
var r = api.call("http://putexample.com");

```

## Model Services

The objective of this service is to provide an api to handle entities defined with functionality 'Model',
definimo eg if an entity 'person' to perform operariones with her and the others.

### Insert

having previously defined a model with id 'person' and the attributes listed below

```
person = create('person');
person.name = 'Me';
person.age = 24;
person.email = 'me@konacloud.org'

var m = kona.model();
m.insert(person);

```

### Upadte

```
person.name = 'Other Me';
var m = kona.model("person");
m.save(obj);

```
### Delete

#### Delete By Json

The delete api need a json to find the element and then remove it.

```
var m = kona.model("person");
m.delete(obj);

```
#### Delete By ID
```
model.deleteById("53486986a09e2c778e82bc37");

```

### Query List

```
var m = kona.model();
var list = m.query('person',"{name:'me'}"); //obtenemos todas las personas con nombre igual a 'me'

for (KonaDO p:list){
    String name = p.get('name');
}
```

### Query By Id

```
var m = kona.model("person");
m.queryById("id1");

```

### Query Single

```
var m = kona.model();
var one = m.query('person',"{name:'me'}",true); //obtenemos 1 persona con nombre igual a 'me'

var name = one.get('name'); //playing with the obj
```
## Map Service

The idea is to have an api to work with maps and locations

One line code Geocode api


```
  kona.map().geocode("uruguay montevideo charrua 1880");
  
```
result

```
{ "latitude" : -34.9062205 ,
  "longitude" : -56.174852}
  
```

One line code Geocode reverse api

```
kona.map().reverse(-34.9062205,-56.174852);
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
    return kona.img().qr("some text");
  
```

Demo video:

http://www.youtube.com/watch?v=0LZ8L3sntfI&feature=youtu.be


## Net Util Service

The idea is to have an api to manage net stuff

One line code

```
    return kona.net().search("konacloud.org");
  
```

###Api List

```
/*
 * Search domain
 */

public DBObject search(String domain) throws Exception;

/*
 * Ping a Host
 */

public DBObject ping(String ip) throws Exception;

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
  kona.sms('12312312','my sms text');
  
  Important the number must have the country code, because is global :)
  
  for example for Uruguay 59899123123 for the number 099 123 123
  
```

Demo Video

http://www.youtube.com/watch?v=4TACroSs-rA&feature=youtu.be

## Social Services

The idea is to have an api to access social networking services

### Basic

Services in connection with an id, eg 'social1' is created

```
  var s = kona.social('social1');
  s.post("Hi from kona"); //post msg
```

### Facebook

On facebook you can make other inquiries in addition to basic
We can use FQL to query for information with api query


Demo Video

http://www.youtube.com/watch?v=_tJVz3I9uts&feature=youtu.be


```
  var s = kona.social('social1');
  
  s.query("select uuid2 from friends"); 
  
```

ref: https://developers.facebook.com/docs/reference/fql/

### Twitter

Demo Video

http://www.youtube.com/watch?v=QMZbURzh1eE

### Linkedin


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
    
    kona.email().send("santiago@konacloud.org","The PDF","Here is the pdf " + url)
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
    kona.email().send("me@konacloud.org","Today backup","the backup is here " + url);
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

# Schedule

```
# * * * * *  api to execute
# â”¬ â”¬ â”¬ â”¬ â”¬
# â”‚ â”‚ â”‚ â”‚ â”‚
# â”‚ â”‚ â”‚ â”‚ â”‚
# â”‚ â”‚ â”‚ â”‚ â””â”€â”€â”€â”€â”€ day of week (0 - 7) (0 to 6 are Sunday to Saturday, or use names; 7 is Sunday, the same as 0)
# â”‚ â”‚ â”‚ â””â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ month (1 - 12)
# â”‚ â”‚ â””â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ day of month (1 - 31)
# â”‚ â””â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ hour (0 - 23)
# â””â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€â”€ min (0 - 59)
```

http://en.wikipedia.org/wiki/Cron#Examples


kona.js
