package com.example;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IAMRolePolicyVerifier {

    public static boolean isValidateResourceField(String path) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File(path);

            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            JsonNode jsonNodeTree = jsonNode.path("PolicyDocument")
                    .path("Statement")
                    .get(0)
                    .path("Resource");

            return !"*".equals(jsonNodeTree.asText()); // I wasn't sure which method should I choose, because
            // I didn't get the answer, so I would consider contains method as well.
            // I was wondering about it, because I was thinking about single asterisks like: "   *    " or "my/url/*"

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find the file " + e.getMessage() + "\nPlease check the path: " + path);
        } catch (NullPointerException e) {
            throw new NullPointerException("In JSON object there is a " + e.getMessage() + "\nCheck the JSON input, path or method that you are retrieving the object from the JSON");
        } catch (JsonParseException e) {
            throw new JsonParseException("Invalid data format. JSON format is required.\n" + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Error reading JSON file: " + e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {

        String path = "src/main/resources/IAMRolePolicy.json";
        System.out.println(isValidateResourceField(path));
    }
}
