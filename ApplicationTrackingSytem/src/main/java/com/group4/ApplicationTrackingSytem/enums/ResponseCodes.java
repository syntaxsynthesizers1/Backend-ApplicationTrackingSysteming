package com.group4.ApplicationTrackingSytem.enums;

public enum ResponseCodes {
    SUCCESS("000", "SUCCESSFUL"),
    ERROR("999", "FAILED"),
    TRANSACTION_REF_EXISTS("E01", "TRANSACTION REF EXISTS"),
    TRANSACTION_SAVE_FAILED("E02", "TRANSACTION SAVE FAILED"),
    TRANSACTION_STILL_PROCESSING("E00", "TRANSACTION PENDING COMPLIANCE"),
    TRANSACTION_UNSUCCESFUL("999", "Sorry we could not process your transaction at the moment contact Admin"),
    TRANSACTION_LIMIT_EXCEEDED("099", "Transaction Channel Limit Exceeded"),
    INVALID_PRODUCT("E03","INVALID PRODUCT SPECIFIED"),
    INVALID_BENEFICIARY_ACCOUNT("E04","INVALID BENEFICIARY ACCOUNT"),
    INVALID_RECEIVE_CURRENCY("E06","INVALID RECEIVE CURRENCY"),
    MISSING_FIELD_IN_REQUEST("E05","MISSING FIELD IN REQUEST: "),
    TRANSACTION_REF_NOT_FOUND("E07","TRANSACTION REFERENCE NOT FOUND"),

    LP_CURRENCY_MISMATCH("E060", "CURRENCY MISMATCH"),
    TRANSACTION_POSTING_FAILED("E09","TRANSACTION POSTING FAILED"),
    INVALID_AMOUNT("E010","TRANSACTION AMOUNT OF 0 IS NOT ALLOWED");

    ResponseCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
