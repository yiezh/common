package cn.enigma.project.common.exception;

import cn.enigma.project.common.error.GlobalErrorEnum;

/**
 * @author luzh
 * Create: 2019-06-17 12:03
 * Modified By:
 * Description:
 */
public class DataNotFoundException extends Exception {
    private static final long serialVersionUID = -2719568469419774112L;

    private String code = GlobalErrorEnum.DATA_BOT_FOUNT_ERROR.errorCode();

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException() {
        super(GlobalErrorEnum.DATA_BOT_FOUNT_ERROR.errorMessage());
    }

    public String getCode() {
        return code;
    }
}
