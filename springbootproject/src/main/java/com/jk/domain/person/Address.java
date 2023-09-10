package com.jk.domain.person;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

	private String siDo;
	
	private String siGunGu;
	
	private String roGil;
	
	private String detailAddress;
	
	private String postalCode;

	@Builder
	public Address(String siDo, String siGunGu, String roGil, String detailAddress, String postalCode) {
		this.siDo = siDo;
		this.siGunGu = siGunGu;
		this.roGil = roGil;
		this.detailAddress = detailAddress;
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [siDo=" + siDo + ", siGunGu=" + siGunGu + ", roGil=" + roGil + ", detailAddress="
				+ detailAddress + ", postalCode=" + postalCode + "]";
	}
	
}
