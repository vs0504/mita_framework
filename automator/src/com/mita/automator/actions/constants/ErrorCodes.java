package com.mita.automator.actions.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCodes {

  GENERIC_ERROR(1000000L),
  ASSERT_ERROR(1000001L),

  WEBDRIVER_EXCEPTION(1010000L),
  ELEMENT_TIMEOUT(1010100L),
  UNREACHABLE_BROWSER(1010200L),
  NO_SUCH_SESSION_EXCEPTION(1010300L),
  UNSUPPORTED_COMMAND_EXCEPTION(1010400L),
  //HTTP Status codes
  HTTP_BAD_REQUEST(400L),
  HTTP_UNAUTHORIZED(401L),
  HTTP_FORBIDDEN(403L),
  HTTP_NOT_FOUND(404L),
  HTTP_PROXY_AUTHENTICATION_REQUIRED(407L),
  HTTP_INTERNAL_SERVER_ERROR(500L),
  HTTP_GENERIC_CLIENT_SIDE_ERROR(4000L),
  HTTP_GENERIC_SERVER_SIDE_ERROR(5000L),
  HTTP_MALFORMED_YRL_EXCEPTION(4100L),
  HTTP_IO_EXCEPTION(4110L),
  HTTP_SOCKET_EXCEPTION(4120L),
  HTTP_UNKNOWN_HOST_EXCEPTION(4121L),

  //Handle NotFoundException types
  NOT_FOUND_EXCEPTION(1020100L),
  NO_SUCH_ELEMENT(1020200L),
  INVALID_SELECTOR(1020300L),
  NO_ALERT_PRESENT_EXCEPTION(1020400L),
  NO_SUCH_CONTEXT_EXCEPTION(1020500L),
  NO_SUCH_COOKIE_EXCEPTION(1020600L),
  NO_SUCH_FRAME_EXCEPTION(1020700L),
  NO_SUCH_WINDOW_EXCEPTION(1020800L),
  //Handle InvalidElementStateException exception types
  INVALID_ELEMENT_STATE_EXCEPTION(1030000L),
  ELEMENT_NOT_VISIBLE(1030100L),
  ELEMENT_CLICK_INTERCEPTED_EXCEPTION(1030200L),
  ELEMENT_NOT_SELECTABLE_EXCEPTION(1030300L),
  //Handle Javascript execption types
  JAVA_SCRIPT_EXCEPTION(1040000L),
  //Handle Move target out of bound exception
  MOVE_TARGET_OUT_OF_BOUND_EXCEPTION(1040000L),
  //Handle UnexpectedTag Exception types
  UNEXPECTED_TAG_NAME_EXCEPTION(1050000L),
  //Handle all StaleElementException types
  STALE_ELEMENT_EXCEPTION(1060000L),
  //Handle all UNHANDLEDALERTEXCEPTION types
  UNHANDLED_ALERT_EXCEPTION(1070000L),
  //Handle AutomatorException
  AUTOMATOR_EXCEPTION(1080000L),
  ELEMENT_NOT_DISPLAYED(1080100L),
  //Handle Exception. Ideally we should be handling all possible exceptions.
  GENERAL_EXCEPTION(1090000L),

  //Mobile
  PAGE_ELEMENT_LOAD_TIMEOUT(1100000L),
  PRESS_INVALID_ARGUMENT(1100001L),
  CHECK_BOX_VALIDATION(1100002L),
  PRESS_INVALID_OPERATION(1100003L);

  @Getter
  private final Long errorCode;


}
