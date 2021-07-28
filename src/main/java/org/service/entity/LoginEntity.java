package org.service.entity;

import java.io.Serializable;
import java.util.Objects;

/** LoginEntity */

public class LoginEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long ts = null;

	private String ip = null;

	public LoginEntity loginEntity() {
		this.ts = getTs();
		this.ip = getIp();
		return this;
	}
	
	public LoginEntity loginEntity(LoginEntity data) {
		this.ts = data.getTs();
		this.ip = data.getIp();
		return this;
	}
	
	public LoginEntity ts(Long ts) {
		this.ts = ts;
		return this;
	}

	public Long getTs() {
		return ts;
	}

	public void setTimestamp(Long ts) {
		this.ts = ts;
	}

	public LoginEntity ip(String ip) {
		this.ip = ip;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoginEntity loginEntity = (LoginEntity) o;
		return Objects.equals(this.ts, loginEntity.ts)
				&& Objects.equals(this.ip, loginEntity.ip);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoginEntity {\n");
		sb.append("    ts: ").append(toIndentedString(ts)).append("\n");
		sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
