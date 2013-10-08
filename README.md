[![Build Status](https://travis-ci.org/aerogear/aerogear-controller-demo.png?branch=master)](https://travis-ci.org/aerogear/aerogear-controller-demo)

**NOTE**: THIS PROJECT WILL NO LONGER BE MAINTAINED AND THE WHOLE PROJECT WILL BE REPLACED BY JAX-RS

# AeroGear Controller Demo - very lean mvc controller
[AeroGear Controller](https://github.com/aerogear/aerogear-controller) is a very lean model view controller written in Java. 
It focuses on the routing of HTTP request to plain Java object endpoint and the handling of the returned result. The result of 
an invocation is either forwarded to a view, or returned in the format requested by the caller

This project show cases some of the functionality of AeroGear Controller.  

An instance of this demo is deployed on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/), but it 
can also be deployed locally. Please refer to the [installation](#installation) section for deploying locally.

## Demo Contents
This demo project has a number of [routes](https://github.com/aerogear/aerogear-controller-demo/blob/master/src/main/java/org/jboss/aerogear/controller/demo/Routes.java#L45), 
some are used in a MVC fashion while others are expected to be called as RESTful resources.

### Model View Controller Routes
These routes are used for the web based interface which you can find on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/). 
Please refer to the [user guide](http://aerogear.org/docs/guides/aerogear-controller) for detailed information about configuring routes.

### RESTful Controller Routes
There are few routes that are intended to respond with JSON data. These routes deal with the ```/cars``` resource and demonstrate 
[pagination](http://aerogear.org/docs/guides/aerogear-controller/pagination.html).  
The basic idea is that a client wants to limit the number of _cars_ it receives per request to a certain number.

#### Requesting the first set of cars:

     curl -i --header "Accept: application/json" "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=0&color=red&limit=5"

Running the above command will produce output similar to the below:  

    HTTP/1.1 200 OK
    Link: <http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=5&color=red&limit=5>; rel="next"
    Content-Type: application/json;charset=UTF-8
    Content-Length: 194
    [
      {"color":"red","brand":"Audi","id":6},
      {"color":"red","brand":"BMW","id":13},
      {"color":"red","brand":"Fiat","id":20},
      {"color":"red","brand":"Golf","id":27},
      {"color":"red","brand":"Lada","id":34}
    ]
By default, AeroGear Controller uses [Web Linking](http://tools.ietf.org/html/rfc5988) specification and the ```Link``` header
above is an example of this. A client can use these links to navigate, to the next/previous page.

#### Requesting the next set of cars:
To retrieve the next set of cars, use the URL from ```next``` (see the ```Link``` header in the previous example):

     curl -i --header "Accept: application/json" "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=5&color=red&limit=5"


## Basic / Digest Authentication

The demo has built-in support for HTTP Basic / Digest authentication. Because both authentication schemas can't coexist, Digest authentication is enabled and configured by default. To switch the authentication schema (e.g. Basic), uncomment and comment the appropriate sections in the app's ```web.xml``` file.

#### Testing HTTP Basic authentication

     curl --basic -b cookies.txt -c cookies.txt -u john:123 "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/autobots" -v

#### Testing Digest authentication

     curl --digest -b cookies.txt -c cookies.txt -u agnes:123 "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/autobots" -v

Note: for Digest authentication, a username called 'agnes' is configured instead of 'john' as in 'Basic' authentication.

## Hawk Authentication

Hawk provides an authentication scheme using a message authentication code (MAC) algorithm using a nonce in combination with a timestamp. For more detailed information please refer to the [Hawk page](https://github.com/hueniverse/hawk).

To make use of it on AeroGear, make sure that your *web.xml* has it enabled. This demo already comes with the filter commented, so is just a matter of uncomment the source and have fun.

Once you have enabled the filter, this demo will become a Hawk Server. To test it, please make use of already existing client libraries for [Node.js](https://github.com/hueniverse/hawk), [Ruby](https://github.com/tent/hawk-ruby) or [Java](https://github.com/wealdtech/hawk).  

## Installation
Building the project is done using maven:
    
    mvn install
    
### Deploy
An AeroGear Controller application can be deployed to any application server with a CDI container.  
However, this demo uses a datasource that by default exists in JBoss AS 7.x and it is the preferred deployment environment.

#### Manual deployment

    $ cp target/aerogear-controller-demo.war $AS7_HOME/standalone/deployments

#### CLI deployment

    $ cd $AS7_HOME
    $ ./jboss-cli.sh --connect
    $ [standalone@localhost:9999 /] deploy /path/to/aerogear-controller-demo/target/aerogear-controller-demo.war
     
## Documentation
* [User Guide](http://aerogear.org/docs/guides/aerogear-controller)
* [API](http://aerogear.org/docs/specs/aerogear-controller)
* [REST API](http://aerogear.org/docs/specs/aerogear-rest-api)
* [JBoss CLI](https://community.jboss.org/wiki/CommandLineInterface)

## Community
* [User Forum](https://community.jboss.org/en/aerogear?view=discussions)
* [Developer Mailing List](http://aerogear-dev.1069024.n5.nabble.com)

## Issue Tracker
* [JIRA](https://issues.jboss.org/browse/AEROGEAR)
