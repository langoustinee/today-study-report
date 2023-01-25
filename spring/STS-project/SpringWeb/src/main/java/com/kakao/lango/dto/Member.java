package com.kakao.lango.dto;

import lombok.Data;

@Data
public class Member {
	private String email;
	private String password;
	private String loginType;
}
