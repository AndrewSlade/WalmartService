
### What and Why

The WalmartService java application based on Spring JPA, Spring Boot and gradle tools.


Service for holding and reserving tickets for concert. Walmart application program


### Features
This service serves to facilitate a reservation system for a concert venue.

This is accomplished via a variety of public endpoints. 
These are:
1. the total number of seats available (no parameters)
2. Find and hold a block (accepts a total number of seats and customer email) 
3. Convert a hold to a reservation given a block id and customer email


### Assumptions
Concert area is 10 x 10 uniform arena. This can be modified by adjusting the constants in the TicketingConstants class.

Patrons #1 desire is seating together in a straight line. Patrons will not be split between two rows in a single order. (Potential for a couple to be split up, most likely not desired)

Seating desireability starts in front and decreases further back.


### Getting started
Download and install **Gradle**. [latest release](http://gradle.org/gradle-download/)]
If you are using an IDE like Eclipse, you can also download the gradle plugin from "Buildship"
	Import project into eclipse as gradle project


### Unit tests
Unit tests for services are run using `gradle build`

# WalmartService




