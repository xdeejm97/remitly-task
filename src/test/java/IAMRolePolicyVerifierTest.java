import com.example.IAMRolePolicyVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IAMRolePolicyVerifierTest {

    @Test
    void isNullResources(){

        var isNullResource = IAMRolePolicyVerifier.validateResourceField("src/main/resources/IAMRolePolicy.json");
        assertFalse(isNullResource);

    }

    @Test
    void shouldReturnFalseDueToContainingAsteriks(){


        var contain = IAMRolePolicyVerifier.validateResourceField("src/main/resources/IAMRolePolicy.json");

        assertFalse(contain);

    }
    @Test
    void shouldReturnTrueDueToNotContainingAsteriks(){

        // mockowanie?
        var contain = IAMRolePolicyVerifier.validateResourceField("src/main/resources/IAMRolePolicy.json");


        assertTrue(contain);

    }

}