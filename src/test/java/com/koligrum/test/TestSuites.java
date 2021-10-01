package com.koligrum.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSuites {
    CreateUserTest createUserTest = new CreateUserTest();
    GetUserByNameTest getUserByNameTest = new GetUserByNameTest();
    GetUserById getUserById = new GetUserById();
    UpdateUserTest updateUserTest = new UpdateUserTest();
    DeleteUserById deleteUserById = new DeleteUserById();


    @Test
    @Order(1)
    public void createUser(){
        createUserTest.createUsers();
    }

    @Test
    @Order(2)
    public void getUserByName(){
        getUserByNameTest.getUsers();
    }

    @Test
    @Order(3)
    public void getUserById(){
        getUserById.getUsersById();
    }

    @Test
    @Order(4)
    public void updateUser(){
        updateUserTest.updateUsers();
    }

    @Test
    @Order(5)
    public void deleteUser(){
        deleteUserById.deleteUserById();
    }
}
