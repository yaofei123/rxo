/*
 * Copyright (C) 2017  , All Rights Reserved.
 */

package com.refactoring.rxo.springmvc.exception;

import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 批量处理异常
 * @author
 * @Date 2017-12-23 10:32
 * @Modified by:
 **/
public class BatchCheckResouceException extends Exception  {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    private List errorData = new ArrayList();

    public List getErrorData() {
        return errorData;
    }

    public void setErrorData(List errorData) {
        this.errorData = errorData;
    }

    /**
     * 批量校验异常
     * @param code
     * @param errorData
     */
    public BatchCheckResouceException(Integer code , List errorData){
        super("批量校验出现错误.");
        this.code = code;
        this.errorData = errorData;
    }
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public BatchCheckResouceException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BatchCheckResouceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public BatchCheckResouceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public BatchCheckResouceException(Throwable cause) {
        super(cause);
    }
}
