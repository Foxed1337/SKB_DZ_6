package ru.zenkov.skb_dz_6.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zenkov.skb_dz_6.exeptions.JsonFormatException;

@Controller
public class JsonController {

    private static long id = 0;

    @PostMapping("/get-json-with-id")
    public ResponseEntity<String> getJsonWithID(@RequestBody JsonNode inJson) throws JsonFormatException {

        try {
            var reqJson = (ObjectNode) inJson;
            if (reqJson.findValue("info").isEmpty()) {
                throw new JsonFormatException();
            }
            reqJson.with("info").put("id", ++id);
            return new ResponseEntity<>(reqJson.toPrettyString(), HttpStatus.OK);
        } catch (Exception e) {
            throw new JsonFormatException();
        }

    }

    @ExceptionHandler(JsonFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "json is invalid")
    public void handleException(JsonFormatException e) {

    }

}
