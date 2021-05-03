package com.progerslifes.diplom.solr.repository;

import com.progerslifes.diplom.solr.document.User;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface UserSolrRepository extends SolrCrudRepository<User, String> {

    @Query(value = "*:*")
    List<User> getUsers();

    @Query(value = "username:?0~")
    List<User> getUserByUsername(String username);

    @Query(value = "username:?0~ OR name:?1~")
    List<User> getUserByUsernameOrByName(String username, String name);
}
