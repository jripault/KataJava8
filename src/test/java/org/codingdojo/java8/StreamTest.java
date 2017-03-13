package org.codingdojo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Julien on 22/02/2017.
 */
public class StreamTest {

    @Test
    public void shouldCreateStreamFromValues() throws Exception {
        //Given

        //When
        List<String> abcList = Arrays.asList("a", "b", "c");

        //Then
        assertThat(abcList).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldCreateSerieStream() throws Exception {
        //Given

        //When
        List<Integer> numbersList = new ArrayList<>();
        for (int i = 0; i <= 27; i++) {
            numbersList.add(i + 3);
        }

        //Then
        assertThat(numbersList).containsExactly(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
    }
}
