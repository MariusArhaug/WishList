package wishList.restapi;

public class Response<T> {

  private final RESPONSE_TYPE RESPONSE;
  private final int STATUS_CODE;
  private final T content;
  /**
   * Response object.
   *
   * @param responseType type of response
   * @param statusCode status code
   * @param content response value
   */
  public Response(RESPONSE_TYPE responseType, int statusCode, T content) {
    this.RESPONSE = responseType;
    this.STATUS_CODE = statusCode;
    this.content = content;
  }

  public enum RESPONSE_TYPE {
    GET,
    POST,
    PUT,
    DELETE
  }
}
