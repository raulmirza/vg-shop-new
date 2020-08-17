package org.fasttrackit.videogameshop;

import org.fasttrackit.videogameshop.domain.User;
import org.fasttrackit.videogameshop.service.UserService;
import org.fasttrackit.videogameshop.steps.unittests.UserSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSteps userSteps;

    @Test
    public void createUser_whenValidRequest_thenReturnCreatedUser() {
        userSteps.createUser();
    }

    @Test
    public void getUser_whenExistingUser_thenReturnUser() {
        User user = userSteps.createUser();

        User userResponse = userService.getUser(user.getId());

        assertThat(userResponse, notNullValue());
        assertThat(userResponse.getId(), is(user.getId()));
        assertThat(userResponse.getRole(), is(user.getRole()));
        assertThat(userResponse.getFirstName(), is(user.getFirstName()));
        assertThat(userResponse.getLastName(), is(user.getLastName()));
    }

}