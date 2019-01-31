package com.github.infovip.core.session;

import java.io.Serializable;


public enum SESSION implements Serializable {
	
    // This session attribute is used for the administration interface. It is worth noting that the value is null in all other cases.
	USER_SESSION("userSession"),
    
	// It is a general attribute, indicates the time of the authentication
	AUTH_TIME("authTime"),
    
	// It is a general attribute, indicates the address of the remote client
	// Remote address always use the REMOTE_ADDR option, the HTTP_X_FORWARDED_FOR attribute is needed to be handled by manually
	REMOTE_ADDR("remoteAddr"),
	
	// General attribute, stores the common information about the client
    HEADER("clientHeader"),
	
    // General attribute . Indicates that the user is logged on to the system.
    IS_AUTHENTICATED("isAuthenticated");
	
    private String value;

    private SESSION(String val) {
        this.value = val;
    }

    public SESSION valueOfSession(String v) {
        for (SESSION s : SESSION.values()) {
            if (s.toString().equals(v)) {
                return s;
            }
        }
        return null;
    }

    
    public String value() {
    	return this.value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }

}
