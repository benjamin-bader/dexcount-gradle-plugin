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

import net.rubygrapefruit.platform.Native
import org.fusesource.jansi.WindowsAnsiOutputStream
import org.gradle.api.logging.Logger

/**
 * Creates implementations of {@link Console} based on the capabilities of the
 * current platform and runtime environment.
 */
public final class ConsoleFactory {
    private ConsoleFactory() {
        // no instances
    }


    public static Console create(Logger logger, boolean useColor) {
        def console = new NoFormattingConsole(logger)

        if (useColor) {
            console = new ColorConsole(console)
        }

        return console
    }
}
