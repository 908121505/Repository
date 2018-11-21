package com.honglu.quickcall.common.third.shumei.reponse;

public class WordFilterResponse {
	
	private String code;
	private String score;
	private String riskLevel;
	private String matchedItem;
	private String filteredText;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getMatchedItem() {
		return matchedItem;
	}
	public void setMatchedItem(String matchedItem) {
		this.matchedItem = matchedItem;
	}
	public String getFilteredText() {
		return filteredText;
	}
	public void setFilteredText(String filteredText) {
		this.filteredText = filteredText;
	}
	@Override
	public String toString() {
		return "WordFilterResponse [code=" + code + ", score=" + score + ", riskLevel=" + riskLevel + ", matchedItem="
				+ matchedItem + ", filteredText=" + filteredText + "]";
	}
}
