package examples.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class _8_ParametrizedExample {
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return asList(new Object[][]{{-3, 3}, {-2, 2}, {-1, 1}, {0, 0}, {1, 1}, {2, 2}, {3, 3}});
    }

    @Parameterized.Parameter(0)
    public int input;

    @Parameterized.Parameter(1)
    public int expectedResult;

    @Test
    public void test() {
        assertThat(abs(input), is(expectedResult));
    }
}
