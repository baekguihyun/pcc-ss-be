package egovframework.com.cmm.service;

import java.util.HashMap;
import java.util.Map;

public class ResultVO {

	private int resultCode = 0;
	private String resultMsg = "OK";
	private Map<String, Object> result = new HashMap<String, Object>();

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMsg;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMsg = resultMessage;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void putResult(String key, Object value) {
		result.put(key, value);
	}

	public Object getResult(String key) {
		return this.result.get(key);
	}


}
