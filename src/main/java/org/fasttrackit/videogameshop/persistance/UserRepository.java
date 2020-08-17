package org.fasttrackit.videogameshop.persistance;

import org.fasttrackit.videogameshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
