Getting Started with the KONA Cloud
===================================

“KONA Cloud is designed to make developers happy” - KONA Team

#Introduction

Everybody has great ideas for new apps, all the time.  Us developers know that creating an app usually involves 2 main components:

1. The App (or Front-end) which you download from the App Store
2. The Cloud services (or back-end) the app needs to work and where the data is stored and accessed

The KONA Cloud helps you code, build and run your app cloud services in a robust and scalable infrastructure.

Before KONA, when you needed to create these services, you usually had to choose from a range of technologies; hosting providers, services monitoring tools, remote backup solutions and sysadmin staff to keep all these running and updated.

A common way of doing back-ends involved: a Linux Server, a PHP-based web app, MySQL database - all this running on a VPS or Shared Hosting. In the best cases, under a virtual elastic instance.

KONA took this know-how, and created a development environment and runtime platform that takes away all the hassle of building and running a back-end in the cloud so you can focus on what matters most: your business.

Imagine a platform where you can design your data models (aka database), code your APIs (endpoints which your App will communicate to) in JavaScript, code and run background tasks, store and retrieve huge data files like video, images or sound files with no coding at all. Then, have your cloud services running on a rock-solid always-on elastic cloud infrastructure with performance indicators and monitoring. 

That’s just some of what the KONA Cloud does for you. 

Now you can create your backend in minutes. Run them forever.

#Building your first app

A very common mobile app back-end usually involves these steps:

1. Define what data are you going to store (design your data models)
2. Code your APIs which will be called from your app
3. Create and schedule background jobs (like fetching data from some service overnight)
4. Provision a place where you are going to store those profile pictures, photos and video files people will upload from the app
5. Develop a back-office solution to administer your data
6. Create documentation for your apps so your mobile developers know how to invoke your APIs
7. Run!

In KONA Cloud, all these steps are done within a single web-based environment, and some of them are done automatically for you.

##1. Design your data models

KONA Cloud uses a NoSQL database called MongoDB. It is a document-based database which uses Collections of data instead of the older “Tables” paradigm. Each Model you define is like defining a Table.

![alt text](http://konacloud.io/doc/gettingstarted/models.png "Models")

A model (or Collection) definition consists of a series of fields. You can add, edit and modify fields for any data model at any time. 

Once you create a new model, KONA Cloud will automatically create a CRUD (Create, Retrieve, Update, Delete) API for that specific model which you can already access through the API URL. This requires no coding at all. But you still can modify the generated API to add add any business logic you might want to.

When you are done designing your data models, you will want to code your app-specific APIs.

##2. Code your APIs

KONA Cloud provides a development environment in your browser. It provides a powerful programmer’s editor where you can code your business logic encapsulated in APIs. 

There are 4 types of APIs: 

* Model APIs (auto generated upon creating  your data models)
* Custom APIs (where you create custom APIs for your app)
* Libraries (business logic that is to be reused throughout your app)
* Jobs (scripts that are to be scheduled to run as background jobs) 

![alt text](http://konacloud.io/doc/gettingstarted/code.png "Code")


KONA Cloud allows you to code and run your APIs in pure JavaScript. These scripts run on top of the powerful Java 8 Virtual Machine, which provides a robust and speedy runtime. Your scripts also can access most of the Java Runtime Environment libraries natively. Think: System.out.println(“hello from java”);

For a comprehensive JavaScript reference guide and tutorials, we recommend to read the Mozilla Developer Network JavaScript documentation: 

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide

Each API you code is accessible through a unique URL in the following form:
```
https://app.konacloud.io/api/[username]/[app]/[api]

Example: https://app.konacloud.io/api/diego/Demo/mr_Post
```

By clicking the API URL button, you can see all exposed methods of your current API script.

KONA Cloud also provides HTTP/S connectivity for all APIs. 

##3. Background Jobs

Background Jobs are scripts that run in the background, at scheduled times for specific purposes. These are usually batch jobs that process data at certain times of the day, or update some data.

![alt text](http://konacloud.io/doc/gettingstarted/jobs.png "Background jobs")

These scripts are created in the CODE section like the other APIs. The only requirement is that these scripts need to have a “run” method. The scheduler will execute this method at the specified times in the SCHEDULER section.

You add a background task by first getting the CronTab definition (select minutes, hour, day, month, weekday). Once you get the CronTab definition, you select the API you want to schedule to run and add it to the scheduled tasks section.

![alt text](http://konacloud.io/doc/gettingstarted/crontab.png "Models")

The Logs button allows you to review any logs that your script might be logging in the background.

##4. Storage (Buckets)

KONA Cloud provides a simple solution for your app storage needs. We call this storage buckets. Each bucket is a place where you can upload and retrieve files from using regular HTTP calls like POST and GET. Think of buckets as folders (or directories) where you store files.

Any file can be stored and retrieved from a bucket. A simple POST to a bucket stores the file. A GET to a bucket retrieves a list of files on that bucket. Each file has it’s own unique URL to be retrieved.

![alt text](http://konacloud.io/doc/gettingstarted/storage.png "Storage")

Buckets are a clever and simple way that solves the painful process of storing large amounts of data. You can create any amount of buckets to organize your files. 

##5. Backoffice (or data admin site)

Usually, you need to administer and manipulate your data through a user-friendly graphical user interface beyond your App, like when you need to review registered users, payments or any other kind of transaction or action that requires you to review or manipulate your data.

![alt text](http://konacloud.io/doc/gettingstarted/backoffice_web.png "Backoffice")

KONA Cloud solves this issue by generating a back-office web application on-the-fly for your data models. This without any coding at all. Add a new model and it will appear automatically in your web-based back-office solution for your back-end.

This back-office solution is a bootstrap-based web app that works in mobile and desktop browsers for your convenience.

To access the back-office web app, click on the “Open back office” link under the BACKOFFICE section. Here you can customize how you backoffice looks and feels.

![alt text](http://konacloud.io/doc/gettingstarted/backoffice_config.png "Backoffice")


##6. Documentation

Very few people like to document their back-ends. This involves writing down all the APIs methods accessible from the outside, parameters, data structures and else.

KONA Cloud does this for you, automatically. Access your auto-generated backend APIs documentation in the DOC section. This doc is generated on-the-fly every time is accessed and allows you to auto-document your services so you can send that doc link to the mobile developers and they will know exactly how to call your services, and even with sample data structures to send!

![alt text](http://konacloud.io/doc/gettingstarted/doc.png "Doc")

By clicking the External Link, you can share this with your mobile developers. Any change you do to the data models, API or storage buckets are automatically updated in the doc without any extra effort on your side.
