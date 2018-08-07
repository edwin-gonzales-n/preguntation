package com.preguntation.repositories;

import com.preguntation.models.user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<user,Long> {
}
