riskmanager
===========

<em>riskmanager</em> was an application I created as my graduation work from university. 
Purpose of the project was to develop a solution,that would be capable of performing some common tasks
of information security management tasks as per Standart of Bank of Russia: STO BR IBBS.

Used technologies/frameworks
----------------------------

<em>riskmanager</em> uses such Java technologies, as <em>Spring MVC</em>, <em>Spring Security</em>, <em>Hibernate ORM</em>, <em>dox4j</em>. It functions 
as a web-service availble to a remote user involved in a process of management.


Features
--------

Primary automation tasks <em>riskmanager</em> is capable of:
* Authenticate remote user and grant him privileges based on his role (Admin, Operator, etc)
* Gather information on information assets from remote user input
* Export reports containing assets scope  to *.docx files for further usage
* Perform self-assessment and evaluate key indexes of information security states as per standard (STO BR IBBS) and build required diagrams
* Export self-assessment results to *.docx file

Some screenshots
----------------
![login screen](/screenshots/1.jpg "login screen")
![logged on](/screenshots/2.jpg "logged on")
![insert asset description](/screenshots/3.jpg "insert asset description")
![diagram](/screenshots/4.jpg "diagram")
![part of a sample report](/screenshots/5.jpg "part of a sample report")
