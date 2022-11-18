Simple CQRS with Axon
---

This is a port of the application from [axon-quick-start](https://github.com/AxonIQ/axon-quick-start) application
intended as a quick intro to the Axon framework.

The difference is that I've tried to simplify some things.

- There is still `axon-spring-boot-starter` starter
- There is no Axon server. The application is completely standalone.
- There is no Event Sourcing. All aggregates (states) are persisted as JPA entities at the end of each domain event processing.
- Since there is no Event Sourcing there is no need for an EventStorageEngine.
- Everything (aggregates, projections) is persisted into the in-memory H2 database.

The idea is to see what minimal beans need to be declared to make this scenario work.

- We still need to declare and configure an instance of `org.axonframework.commandhandling.SimpleCommandBus`
- We also need an instance of `org.axonframework.eventhandling.SimpleEventBus`
- The rest of the infrastructure is wired by the starter

#### Copyright disclaimer and acknowledgements

This code is based heavily on the code for the [axon-quick-start](https://github.com/AxonIQ/axon-quick-start)
exercise (and the solution).
