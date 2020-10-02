package com.valuelinku.biz.repository;

import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@Test
	public void save() throws Exception {
		repository.save();
	}
}
