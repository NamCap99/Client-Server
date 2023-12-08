package client;


import shares.ResponseBody;

/**
 * abstract handler data class depending on get by id function, list weather
 * @param <T>
 */
public abstract class HandlerResponseHttp<T> {
    protected T handlerResponse(ResponseBody responseBody) throws Exception {
        switch (ResponseBody.Code.fromValue(responseBody.getCode())){
            case SUCCESS:
                return handlerResponseSuccess(responseBody.getResult());
            default: throw new Exception(responseBody.getMessage());
        }
    }

    protected abstract T handlerResponseSuccess(Object result);
}
