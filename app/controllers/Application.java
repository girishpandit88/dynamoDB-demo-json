package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.models.DataResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.DBService;
import views.html.index;

public class Application extends Controller {
    private final DBService dbService;
    @Inject
    Application(DBService dbService){
        this.dbService = dbService;
    }
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result setData(){
        JsonNode jsonNode = request().body().asJson();
        DataResponse response = this.dbService.set(jsonNode);
        return ok(Json.toJson(response));
    }
}
