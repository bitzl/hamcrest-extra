package io.bitzl.hamcrest.extra.matcher;

import org.junit.Before;
import org.junit.Test;

import static io.bitzl.hamcrest.extra.matcher.HasPropertyPath.hasPropertyPath;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class HasPropertyPathTest {

    private GraphObject first;

    @Before
    public void setUp() {
        first = new GraphObject("First");
        GraphObject second = new GraphObject("Second");
        GraphObject third = new GraphObject("Third");
        first.setNext(second);
        second.setNext(third);
    }

    @Test
    public void shouldMatchDirectProperty() {
        assertThat(first, hasPropertyPath("label", is("First")));
    }

    @Test
    public void shouldNotMatchIfValueMatcherFails() {
        GraphObject object = new GraphObject("First");
        object.setNext(new GraphObject("Second"));
        assertThat(object, not(hasPropertyPath("next.label", is("Fail"))));
    }

    @Test
    public void shouldNotMatchValueIfPathIsWrong() {
        GraphObject object = new GraphObject("First");
        object.setNext(new GraphObject("Second"));
        assertThat(object, not(hasPropertyPath("next.stuff", is("Second"))));
    }

    @Test
    public void shouldMatchValue() {
        assertThat(first, hasPropertyPath("next.next.label", is("Third")));
    }

    @Test
    public void shouldMatchIfPathExists() {
        assertThat(first, hasPropertyPath("next.next.label"));
    }

    @Test
    public void shouldNotMatchIfPathDoesNotExist() {
        assertThat(first, not(hasPropertyPath("next.next.fail")));
    }

}
