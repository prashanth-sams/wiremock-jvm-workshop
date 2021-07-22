package org.wmock.tags;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@DisplayName("From Tag")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public @interface WMApiTestConfig {
    String level();
}
