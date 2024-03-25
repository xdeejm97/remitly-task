import com.example.IAMRolePolicyVerifier;
import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IAMRolePolicyVerifierTest {

    @Test
    void testResourceFieldDoesContainSingleAsterisk() throws IOException {
        String path = "src/test/resources/IAMRolePolicyTest.json";
        assertFalse(IAMRolePolicyVerifier.isValidateResourceField(path));

    }

    @Test
    void testResourceFieldDoesNotContainSingleAsterisk() throws IOException {
        String path = "src/test/resources/IAMRolePolicyTestWithoutAsterisk.json";
        assertTrue(IAMRolePolicyVerifier.isValidateResourceField(path));
    }

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