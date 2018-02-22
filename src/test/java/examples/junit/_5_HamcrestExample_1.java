package examples.junit;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.StrictMath.sin;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class _5_HamcrestExample_1 {

    private double ERROR;
    private List<Object> list;

    @Before
    public void setUp() {
        this.ERROR = 0.000_001;
        this.list = Arrays.asList("A", "B", "C");
    }

    @Test
    public void test_float() {
        assertThat(sin(100.0), is(closeTo(0.0, ERROR)));
    }

    @Test
    public void test_hasItem() {
        assertThat(list, hasItem("BBB"));
    }

    @Test
    public void test_B_or_C() {
        assertThat(list, anyOf(hasItem("B"), hasItem("C")));
    }

    @Test
    public void test_onlyB_or_onlyC() {
        assertThat(list, anyOf(allOf(hasItem("B"), not(hasItem("C"))), allOf(not(hasItem("B")), hasItem("C"))));
    }

}
