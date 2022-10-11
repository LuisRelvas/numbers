package com.aor.numbers;

import com.aor.numbers.ListSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ListSorterTest {



    @Test
    public void bug_sort_8276() {
        ListSorter sorter = new ListSorter();
        List<Integer> sorted = sorter.sort(Arrays.asList(1, 2, 4, 2));
        Assertions.assertEquals(Arrays.asList(1, 2, 2, 4), sorted);
    }


}
