package org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.decoration.helpers.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.service.decoration.helpers.ClassContextConverter.State.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-module-context.xml")
public class StateManagerTest {

    @Autowired
    private StateManager stateManager;

    @Test
    public void beforeClassThanCommentThanBeforeClass() {
        String[] classContext = new String[] { "//test", "\n\n\n"};

        assertEquals(stateManager.getActualState(""), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[0]), IN_COMMENT);
        assertEquals(stateManager.getActualState(classContext[1]), BEFORE_CLASS);
    }

    @Test
    public void beforeClassThanClass() {
        String[] classContext = new String[] { "public class Test", "{"};

        assertEquals(stateManager.getActualState(""), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[0]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[1]), IN_CLASS);
    }

    @Test
    public void beforeClassThanClassThanCommentThanMethod() {
        String[] classContext = new String[] { "public class Test", "{", "/*comment", "*/", "\n",
                "public void test(){", "}" };

        assertEquals(stateManager.getActualState(""), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[0]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[1]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[2]), IN_COMMENT);
        assertEquals(stateManager.getActualState(classContext[3]), IN_COMMENT);
        assertEquals(stateManager.getActualState(classContext[4]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[5]), IN_METHOD);
        assertEquals(stateManager.getActualState(classContext[6]), IN_CLASS);
    }

    @Test
    public void testGeneralFlow() {
        final String[] classContext = new String[] {
                "package", "org.service;", "\n",
                "public class ", "StateManagerTest ", "{", "\n",
                "//here comes comment", "\n",
                "public void test1()", "{",
                "return 12;",
                "}",
                "}"
        };

        assertEquals(stateManager.getActualState(classContext[0]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[1]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[2]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[3]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[4]), BEFORE_CLASS);
        assertEquals(stateManager.getActualState(classContext[5]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[6]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[7]), IN_COMMENT);
        assertEquals(stateManager.getActualState(classContext[8]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[9]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[10]), IN_METHOD);
        assertEquals(stateManager.getActualState(classContext[11]), IN_METHOD);
        assertEquals(stateManager.getActualState(classContext[12]), IN_CLASS);
        assertEquals(stateManager.getActualState(classContext[13]), IN_CLASS);

    }
}
