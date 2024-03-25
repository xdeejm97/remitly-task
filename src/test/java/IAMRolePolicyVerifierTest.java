import com.example.IAMRolePolicyVerifier;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IAMRolePolicyVerifierTest {

    @Test
    void testHandleFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> {
            IAMRolePolicyVerifier.isValidateResourceField("src/test/resources/.json");
        });
    }
    @Test
    void testHandleNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            IAMRolePolicyVerifier.isValidateResourceField(null);
        });
    }
    @Test
    void testHandleJsonParseException() {
        assertThrows(JsonParseException.class, () -> {
            IAMRolePolicyVerifier.isValidateResourceField("src/test/resources/wrongJSON.json");
        });
    }
}