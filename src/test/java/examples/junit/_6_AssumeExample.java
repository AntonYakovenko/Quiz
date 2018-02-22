package examples.junit;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _6_AssumeExample {
    @Test
    public void test_fail_but_ignored() {
        List<String> list = new ArrayList<>();
        Assume.assumeTrue(!list.isEmpty());
        Assert.assertThat(list.get(0), Matchers.is("A"));
    }
}
