package org.fasttrackit.videogameshop.steps.unittests;

import org.fasttrackit.videogameshop.domain.User;
import org.fasttrackit.videogameshop.domain.UserRole;
import org.fasttrackit.videogameshop.service.UserService;
import org.fasttrackit.videogameshop.transfer.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class UserSteps {
    @Autowired
    private UserService userService;

    public User createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setRole(UserRole.CUSTOMER);
        request.setFirstName("Test First Name");
        request.setLastName("Test Last Name");

        User user = userService.createUser(request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getRole(), is(request.getRole().name()));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));

        return user;
    }

}
