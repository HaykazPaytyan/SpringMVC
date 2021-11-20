package com.base.springmvc.repository;

import com.base.springmvc.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends CrudRepository<User,Long> {
}
