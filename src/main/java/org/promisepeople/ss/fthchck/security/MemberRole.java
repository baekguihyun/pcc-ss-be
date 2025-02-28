package org.promisepeople.ss.fthchck.security;

import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.domain.MemberParent;
import org.promisepeople.ss.fthchck.domain.MemberStudent;
import org.promisepeople.ss.fthchck.domain.MemberTeacher;
import org.promisepeople.ss.fthchck.dto.MemberDTO;

public enum MemberRole {
	MEMBER("00"),
	STUDENT("01"),
	PARENT("02"),
	TEACHER("03");

	private final String mbrType;

	MemberRole(String mbrType) {
		this.mbrType = mbrType;
	}

	public String getMbrType() {
		return mbrType;
	}

	public static MemberRole valueOfType(Member member) {
		if (member instanceof Member) {
			return MEMBER;
		}
		else if (member instanceof MemberStudent) {
			return STUDENT;
		}
		else if (member instanceof MemberParent) {
			return PARENT;
		}
		else if (member instanceof MemberTeacher) {
			return TEACHER;
		}
		else {
			return null;
		}
	}

	public static MemberRole valueOfType(MemberDTO member) {
		return valueOfType(member.getMbrType());
	}

	public static MemberRole valueOfType(String mbrType) {
		for (MemberRole mbrRole : MemberRole.values()) {
			if (mbrRole.mbrType.equals(mbrType)) {
				return mbrRole;
			}
		}

		return null;
	}
}
