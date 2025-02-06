package com.xiaomo.common;

/**
 * 枚举，定义token的三种状态
 *
 */
 public enum TokenState {  
	EXPIRED("EXPIRED"), // 过期
	INVALID("INVALID"), // 无效(token不合法)
	VALID("VALID");  // 有效的
	
    private String state;  
      
    private TokenState(String state) {  
        this.state = state;  
    }
    
    /**
     * 根据状态字符串获取token状态枚举的对象
     * @param tokenState
     * @return
     */
    public static TokenState getTokenState(String tokenState){
    	TokenState[] states=TokenState.values();
    	TokenState ts=null;
    	for (TokenState state : states) {
			if(state.toString().equals(tokenState)){
				ts=state;
				break;
			}
		}
    	return ts;
    }
    public String toString() {
    	return this.state;
    }
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public static void main(String[] args) {
		System.out.println(TokenState.EXPIRED);

		TokenState state = TokenState.getTokenState(TokenState.EXPIRED.state);
		switch (state) {
			case VALID: {
//				// 取出payload中数据,放入到request作用域中
//				HashMap<String, String> data= (HashMap<String, String>) resultMap.get("data");
//
//				// 验证token信息是否有效
//				if(this.verifyTokenIsValidByStudent(data, request)) {
//					request.setAttribute("data", data);
//					chain.doFilter(request, response);
//					return;
//				}
//
//				// token验证失败，重新登录
//				if(StringHelp.equals(language, "ZH")) {
//					RespUtil.output(JSON.toJSONString(Result.error("您的登录已过期，请重新登录。", "000004")), response);
//				}else {
//					RespUtil.output(JSON.toJSONString(Result.error("Your session has expired. Please log in again.", "000004")), response);
//				}
				return;
			}
			case EXPIRED: {
				// token过期, 则输出错误信息返回
//				if(StringHelp.equals(language, "ZH")) {
//					RespUtil.output(JSON.toJSONString(Result.error("您的登录已过期，请重新登录。", "000004")), response);
//				}else {
//					RespUtil.output(JSON.toJSONString(Result.error("Your session has expired. Please log in again.", "000004")), response);
//				}
//				logger.info("访问接口验证权限失败，原因：token过期");
				return;
			}
			case INVALID: {
//				if(StringHelp.equals(language, "ZH")) {
//					RespUtil.output(JSON.toJSONString(Result.error("您的登录已过期，请重新登录。", "000004")), response);
//				}else {
//					RespUtil.output(JSON.toJSONString(Result.error("Your session has expired. Please log in again.", "000004")), response);
//				}
//				logger.info("访问接口验证权限失败，原因：token不合法");
				return;
			}
			default: {
//				if(StringHelp.equals(language, "ZH")) {
//					RespUtil.output(JSON.toJSONString(Result.error("您的登录已过期，请重新登录。", "000004")), response);
//				}else {
//					RespUtil.output(JSON.toJSONString(Result.error("Your session has expired. Please log in again.", "000004")), response);
//				}
//				logger.info("访问接口验证权限失败，原因：无效的token");
				return;
			}
		}
	}
}  
