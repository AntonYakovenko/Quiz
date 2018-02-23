package examples.mockito;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class _1_mid_mock {
    public static void main(String[] args) {
        List list = mock(List.class);

        when(list.get(anyInt())).thenReturn("0");
        when(list.get(eq(5))).thenReturn("A");
        ArgumentMatcher<Integer> matcher = new ArgumentMatcher<>() {
            @Override
            public boolean matches(Object arg) {
                return ((Integer) arg) % 3 == 0;
            }
        };
        when(list.get(Mockito.intThat(matcher))).thenReturn("B");
        when(list.get(eq(9))).thenThrow(new RuntimeException("Hello!"));

        for (int k = 0; k < 10; k++) {
            System.out.println("list get(" + k + "): " + list.get(k));
        }
    }
}
