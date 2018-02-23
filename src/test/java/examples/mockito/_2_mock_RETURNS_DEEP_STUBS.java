package examples.mockito;

import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

public class _2_mock_RETURNS_DEEP_STUBS {
    public static void main(String[] args) {
        List list = Mockito.mock(List.class, Mockito.withSettings().defaultAnswer(RETURNS_DEEP_STUBS));

        System.out.println(list.iterator());
        System.out.println(list.iterator().hasNext());
        System.out.println(list.iterator().next());
    }
}
