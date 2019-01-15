package com.github.infovip.web.application.message;

import com.github.infovip.core.elasticsearch.ESDataElement;

public interface Message<T extends BaseMessage>  extends ESDataElement<T> {

}
