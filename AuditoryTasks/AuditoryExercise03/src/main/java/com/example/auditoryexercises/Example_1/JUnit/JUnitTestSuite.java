package com.example.auditoryexercises.Example_1.JUnit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({MessageTest_1.class, MessageTest_2.class, MessageTest_3.class})
public class JUnitTestSuite {
}
