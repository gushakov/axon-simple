package com.github.axonsimple;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.modelling.command.GenericJpaRepository;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.DefaultQueryGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxonSimpleApplicationTests {


	@Autowired(required = false)
	@Qualifier("chatRoomRepository")
	private Repository repository;

	@Autowired(required = false)
	private CommandGateway commandGateway;

	@Autowired(required = false)
	private CommandBus commandBus;

	@Autowired(required = false)
	private EventBus eventBus;

	@Autowired(required = false)
	private QueryGateway queryGateway;

	@Autowired(required = false)
	private EventStorageEngine eventStorageEngine;

	@Test
	public void contextLoads() {
		assertThat(repository)
				.isNotNull()
				.isInstanceOf(GenericJpaRepository.class);
		assertThat(commandGateway)
				.isNotNull()
				.isInstanceOf(DefaultCommandGateway.class);
		assertThat(commandBus)
				.isNotNull()
				.isInstanceOf(SimpleCommandBus.class);
		assertThat(eventBus)
				.isNotNull()
				.isInstanceOf(SimpleEventBus.class);
		assertThat(queryGateway)
				.isNotNull()
				.isInstanceOf(DefaultQueryGateway.class);
		// assert that we do not have any EventStorageEngine
		assertThat(eventStorageEngine).isNull();
	}

}
