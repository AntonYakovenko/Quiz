package examples.junit;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _2_ExceptionsExample {
    private List<String> list;

    @Before
    public void setUp() {
        this.list = new ArrayList<>();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_check_exception() {
        list.get(0);

    }

    @Test(timeout = 100)
    public void test_timeout() {
        while (true);
    }
}
