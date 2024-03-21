package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class IAMRolePolicyVerifier {

    public static boolean validateResourceField(String path) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(path);

            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            JsonNode jsonNodeTree = jsonNode.path("PolicyDocument")
                    .path("Statement")
                    .get(0)
                    .path("Resource");

            return !("*".equals(jsonNodeTree.asText()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static void main(String[] args) {

        String path = "src/main/resources/IAMRolePolicy.json";
        System.out.println(validateResourceField(path));
    }
}
