package org.promisepeople.ss.util;

import org.promisepeople.ss.fthchck.domain.Member;
import org.promisepeople.ss.fthchck.domain.MemberParent;
import org.promisepeople.ss.fthchck.domain.MemberStudent;
import org.promisepeople.ss.fthchck.domain.MemberTeacher;

public class MemberUtil {

	public static String getMbrType(Member member) {

		String mbrType = "00";

		if (member instanceof MemberStudent) {
			mbrType = "01";
		}
		else if (member instanceof MemberParent) {
			mbrType = "02";
		}
		else if (member instanceof MemberTeacher) {
			mbrType = "03";
		}

		return mbrType;
	}

	public static String convertRoleName(String mbrType) {
		if ("00".equals(mbrType)) {
			return "MEMBER";
		}
		else if ("01".equals(mbrType)) {
			return "STUDENT";
		}
		else if ("02".equals(mbrType)) {
			return "PARENT";
		}
		else if ("03".equals(mbrType)) {
			return "TEACHER";
		}
		else {
			return null;
		}

	}
}
