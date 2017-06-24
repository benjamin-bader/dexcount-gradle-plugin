package com.getkeepsafe.dexcount

import net.rubygrapefruit.platform.Native
import net.rubygrapefruit.platform.NativeIntegrationUnavailableException
import net.rubygrapefruit.platform.Terminal
import net.rubygrapefruit.platform.Terminals
import org.fusesource.jansi.WindowsAnsiOutputStream

final class NativeServices {
    private static boolean initialized;

    private static boolean canUseNativeServices;
    private static boolean isWindows;

    static boolean isAnsiTerminal() {
        initialize()

        if (canUseNativeServices) {
            try {
                System.out.println("canUseNativeServices=true")
                def terminals = Native.get(Terminals)
                System.out.println("created Terminals")
                def term = System.getenv("TERM")
                System.out.println("env[TERM]=$term")
                if ("dumb" == term) {
                    return false
                }
                def isStdoutTerminal = terminals.isTerminal(Terminals.Output.Stdout)
                def isStderrTerminal = terminals.isTerminal(Terminals.Output.Stderr)
                System.out.println("stdout=$isStdoutTerminal stderr=$isStderrTerminal")

                return isStdoutTerminal || isStderrTerminal
            } catch (NativeIntegrationUnavailableException ignored) {
                return isWindows
            }
        } else if (isWindows) {
            return true
        } else {
            System.out.println("cannot use native services :(")
            return false
        }
    }

    static synchronized void initialize() {
        if (!initialized) {
            initialized = true

            def home = getDexcountHomeDir()
            if (home == null) {
                System.out.println("Could not create dexcount home :(")
                return
            }

            try {
                Native.init(home)
                canUseNativeServices = true
            } catch (Exception ignored) {
                System.out.println("Error initializing Native")
                ignored.printStackTrace()
            }
        }
    }

    static synchronized File getDexcountHomeDir() {
        String home = System.properties["user.home"]
        if (home == null) {
            System.out.println("user.home was null!")
            return null
        }

        // Work around the Oracle JDK 7-and-below profile bug on Win7+
        // http://bugs.java.com/view_bug.do?bug_id=4787931
        def profileMatcher = home =~ /(?i)^%userprofile%/
        if (profileMatcher.matches()) {
            def userProfile = System.getenv("userprofile")
            if (userProfile != null) {
                home = profileMatcher.replaceAll(userProfile)
            }
        }

        System.out.println("user.home = $home")

        def homeDir = new File(home)
        if (!homeDir.exists()) {
            System.out.println("user.home does not exist!")
            return null
        }

        // We've got a home directory - what we do with it is platform-dependent.
        // On *nix platforms, we can just use a good 'ol dot directory; not so on
        // Windows.  Program-specific data on Windows goes not into dot directories,
        // but into various parts of "AppData", depending on the type of data.
        // In our case, the right choice is under "$HOME/AppData/Local/dexcount".
        File dexcountHome
        try {
            // This will blow up if we aren't on windows.
            new WindowsAnsiOutputStream(new ByteArrayOutputStream())

            // we're on windows
            isWindows = true
            def appData = new File(homeDir, "AppData")
            if (appData.exists()) {
                def localData = new File(appData, "Local")
                if (localData.exists()) {
                    dexcountHome = new File(localData, "dexcount")
                }
            }
        } catch (UnsatisfiedLinkError ignored) {
            // we're not on windows
            dexcountHome = new File(homeDir, ".dexcount")
        }

        if (dexcountHome != null) {
            System.out.println("Attempting to .mkdirs() $dexcountHome")
            dexcountHome.mkdirs()
        }

        return dexcountHome
    }

    private NativeServices() {
        // no instances
    }
}
