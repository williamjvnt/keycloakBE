package com.example.demo.Config;

import com.example.demo.Dto.Request.TokenRequest;
import com.example.demo.Util.MasterErrorOutputModel;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@ApiResponse( responseCode= "500",
        description= "GENERAL_ERROR<br><br>" +
                "DATABASE_ERROR<br><br>" +
                "DATA_ACCESS_RESOURCE<br><br>" +
                "RESOURCE_ACCESS<br><br>" +
                "SOCKET_TIMEOUT<br><br>" +
                "REST_CLIENT<br><br>" +
                "INTERRUPTED<br><br>" +
                "NULL_POINTER", content =
@Content(schema =
@Schema(implementation = MasterErrorOutputModel.class),
        examples = {
                @ExampleObject(
                        name = "Example User",
                        value = "{\"id\": 1,\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}"
                ),
                @ExampleObject(name ="GENERAL_ERROR", description = "General error", summary = "GENERAL_ERROR",
                        value = "{\"error_code\":"+"\"MIA-000-500,\""
                                +"\"error_message\":"+"}"),
                @ExampleObject(name ="DATABASE_ERROR", description = "DATABASE_ERROR", summary = "DATABASE_ERROR",
                        value = "{\"error_code\":" + "\"MIA-001-500\""+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="DATA_ACCESS_RESOURCE", description = "DATA_ACCESS_RESOURCE", summary = "DATA_ACCESS_RESOURCE",
                        value = "{\"error_code\":" + "MIA-002-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="RESOURCE_ACCESS", description = "RESOURCE_ACCESS", summary = "RESOURCE_ACCESS",
                        value = "{\"error_code\":" + "MIA-003-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="SOCKET_TIMEOUT", description = "SOCKET_TIMEOUT", summary = "SOCKET_TIMEOUT",
                        value = "{\"error_code\":" + "MIA-004-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="REST_CLIENT", description = "REST_CLIENT", summary = "REST_CLIENT",
                        value = "{\"error_code\":" + "MIA-005-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="INTERRUPTED", description = "INTERRUPTED", summary = "INTERRUPTED",
                        value = "{\"error_code\":" + "MIA-006-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),
                @ExampleObject(name ="NULL_POINTER", description = "NULL_POINTER", summary = "NULL_POINTER",
                        value = "{\"error_code\":" + "MIA-007-500"+ "\"error_message\":"
                                +"{\"indonesian\":"+"\"INTERNAL_SERVER_ERROR\""
                                + ",\"english\":"+ "\"INTERNAL_SERVER_ERROR\""+"}"),

        }, mediaType = MediaType.APPLICATION_JSON_VALUE))



public @interface ResponseDocumentation {
    //anjay
}
