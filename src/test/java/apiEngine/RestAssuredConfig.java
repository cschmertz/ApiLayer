package apiEngine;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.config.JsonPathConfig;

public class RestAssuredConfig {

    public static void configureRestAssured() {
        RestAssured.config = RestAssured.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JACKSON_2))
                .jsonConfig(new JsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL));
    }

}
