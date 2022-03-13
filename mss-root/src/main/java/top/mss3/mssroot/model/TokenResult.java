package top.mss3.mssroot.model;

import org.springframework.boot.diagnostics.analyzer.BeanNotOfRequiredTypeFailureAnalyzer;

public enum TokenResult{
    NO_TOKEN(new BaseResult<>(-5, "未输入 Token", "")),
    TOKEN_ERROR(new BaseResult<>(-5, "Token 错误", "")),
    USER_NOTFOUND(new BaseResult<>(-5, "Token 所属用户未找到", "")),
    PASSWORD_ERROR(new BaseResult<>(-5, "Token 所属用户密码错误", "")),
    SUCCESS_TOKEN(null);

    private final BaseResult result;

    TokenResult(BaseResult result){
        this.result = result;
    }

    public BaseResult getResult() {
        if(this != SUCCESS_TOKEN){
            return new BaseResult<>(-5, "Token 错误", "");
        }
        return this.result;
    }
}