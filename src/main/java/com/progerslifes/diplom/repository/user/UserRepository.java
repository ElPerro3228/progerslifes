package com.progerslifes.diplom.repository.user;

import com.progerslifes.diplom.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u JOIN FETCH u.posts WHERE u.id = (:id)")
    User findByIdAndFetchPostsEagerly(@Param("id") Long id);

}
