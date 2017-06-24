/*
 * Copyright (C) 2015-2016 KeepSafe Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.getkeepsafe.dexcount.console

import org.gradle.api.logging.Logger

class NoFormattingConsole implements Console {
    private Logger logger;

    NoFormattingConsole(Logger logger) {
        this.logger = logger
    }

    @Override
    Console trace(Object text) {
        logger.trace("$text")
        return this;
    }

    @Override
    Console info(Object text) {
        logger.info("$text")
        return this;
    }

    @Override
    Console warn(Object text) {
        logger.warn("$text")
        return this;
    }

    @Override
    Console error(Object text) {
        logger.error("$text")
        return this;
    }
}
