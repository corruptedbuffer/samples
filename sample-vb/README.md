# sample-vb

This project contains a sample CRUD application using AngularJS and Spring Boot. 

### Requirements

<ul>
	<li>Java 8</li>
	<li>Maven >= 3 </li>
</ul>

### Running the application

Execute these maven commands at the sample-vb level</strong>:

~$ mvn clean install (this generates the jar) <br/>
~$ mvn spring-boot:run (this runs the application with an embedded tomcat)

Open <a href="http://localhost:8080/">http://localhost:8080/</a> in the browser.

### Back-end implementation

The application is implemented with <strong>Spring Boot, Spring Data</strong> and <strong>Spring Data Rest</strong> to expose the repositories (<strong>see the pom.xml file</strong> for more information)

I am using an embedded H2 database and every time the application restarts it generates the DB structure and imports some test data. The DB configuration can be modified in application.properties and the initial imports in import.sql. 

Please note, the H2 console is accessible via web under <a href="http://localhost:8080/h2-console"> http://localhost:8080/h2-console</a> whenever the application is running. It is useful to have a look at the real tables and can be deactivated in the application.properties file.

I have gone for a model in which we have two entities: <strong>company</strong> and <strong>person</strong>. A company can have many employees and beneficiary owners (two relations many-to-many to person).

The main front-end page can be obtained under <a href="http://localhost:8080/">http://localhost:8080/</a> and the REST operations are available under <a href="http://localhost:8080/api">http://localhost:8080/api</a>

I have introduced <a href="http://docs.spring.io/spring-data/rest/docs/current/reference/html/#_the_hal_browser">spring-data-rest-hal-browser</a> so that there is a web application with all the REST services provided by the application. The URL for the hal browser is <a href="http://localhost:8080/api/browser/index.html#/api">http://localhost:8080/api/browser/index.html#/api</a>. I have found it similar to <a href="http://swagger.io/">swagger</a>

### cURL calls

As discussed this is a fully HAL compliant application so we can make GET; POST, PUST...requests in a RESTfulway. Please refer to the HAL client for detailed information.

#### Operations available

curl http://localhost:8080/api

```
{
  "_links" : {
    "people" : {
      "href" : "http://localhost:8080/api/people{?page,size,sort}",
      "templated" : true
    },
    "companies" : {
      "href" : "http://localhost:8080/api/companies{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile"
    }
}
```

### Front-end implementation

The technology of choice has been <strong>AngularJS</strong> and <strong>Bootstrap</strong> and some plugins like angular-datables, ng-notifications-bar and ng-dialog (<strong>see the bower.json file</strong> for more information).

I am using server side sorting/ pagination for the retrieval of companies. The configuration of the datatable is a little bit more involved that if I had loaded all companies directly and had left the front-end do its job...but I think it is a good idea performance-wise.

Since the application is simple, I have not needed to configure modules, routes, etc. and I have limited the beneficiary owners functionalities to introduction/ removal (as indicated in the spec). The search (in the dialog) does not paginate but I did not think it necessary (since the user cannot even introduce new people) 