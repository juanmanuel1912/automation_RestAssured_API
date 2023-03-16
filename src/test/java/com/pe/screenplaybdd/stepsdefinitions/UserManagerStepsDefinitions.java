package com.pe.screenplaybdd.stepsdefinitions;

import com.pe.screenplaybdd.model.User;
import com.pe.screenplaybdd.questions.TheUser;
import com.pe.screenplaybdd.tasks.CreateUser;
import com.pe.screenplaybdd.tasks.DeleteUser;
import com.pe.screenplaybdd.tasks.UpdateUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.hamcrest.CoreMatchers.equalTo;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserManagerStepsDefinitions {
    EnvironmentVariables environmentVariables;
    private User user;


    @Given("^el owner con los siguiente detalles:$")
    public void elOwnerConLosSiguientesDetalles(List<Map<String, String>> listOfData) {
        theActorCalled("owner").whoCan(CallAnApi.at(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("url")));
        Map<String, String> userData = listOfData.get(0);
        this.user = new User(Integer.parseInt(userData.get("id")), userData.get("username"), userData.get("firstname"), userData.get("lastname"), userData.get("email"), userData.get("password"), userData.get("phone"), userData.get("userstatus"));


    }

    @When("^el owner registra el usuario$")
    public void elOwnerRegistraElUsuario() {
        theActorInTheSpotlight().attemptsTo(CreateUser.asNewUser(this.user));
    }

    @Then("^el usuario se debe visualizar en la lista con su nombre (.*)$")
    public void elUsuarioSeDebeVisualizarEnLaListaConSuNombre(String userName) {
        String userRegistred = theActorInTheSpotlight().recall("userName");
        System.out.println(userRegistred + "__________________________");
        theActorInTheSpotlight().should(seeThat(TheUser.theValueName(userRegistred), is(equalTo(userName))));
    }

    @When("^el owner actualiza el usuario (.*)$")
    public void elOwnerActualizaElUsuario(String userUpdate) {
        theActorInTheSpotlight().attemptsTo(UpdateUser.as(this.user, userUpdate));
    }

    @When("^el owner realiza la eliminación del usuario$")
    public void elOwnerRealizaLaEliminaciónDelUsuario() {
        theActorInTheSpotlight().attemptsTo(DeleteUser.how(this.user.getUsername()));
    }

    @Then("^el owner obtiene en la busqueda el mensaje (.*)$")
    public void elOwnerObtieneEnLaBusquedaElMensaje(String message) {
        String userRegistereds = theActorInTheSpotlight().recall("userName");
        theActorInTheSpotlight().should(seeThat(TheUser.message(userRegistereds), is(equalTo(message))));
    }
}
