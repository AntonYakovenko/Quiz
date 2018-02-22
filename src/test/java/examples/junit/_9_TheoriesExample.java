package examples.junit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class _9_TheoriesExample {
    @DataPoints
    public static List[] data() {
        return new List[]{
                asList("A", "B", "C"),
                asList("F", "B", "C"),
                asList("A", "B", "C", "D")
        };
    }

//    /**
//     * This theory confirms that all lists start with "A" than "B"
//     */
//    @Theory
//    public void firstElementAreAB(List<String> example) {
//        assertThat(example, startWith("A", "B"));
//    }

    /**
     * This theory confirms that all lists contains only elements greater or equal "A"
     */
    @Theory
    public void allElementsGreaterThanOrEqualToA(List<String> example) {
        assertThat(example, everyItem(greaterThanOrEqualTo("A")));
    }

    /**
     * This theory confirms that all lists doesn't contain "X" or "Y"
     */
    @Theory
    public void excludeXY(List<String> example) {
        assertThat(example, not(anyOf(hasItem("X"), hasItem("Y"))));
    }
}
