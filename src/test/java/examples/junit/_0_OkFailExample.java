package examples.junit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _0_OkFailExample {

    @Test
    public void test_Ok() {
        List<String> list = new ArrayList<>();
        list.add("A");
        if (list.size() != 1) {
            throw new AssertionError();
        }
    }

    @Test
    public void test_Fail() {
        List<String> list = new ArrayList<>();
        list.add("A");
        if (!list.get(0).equals("B")) {
            throw new AssertionError();
        }
    }
}
