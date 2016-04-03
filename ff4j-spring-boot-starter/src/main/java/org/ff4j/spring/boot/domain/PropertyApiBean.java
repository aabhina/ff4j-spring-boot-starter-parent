/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2016 the original author or authors.
 */

package org.ff4j.spring.boot.domain;


import lombok.Getter;
import lombok.Setter;
import org.ff4j.property.Property;
import org.ff4j.property.util.PropertyFactory;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
public class PropertyApiBean implements Serializable {

    private static final long serialVersionUID = -5366099799518640405L;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String value;

    @Getter
    private Set<String> fixedValues = new HashSet<>();

    public PropertyApiBean() {
        super();
    }

    public PropertyApiBean(Property<?> property) {
        if (property != null) {
            this.name = property.getName();
            this.description = property.getDescription();
            this.type = property.getType();
            this.value = property.asString();
            if (property.getFixedValues() != null) {
                fixedValues.addAll(property.getFixedValues().stream().map(Object::toString).collect(Collectors.toList()));
            }
        }
    }

    public Property asProperty() {
        return PropertyFactory.createProperty(name, type, value, description, fixedValues);
    }
}