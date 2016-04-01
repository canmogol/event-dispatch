package com.fererlab.dispatch.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Status implements Serializable {
    public static final Status STATUS_CONTINUE = new Status(100, "Continue");
    public static final Status STATUS_SWITCHING_PROTOCOL = new Status(101, "Switching Protocol");
    public static final Status STATUS_OK = new Status(200, "OK");
    public static final Status STATUS_CREATED = new Status(201, "Created");
    public static final Status STATUS_ACCEPTED = new Status(202, "Accepted");
    public static final Status STATUS_NON_AUTHORITATIVE_INFORMATION = new Status(203, "Non-Authoritative Information");
    public static final Status STATUS_NO_CONTENT = new Status(204, "No Content");
    public static final Status STATUS_RESET_CONTENT = new Status(205, "Reset Content");
    public static final Status STATUS_PARTIAL_CONTENT = new Status(206, "Partial Content");
    public static final Status STATUS_AUTHENTICATION_SUCCESSFUL = new Status(230, "Authentication Successful");
    public static final Status STATUS_MULTIPLE_CHOICE = new Status(300, "Multiple Choice");
    public static final Status STATUS_MOVED_PERMANENTLY = new Status(301, "Moved Permanently");
    public static final Status STATUS_FOUND = new Status(302, "Found");
    public static final Status STATUS_SEE_OTHER = new Status(303, "See Other");
    public static final Status STATUS_NOT_MODIFIED = new Status(304, "Not Modified");
    public static final Status STATUS_USE_PROXY = new Status(305, "Use Proxy");
    public static final Status STATUS_UNUSED = new Status(306, "unused");
    public static final Status STATUS_TEMPORARY_REDIRECT = new Status(307, "Temporary Redirect");
    public static final Status STATUS_PERMANENT_REDIRECT = new Status(308, "Permanent Redirect");
    public static final Status STATUS_BAD_REQUEST = new Status(400, "Bad Request");
    public static final Status STATUS_UNAUTHORIZED = new Status(401, "Unauthorized");
    public static final Status STATUS_PAYMENT_REQUIRED = new Status(402, "Payment Required");
    public static final Status STATUS_FORBIDDEN = new Status(403, "Forbidden");
    public static final Status STATUS_NOT_FOUND = new Status(404, "Not Found");
    public static final Status STATUS_METHOD_NOT_ALLOWED = new Status(405, "Method Not Allowed");
    public static final Status STATUS_NOT_ACCEPTABLE = new Status(406, "Not Acceptable");
    public static final Status STATUS_PROXY_AUTHENTICATION_REQUIRED = new Status(407, "Proxy Authentication Required");
    public static final Status STATUS_REQUEST_TIMEOUT = new Status(408, "Request Timeout");
    public static final Status STATUS_CONFLICT = new Status(409, "Conflict");
    public static final Status STATUS_GONE = new Status(410, "Gone");
    public static final Status STATUS_LENGTH_REQUIRED = new Status(411, "Length Required");
    public static final Status STATUS_PRECONDITION_FAILED = new Status(412, "Precondition Failed");
    public static final Status STATUS_REQUEST_ENTITY_TOO_LARGE = new Status(413, "Request Entity Too Large");
    public static final Status STATUS_REQUEST_URI_TOO_LONG = new Status(414, "Request-URI Too Long");
    public static final Status STATUS_UNSUPPORTED_MEDIA_TYPE = new Status(415, "Unsupported Media Type");
    public static final Status STATUS_REQUESTED_RANGE_NOT_SATISFIABLE = new Status(416, "Requested Range Not Satisfiable");
    public static final Status STATUS_EXPECTATION_FAILED = new Status(417, "Expectation Failed");
    public static final Status STATUS_INTERNAL_SERVER_ERROR = new Status(500, "Internal Server Error");
    public static final Status STATUS_NOT_IMPLEMENTED = new Status(501, "Not Implemented");
    public static final Status STATUS_BAD_GATEWAY = new Status(502, "Bad Gateway");
    public static final Status STATUS_SERVICE_UNAVAILABLE = new Status(503, "Service Unavailable");
    public static final Status STATUS_GATEWAY_TIMEOUT = new Status(504, "Gateway Timeout");
    public static final Status STATUS_HTTP_VERSION_NOT_SUPPORTED = new Status(505, "HTTP Version Not Supported");
    private int status;
    private String message;

    public Status() {
        this(STATUS_OK.getStatus(), STATUS_OK.getMessage());
    }

    public Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        return "Status{status=" + this.status + ", message=\'" + this.message + '\'' + '}';
    }
}
