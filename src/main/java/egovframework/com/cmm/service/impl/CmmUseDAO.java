package egovframework.com.cmm.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;

/**
 * @Class Name : CmmUseDAO.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.     이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("cmmUseDAO")
@RequiredArgsConstructor
public class CmmUseDAO {

	private final SqlSessionTemplate sessionTemplate;

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) {
		return sessionTemplate.selectList("CmmUseDAO.selectCmmCodeDetail", vo);
	}

	/**
	 * 주어진 조건에 따른 3Depth 공통코드를 불러온다.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * **/
	public List<CmmnDetailCode> selectHierarchicalCmmCodeDetail(ComDefaultCodeVO vo) {
		return sessionTemplate.selectList("CmmUseDAO.selectHierarchicalCmmCodeDetail", vo);
	}

}
