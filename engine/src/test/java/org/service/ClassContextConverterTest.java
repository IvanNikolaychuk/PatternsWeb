package org.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.Code;
import org.service.decoration.context.ClassContext;
import org.service.decoration.helpers.ClassContextConverter;
import org.service.decoration.helpers.CodeSplitter;
import org.service.decoration.elements.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/engine-context.xml")
public class ClassContextConverterTest {

    @Autowired
    private CodeSplitter codeSplitter;

    @Autowired
    private ClassContextConverter classContextConverter;

    @Test
    public void testFlow() {
        final String code = "import org.test;\n" +
                            "public class MyClass {\n" +
                                 "// hello\n" +
                                 "private int a;" +
                            "}";
        String[] splitted = codeSplitter.split(new Code(code));

        ClassContext<CodeElement> classContext = classContextConverter.convert(new ClassContext<>(splitted));

        check(classContext.next(), KeyWord.class, "import");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), GenericElement.class, "org");
        check(classContext.next(), PunctuationSign.class, ".");
        check(classContext.next(), GenericElement.class, "test");
        check(classContext.next(), PunctuationSign.class, ";");
        check(classContext.next(), NewLineElement.class, "\n");
        check(classContext.next(), KeyWord.class, "public");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), KeyWord.class, "class");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), GenericElement.class, "MyClass");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), GenericElement.class, "{");
        check(classContext.next(), NewLineElement.class, "\n");
        check(classContext.next(), Comment.class, "//");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), Comment.class, "hello");
        check(classContext.next(), NewLineElement.class, "\n");
        check(classContext.next(), KeyWord.class, "private");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), KeyWord.class, "int");
        check(classContext.next(), GenericElement.class, " ");
        check(classContext.next(), ClassField.class, "a");
        check(classContext.next(), PunctuationSign.class, ";");
        check(classContext.next(), GenericElement.class, "}");



    }

    private void check(CodeElement element, Class expectedClass, String expectedValue) {
        assertEquals(element.getClass(), expectedClass);
        assertEquals(element.getValue(), expectedValue);

    }
}
