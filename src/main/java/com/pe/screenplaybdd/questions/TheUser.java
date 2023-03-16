package com.pe.screenplaybdd.questions;

import com.pe.screenplaybdd.endpoints.UserBDDEndpoints;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.RestQuestionBuilder;

public class TheUser {
    public static Question<String> theValueName(String userName) {

        return new RestQuestionBuilder<String>().about("Cash account balance")
                .to(UserBDDEndpoints.Obtain_User.getPath())
                .with(request -> request.given().log().all().pathParam("username", userName))
                .returning(response -> response.then().log().all().extract().path("username"));

    }

    public static Question<String> message(String userName) {

        return new RestQuestionBuilder<String>().about("Cash account balance")
                .to(UserBDDEndpoints.Obtain_User.getPath())
                .with(request -> request.given().log().all().pathParam("username", userName))
                .returning(response -> response.then().log().all().extract().path("message"));

    }


}
