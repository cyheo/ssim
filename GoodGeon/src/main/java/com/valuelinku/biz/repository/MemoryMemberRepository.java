package com.valuelinku.biz.repository;

public class MemoryMemberRepository implements MemberRepository {

	@Override
	public void save() throws Exception{
		System.out.println("Test Good");
		
		throw new Exception("Error");
	}

}