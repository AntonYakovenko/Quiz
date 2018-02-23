package examples.mockito;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class _3_InOrder {
    public static void main(String[] args) {
        List list = Mockito.mock(List.class);

        list.add("A");
        list.remove("C");
        list.add("B");

        InOrder inOrder = Mockito.inOrder(list);
        inOrder.verify(list).remove("C");
        inOrder.verify(list).add("B");
        inOrder.verify(list).add("A");
        verifyNoMoreInteractions(list);
    }
}
