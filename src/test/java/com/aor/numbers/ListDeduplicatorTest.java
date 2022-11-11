package com.aor.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import com.aor.numbers.GenericListDeduplicator;
import com.aor.numbers.GenericListSorter;
import com.aor.numbers.ListDeduplicator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {

    List<Integer> list;
    private List<Integer> expected;

    @BeforeEach
    public void helper() {
        list = Arrays.asList(1, 2, 3, 4, 5);
    }

    @Test
    public void deduplicate() {
        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        Mockito.when(deduplicator.deduplicate(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 4));
        class StubListSorter implements GenericListSorter {
            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1, 2, 4, 5);
            }
        }
        StubListSorter sorter = new StubListSorter();
        ListDeduplicator deduplicator2 = new ListDeduplicator(sorter);
        List<Integer> distinct = deduplicator2.deduplicate(list);
        Assertions.assertEquals(Arrays.asList(1,2,4,5), distinct);
    }

    @Test
    public void bug_deduplicate_8726() {
        List<Integer> list = Arrays.asList(1, 2, 4, 2);
        class StubListSorter implements GenericListSorter {
            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1, 2, 2, 4);
            }
        }
        StubListSorter sorter = new StubListSorter();
        ListDeduplicator deduplicator = new ListDeduplicator(sorter);
        List<Integer> distinct = deduplicator.deduplicate(list);
        Assertions.assertEquals(Arrays.asList(1,2,4), distinct);
    }
}