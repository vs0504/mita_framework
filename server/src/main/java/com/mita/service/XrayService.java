package com.mita.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mita.exception.MitaException;
import com.mita.web.request.IntegrationsRequest;

public interface XrayService {

    JsonNode testIntegration(IntegrationsRequest testAuth) throws MitaException;
}
