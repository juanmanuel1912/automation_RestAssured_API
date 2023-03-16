package com.pe.screenplaybdd.endpoints;

public enum UserBDDEndpoints {
    Create_User("/user"),
    Obtain_User("/user/{username}"),
    Actualizar_User("/user/{username}"),
    Eliminar_User("/user/{username}");


    private final String path;


    UserBDDEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
