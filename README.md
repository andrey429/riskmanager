riskmanager
===========

**riskmanager** is an application I wrote for my graduation work from university. 
Purpose of the project was to develop a solution,that would be capable of performing some common tasks
of information security management tasks as per Standart of Bank of Russia: STO BR IBBS.

Used technologies/frameworks
----------------------------

**riskmanager** functions as a web-service availble to a remote user involved in a process of management.
Source code features usage of the following technologies:

**Server-side:**
* JSP
* Spring MVC
* Spring Security
* Hibernate ORM
* dox4j
* MySQL

**Client side:**

* jQuery
* CSS

Features
--------

Primary automation tasks **riskmanager** is capable of:
* Authenticate remote user and grant him privileges based on his role (Admin, Operator, etc)
* Gather information on information assets from remote user input
* Export reports containing assets scope  to *.docx files for further usage
* Perform self-assessment and evaluate key indexes of information security states as per standard (STO BR IBBS) and build required diagrams
* Export self-assessment results to *.docx file

Some screenshots
----------------
### Login screen

![login screen](/screenshots/1.jpg "login screen")

### Client side

![logged on](/screenshots/2.jpg "logged on")

### Inserting asset description

![insert asset description](/screenshots/3.jpg "insert asset description")

### Diagram

![diagram](/screenshots/4.jpg "diagram")

### Part of a sample report

![part of a sample report](/screenshots/5.jpg "part of a sample report")
