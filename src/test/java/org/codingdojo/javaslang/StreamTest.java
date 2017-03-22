package org.codingdojo.javaslang;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import javaslang.collection.List;

public class StreamTest {

	@Test
	public void shouldCreateListFromValues() throws Exception {
		// Given

		// When
		List<String> abcList = List.of("a", "b", "c");

		// Then
		assertThat(abcList).contains("a", "b", "c");
	}

	@Test
	public void shouldCreateSerieList() throws Exception {
		// Given

		// When
		List<Integer> numbersList = List.rangeBy(0, 30, 3);

		// Then
		assertThat(numbersList).contains(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
	}
}
