package org.wmock;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.wmock.port.DynamicPortTests;
import org.wmock.port.StaticPortTests;

// JUnit 4 based test suite execution
@RunWith(JUnitPlatform.class)
@SelectClasses({StaticPortTests.class, DynamicPortTests.class})
@SelectPackages("org.wmock.port")
public class TestRunner {

}
