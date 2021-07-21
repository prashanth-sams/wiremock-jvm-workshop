package org.wmock;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.wmock.port.WMDynamicPortApiTests;
import org.wmock.port.WMStaticPortApiTests;

// JUnit 4 based test suite execution
@RunWith(JUnitPlatform.class)
@SelectClasses({WMStaticPortApiTests.class, WMDynamicPortApiTests.class})
@SelectPackages("org.wmock.port")
public class TestRunner {

}
