package org.wmock.tags;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@DisplayName("From Tag2")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public @interface TestConfigValue {
    String level() default "Level B";
}
