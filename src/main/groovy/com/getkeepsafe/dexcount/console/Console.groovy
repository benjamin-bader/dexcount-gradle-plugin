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

/**
 * Represents a device that can print text to stdout.
 *
 * Depending on the implementation, output may be formatted, e.g. with ANSI
 * color escape codes.
 *
 * Regardless of implementation, {@code Console} does <em>not</em> participate
 * in Gradle's logging infrastructure.  Use a {@link org.gradle.api.logging.Logger}
 * for that.
 */
public interface Console {
    /**
     * Prints a line of text at the 'trace' level.
     */
    Console trace(Object text);

    /**
     * Prints a line of text at the 'info' level.
     */
    Console info(Object text);

    /**
     * Prints a line of text at the 'warn' level.
     */
    Console warn(Object text);

    /**
     * Prints a line of text at the 'error' level.
     */
    Console error(Object text);
}