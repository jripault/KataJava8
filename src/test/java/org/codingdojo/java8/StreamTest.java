package org.codingdojo.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Julien on 22/02/2017.
 */
public class StreamTest {

    @Test
    public void shouldCreateStreamFromValues() throws Exception {
        //Given

        //When
        List<String> abcList = Stream.of("a", "b", "c").collect(Collectors.toList());

        //Then
        assertThat(abcList).containsExactly("a", "b", "c");
    }

    @Test
    public void shouldCreateSerieStream() throws Exception {
        //Given

        //When
        List<Integer> numbersList = Stream.iterate(0, ele -> ele + 3).limit(10).collect(Collectors.toList());

        //Then
        assertThat(numbersList).containsExactly(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
    }
}
