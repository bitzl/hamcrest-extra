package io.bitzl.hamcrest.extra.matcher;

import org.hamcrest.*;
import org.hamcrest.beans.HasPropertyWithValue;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


public class HasPropertyPath<T> extends TypeSafeDiagnosingMatcher<T> {

    private String propertyPath;
    private Matcher<?> valueMatcher;

    HasPropertyPath(String propertyPath, Matcher<?> valueMatcher) {
        this.propertyPath = propertyPath;
        this.valueMatcher = valueMatcher;
    }


    @Factory
    public static <T> Matcher<T> hasPropertyPath(String propertyPath) {
        return new HasPropertyPath<T>(propertyPath, anything());
    }

    @Factory
    public static <T> Matcher<T> hasPropertyPath(String propertyPath, Matcher<?> valueMatcher) {
        return new HasPropertyPath<T>(propertyPath, valueMatcher);
    }

    protected boolean matchesSafely(T t, Description description) {
        String[] path = propertyPath.split("\\.");
        Matcher<?> matcher = valueMatcher;
        for (int i = path.length - 1; i >=0; i--) {
            String property = path[i];
            System.out.println("Add property " + property + ": " + matcher);
            matcher = hasProperty(property, matcher);
        }
        System.out.println("Matcher now is " + matcher);
        return ((HasPropertyWithValue<T>) matcher).matchesSafely(t, description);
    }

    public void describeTo(Description description) {
        description.appendText("hasPropertyPath(").appendValue(this.propertyPath).appendText(", ").appendDescriptionOf(this.valueMatcher).appendText(")");
    }
}
