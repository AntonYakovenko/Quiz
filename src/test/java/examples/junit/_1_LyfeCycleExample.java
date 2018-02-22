package examples.junit;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _1_LyfeCycleExample {
    private List<String> list;

    @Before
    public void setUp() {
        this.list = new ArrayList<>();
    }

    @Test
    public void test_Ok() {
        list.add("A");
        if (list.size() != 1) {
            throw new AssertionError();
        }
    }

    @Test
    public void test_Fail_convert() {
        list.add("A");
        if (!list.get(0).equals("B")) {
            throw new AssertionError();
        }
    }

}
