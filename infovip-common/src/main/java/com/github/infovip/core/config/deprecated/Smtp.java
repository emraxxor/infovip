package com.github.infovip.core.config.deprecated;

public class Smtp {

	private String hostname;
	
	private int port;
	
	private String username;
	
	private String password;
	
	private boolean ssl;
	
	private String from;
	
	private boolean forceSSL;
	
	private boolean useStartTLS;
	
	
	
	public boolean isForceSSL() {
		return forceSSL;
	}

	public void setForceSSL(boolean forceSSL) {
		this.forceSSL = forceSSL;
	}

	public boolean isUseStartTLS() {
		return useStartTLS;
	}

	public void setUseStartTLS(boolean useStartTLS) {
		this.useStartTLS = useStartTLS;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Smtp() {
		// TODO Auto-generated constructor stub
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (forceSSL ? 1231 : 1237);
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + port;
		result = prime * result + (ssl ? 1231 : 1237);
		result = prime * result + (useStartTLS ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Smtp other = (Smtp) obj;
		if (forceSSL != other.forceSSL)
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (port != other.port)
			return false;
		if (ssl != other.ssl)
			return false;
		if (useStartTLS != other.useStartTLS)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	
}
