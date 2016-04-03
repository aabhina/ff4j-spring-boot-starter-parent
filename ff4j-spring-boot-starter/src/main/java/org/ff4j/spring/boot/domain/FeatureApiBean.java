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
import org.ff4j.core.Feature;
import org.ff4j.property.Property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
public class FeatureApiBean implements Serializable {

    private static final long serialVersionUID = -4977143873952901044L;

    @Getter
    @Setter
    private String uid;

    @Getter
    @Setter
    private boolean enable;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String group;

    @Getter
    @Setter
    private List<String> permissions = new ArrayList<>();

    @Getter
    @Setter
    private FlippingStrategyApiBean flippingStrategy;

    @Getter
    @Setter
    private Map<String, PropertyApiBean> customProperties = new HashMap<>();

    public FeatureApiBean() {
        super();
    }

    public FeatureApiBean(Feature f) {
        this.uid = f.getUid();
        this.enable = f.isEnable();
        this.description = f.getDescription();
        this.group = f.getGroup();
        this.permissions = new ArrayList<>(f.getPermissions());
        if (f.getFlippingStrategy() != null) {
            this.flippingStrategy = new FlippingStrategyApiBean(f.getFlippingStrategy());
        }
        if (f.getCustomProperties() != null) {
            for (Property<?> ap1 : f.getCustomProperties().values()) {
                customProperties.put(ap1.getName(), new PropertyApiBean(ap1));
            }
        }
    }
}