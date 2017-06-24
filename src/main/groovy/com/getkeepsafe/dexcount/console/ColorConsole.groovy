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

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.Color

class ColorConsole implements Console {
    private static final Color TRACE = Color.DEFAULT;
    private static final Color INFO = Color.GREEN;
    private static final Color WARN = Color.YELLOW;
    private static final Color ERROR = Color.RED;

    private Console delegate;

    ColorConsole(Console delegate) {
        this.delegate = delegate;
    }

    @Override
    public Console trace(Object text) {
        delegate.trace(styledText(TRACE, text));
        return this;
    }

    @Override
    public Console info(Object text) {
        delegate.trace(styledText(INFO, text));
        return this;
    }

    @Override
    public Console warn(Object text) {
        delegate.trace(styledText(WARN, text));
        return this;
    }

    @Override
    public Console error(Object text) {
        delegate.trace(styledText(ERROR, text));
        return this;
    }

    private static String styledText(Color color, Object text) {
        return new Ansi().fg(color).a(text).reset();
    }
}
