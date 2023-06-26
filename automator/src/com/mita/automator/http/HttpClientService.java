

package com.mita.automator.http;

public class HttpClientService {
  private static HttpClientService _instance = null;

  public static HttpClientService getInstance() {
    if (_instance == null)
      _instance = new HttpClientService();

    return _instance;

  }
}
