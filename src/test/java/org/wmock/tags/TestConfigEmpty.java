package org.wmock.tags;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@DisplayName("From Tag1")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public @interface TestConfigEmpty {
}
