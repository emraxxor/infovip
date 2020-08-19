package com.github.infovip.web.application.message;

import com.github.infovip.core.elasticsearch.ESExtendedDataElement;

public interface Message<T extends BaseMessage>  extends ESExtendedDataElement<T> {

}
