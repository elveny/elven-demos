package site.elven.test.java.jsr303;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

public class ValidateTest {

    @Test
    public void test1(){

        User user = new User(null, 34, 500, "我我我我我我我我我我我我我我我我我我我我");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Iterator<ConstraintViolation<User>> iterator = violations.iterator();
        while (iterator.hasNext()){
            String errorMessage = iterator.next().getMessage();
            System.out.println(errorMessage);
        }

    }
}
