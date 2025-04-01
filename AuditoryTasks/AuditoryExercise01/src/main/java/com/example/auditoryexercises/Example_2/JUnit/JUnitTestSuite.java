package com.example.auditoryexercises.Example_2.JUnit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({UnitTest_1.class, UnitTest_2.class})
public class JUnitTestSuite {
}
