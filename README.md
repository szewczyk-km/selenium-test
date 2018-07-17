## Seed project for running java based selenium tests by RemoteWebDriver inside docker container.
***Designed to run tests remotely on selenium grid hub by the GitLab CI Pipeline.***

### Usage

Build project and docker image: 

`mvn clean install` 

Run options:
```
usage: run [OPTION]...
 -c,--capability <browser>                Desired capability passed to
                                          remote web driver (firefox,
                                          chrome, ie, edge, opera, safari)
 -senv,--selenium-run-environment <url>   A url pointing to selenium run
                                          environment
 -t,--timeout <seconds>                   Driver implicit timeout
 -url,--application-url <url>             A url pointing to tested
                                          application
```
Example:

###### cmd
```
docker run --net="host" -it selenium-test run -url https://en.wikipedia.org -senv http://localhost:4444/wd/hub
```
###### .gitlab-ci.yml
```
...your pipeline config...
stages:
  - ui-test

selenium-tests:
  stage: ui-test
  image: selenium-test
  script:
    - run -url https://en.wikipedia.org -senv http://grid.selenium.example.com:4444/wd/hub  
```

### Selenium Run Environment Setup

#### Local
If You want to debug tests locally the easiest way is to install and run chrome-driver in server mode: https://sites.google.com/a/chromium.org/chromedriver/home

#### Selenium Grid Hub

To setup selenium grid follow instructions on: https://github.com/SeleniumHQ/docker-selenium#selenium-grid-hub-and-nodes

#### Selenium Grid Hub Debug
In case you wish to visually see what the browser is doing on selenium grid check out this link: https://github.com/SeleniumHQ/docker-selenium#debugging  

Here is sample docker compose configuration with grid debug env, connect with vnc to 5900 for chrome and 5901 for firefox   
*Note: To view what nodes are doing use realvnc I expired problems with remina. Always remember to call quit() on driver after tests are finished because debug images tend to stuck and restart is required*

###### docker-compose.yml
```yml
version: "3"
services:
  selenium-hub:
    image: selenium/hub:3.13.0-argon
    container_name: selenium-hub
    ports:
      - "4444:4444"
  chrome:
    image: selenium/node-chrome-debug:3.13.0-argon
    depends_on:
      - selenium-hub
    environment:
      - HUB_HOST=selenium-hub
      - HUB_PORT=4444
    ports:
      - "5900:5900"
  firefox:
    image: selenium/node-firefox-debug:3.13.0-argon
    depends_on:
      - selenium-hub
    environment:
      - HUB_HOST=selenium-hub
      - HUB_PORT=4444
    ports:
      - "5901:5900"
```

