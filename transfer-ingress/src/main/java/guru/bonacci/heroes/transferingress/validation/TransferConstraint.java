package guru.bonacci.heroes.transferingress.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = TransferValidationDelegator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransferConstraint {

  String message() default "${errorMessage}";
  Class<? extends Payload>[] payload() default {};
  Class<?>[] groups() default {};

  String pool();
  String from();
  String to();
  String amount();
}