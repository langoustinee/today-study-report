package com.kakao.lango.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kakao.lango.dto.Member;

public class MemberValidator implements Validator {

	// 유효성 검사를 수행할 DTO 설정하기
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	// 유효성 검사를 수행할 메서드
	@Override
	public void validate(Object target, Errors errors) {
		// 유효성 검사할 대상을 원래의 자료형으로 변환하기
		Member member = (Member) target;
		
		// 이메일 유효성 검사 진행하기
		if(member.getEmail() == null || member.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}
		// 암호 유효성 검사 진행하기
		if(member.getPassword() == null || member.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
		}

	}

}
