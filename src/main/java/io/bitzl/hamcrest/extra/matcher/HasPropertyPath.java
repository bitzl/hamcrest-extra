package io.bitzl.hamcrest.extra.matcher;

import org.hamcrest.*;
import org.hamcrest.beans.HasPropertyWithValue;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

/**
 * A matcher to match nested properties transitively.
 *
 * @param <T> A generic type to fit in the static typing with other matchers.
 */
public class HasPropertyPath<T> extends TypeSafeDiagnosingMatcher<T> {

    private String propertyPath;
    private Matcher<?> valueMatcher;

    private HasPropertyPath(String propertyPath, Matcher<?> valueMatcher) {
        this.propertyPath = propertyPath;
        this.valueMatcher = valueMatcher;
    }


    /**
     * Matches if the specified path to a property exist. The matcher <code>hasPropertyPath("a.b.c")</code> matches on
     * <code>object</code>, if <code>object</code> has a property <code>a</code> which has a property <code>b</code>
     * which then has a property <code>c</code>. It does not matter if property <code>c</code> has a value or not.
     *
     * @param propertyPath The path to the desired property.
     * @param <T> A generic type to fit in the static typing with other matchers.
     * @return A matcher for the desired property path.
     */
    @Factory
    public static <T> Matcher<T> hasPropertyPath(String propertyPath) {
        return new HasPropertyPath<T>(propertyPath, anything());
    }

    /**
     * Matches if the specified path to a property exists and the value of the last path element is matched by
     * <code>valueMatcher</code>.
     *
     * Example: The matcher <code>hasPropertyPath("owner.pet.name", is("Pete")</code> matches on <code>object</code>,
     * if <code>object</code> has a property <code>owner</code> which has a property <code>pet</code> which then has a
     * property <code>name</code> and <code>name</code> has the value <code>"Pete"</code>.
     *
     * @param propertyPath The path to the desired property.
     * @param valueMatcher A matcher for the last element in the property path.
     * @param <T> A generic type to fit in the static typing with other matchers.
     * @return A matcher for the desired property path.
     */
    @Factory
    public static <T> Matcher<T> hasPropertyPath(String propertyPath, Matcher<?> valueMatcher) {
        return new HasPropertyPath<T>(propertyPath, valueMatcher);
    }

    protected boolean matchesSafely(T t, Description description) {
        String[] path = propertyPath.split("\\.");
        Matcher<?> matcher = valueMatcher;
        for (int i = path.length - 1; i >=0; i--) {
            String property = path[i];
            matcher = hasProperty(property, matcher);
        }
        return ((HasPropertyWithValue<T>) matcher).matchesSafely(t, description);
    }

    /**
     * Describes the matcher.
     *
     * @param description The description object to append the matchers description.
     */
    public void describeTo(Description description) {
        description.appendText("hasPropertyPath(").appendValue(this.propertyPath).appendText(", ").appendDescriptionOf(this.valueMatcher).appendText(")");
    }
}
