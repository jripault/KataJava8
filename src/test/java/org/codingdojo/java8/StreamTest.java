package org.codingdojo.java8;

import org.junit.Test;

import java.util.ArrayList;
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
        List<String> abcList = new ArrayList<String>();

        //Then

        assertThat(abcList).contains("a", "b", "c");
    }

    @Test
    public void shouldCreateSerieStream() throws Exception {
        //Given

        //When
        List<Integer> numbersList = new ArrayList<Integer>();

        //Then
        assertThat(numbersList).contains(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
    }
}
