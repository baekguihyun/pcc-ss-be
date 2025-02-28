package org.promisepeople.ss.fthchck.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

/**
 * 회원_학부모
 */
@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "TBL_MBR_PARENT")
@PrimaryKeyJoinColumn(name = "PARENT_ID")
public class MemberParent extends Member {

}



