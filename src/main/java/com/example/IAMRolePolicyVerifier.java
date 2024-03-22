package com.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IAMRolePolicyVerifier {

    public static boolean isValidateResourceField(String path) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(path);

            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            JsonNode jsonNodeTree = jsonNode.path("PolicyDocument")
                    .path("Statement")
                    .get(0)
                    .path("Resource");

            return !"*".equals(jsonNodeTree.asText()); // czy cpntains czy equals,

        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file " + e.getMessage() + "\nPlease check the path: " + path);
        } catch (NullPointerException e) {
            System.err.println("In JSON object there is a " + e.getMessage() + "\nCheck the JSON input or method that you are retrieving the object!");
        } catch (JsonParseException e) {
            System.err.println("Invalid data format. JSON format is required" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {

        String path = "src/main/resources/IAMRolePolicy.jon";
        System.out.println(isValidateResourceField(path));
    }
}
