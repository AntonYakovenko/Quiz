package examples.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

import static java.util.Arrays.asList;

public class StartWith extends TypeSafeMatcher<List<String>> {
    private final String[] head;

    public StartWith(String[] head) {
        this.head = head;
    }

    @Override
    protected boolean matchesSafely(List<String> strings) {
        if (strings.size() < head.length) {
            return false;
        }

        for (int k = 0; k < head.length; k++) {
            if (!head[k].equals(strings.get(k))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("start with" + asList(head));
    }

    @Factory
    public static Matcher<List<String>> startWith(String... head) {
        return new StartWith(head);
    }
}
