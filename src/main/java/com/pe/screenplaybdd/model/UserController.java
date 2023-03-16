package com.pe.screenplaybdd.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserController {


    private List<User> users = new ArrayList<>();


    public void addAllUser(Collection<User> users) {
        this.users.addAll(users);
    }
}
