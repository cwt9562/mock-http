CREATE TABLE CUSTOM_RULE (
    ID VARCHAR(36) PRIMARY KEY NOT NULL,
    REQ_URI VARCHAR(2000) NOT NULL,
    RESP_STATUS_CODE INT,
    RESP_CONTENT_TYPE VARCHAR(200),
    RESP_BODY VARCHAR(5000),
    CREATE_AT VARCHAR(100) NOT NULL,
    UPDATE_AT VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX IDX_CUSTOM_REQUEST_URI ON CUSTOM_RULE (REQ_URI);
CREATE INDEX IDX_CUSTOM_CREATE_AT ON CUSTOM_RULE (CREATE_AT);
CREATE INDEX IDX_CUSTOM_UPDATE_AT ON CUSTOM_RULE (UPDATE_AT);